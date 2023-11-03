package util;

import node.TreeNode;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodec {
    private TreeNode root;
    private HashMap<Character, String> table;
    private final StringBuilder treeOutput = new StringBuilder();

    public void createHuffmanTree(char[] characters, int[] frequency) {
        int n = characters.length;
        PriorityQueue<TreeNode> huffmanTree = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            TreeNode huffmanNode = new TreeNode(characters[i], frequency[i], null, null);
            huffmanTree.add(huffmanNode);
        }

        while (huffmanTree.size() > 1) {
            TreeNode left = huffmanTree.poll();
            TreeNode right = huffmanTree.poll();

            TreeNode v = new TreeNode('¬', (left.getCount() + right.getCount()), left, right);
            huffmanTree.add(v);
        }

        root = huffmanTree.poll();
    } // end of createHuffmanTree

    public void initializeHuffmanTable(TreeNode root, String binaryValue) {
        table = new HashMap<>();
        createHuffmanCode(root, binaryValue);
    }

    private void createHuffmanCode(TreeNode root, String binaryValue) {
        if (root.getLeft() == null && root.getRight() == null) {
            table.put(root.getSymbol(), binaryValue);
            return;
        }
        createHuffmanCode(root.getLeft(), binaryValue + "0");
        createHuffmanCode(root.getRight(), binaryValue + "1");
    } // end of createHuffmanCode method

    public String convertToHuffmanCode(String text) {
        StringBuilder huffmanCode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character == '\\') {
                character = '\n';
                i++;
            }
            if (table.containsKey(character)) {
                String code = table.get(character);
                huffmanCode.append(code);
            }
        }
        return huffmanCode.toString();
    } // end of convertToHuffmanCode method

    public String convertToText(String huffmanCode) {
        StringBuilder text = new StringBuilder();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < huffmanCode.length(); i++) {
            code.append(huffmanCode.charAt(i));
            for (Map.Entry<Character, String> entry : table.entrySet()) {
                if (entry.getValue().contentEquals(code)) {
                    char character = entry.getKey();
                    text.append(character);
                    code.setLength(0);
                    break;
                }
            }
        }
        return text.toString();
    }

    public void viewHuffmanTable() {
        System.out.println("Char | Huffman Code");
        System.out.println("-------------------");
        for (Map.Entry<Character, String> entry : table.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            System.out.println(character + " | " + code);
        }
    } // end of viewHuffmanTable method

    public double computeSavings(String binaryCode, String huffmanCode) {
        return (double) huffmanCode.length() / binaryCode.length() * 100;
    } // end of computeSavings method

    public void printTree(TreeNode root, String prefix, boolean isLeftChild) {
        if (root != null) {
            String symbol;
            if (root.getSymbol() == ' ') {
                symbol = "Space";
            } else if (root.getSymbol() == '\n') {
                symbol = "Newline";
            } else {
                symbol = String.valueOf(root.getSymbol());
            }

            treeOutput.append(prefix).append(isLeftChild ? "└──  " : "├──  ")
                    .append(symbol).append(" (").append(root.getCount()).append(")\n");
            if (root.getLeft() != null || root.getRight() != null) {
                if (root.getLeft() != null) {
                    printTree(root.getLeft(), prefix + (isLeftChild ? "     " : "│    "), false);
                }
                if (root.getRight() != null) {
                    printTree(root.getRight(), prefix + (isLeftChild ? "     " : "│    "), true);
                }
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public HashMap<Character, String > getTable() {
        return table;
    }

    public StringBuilder getTreeOutput() {
        return treeOutput;
    }
} // end of HuffmanCodec class
