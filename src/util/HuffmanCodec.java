package util;

import node.TreeNode;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodec {
    private TreeNode root;
    private HashMap<Character, String> huffmanCodeTable;
    private final StringBuilder treeOutput = new StringBuilder();

    /**
     * Creates a huffman tree based on a character set and their respective frequencies
     *
     * @param characterSet An array of characters representing the input character set
     * @param frequency An array of integers representing the frequencies of the characters.
     */
    public void createHuffmanTree(char[] characterSet, int[] frequency) {
        int n = characterSet.length;
        PriorityQueue<TreeNode> huffmanTree = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            TreeNode huffmanNode = new TreeNode(characterSet[i], frequency[i], null, null);
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

    /**
     * Initializes a huffman code table by generating binary codes for characters in the huffman tree
     *
     * @param root The root of the huffman tree
     * @param binaryValue  The binary code value for the leaf nodes
     */
    public void initializeHuffmanTable(TreeNode root, String binaryValue) {
        huffmanCodeTable = new HashMap<>();
        createHuffmanCodes(root, binaryValue);
    } // end of initializeHuffmanTable

    /**
     * The createHuffmanCodes method generates Huffman codes for each symbol in a Huffman tree.
     *
     * @param root The root of the huffman tree
     * @param binaryValue The binary code value for the leaf nodes
     */
    private void createHuffmanCodes(TreeNode root, String binaryValue) {
        if (root.getLeft() == null && root.getRight() == null) {
            huffmanCodeTable.put(root.getSymbol(), binaryValue);
            return;
        }
        createHuffmanCodes(root.getLeft(), binaryValue + "0");
        createHuffmanCodes(root.getRight(), binaryValue + "1");
    }// end of createHuffmanCodes

    /**
     * Converts a given text into huffman code using a huffman code table
     *
     * @param text The text to be converted into huffman code
     * @return The huffman code representation of the text input
     */
    public String convertToHuffmanCode(String text) {
        StringBuilder huffmanCode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character == '\\') {
                character = '\n';
                i++;
            }
            if (huffmanCodeTable.containsKey(character)) {
                String code = huffmanCodeTable.get(character);
                huffmanCode.append(code);
            }
        }
        return huffmanCode.toString();
    } // end of convertToHuffmanCode

    /**
     * Converts a given huffman code to its original text representation using a huffman code table
     *
     * @param huffmanCode The huffman code to be converted back to text
     * @return The original text represented by the given huffman code
     */
    public String convertToText(String huffmanCode) {
        StringBuilder text = new StringBuilder();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < huffmanCode.length(); i++) {
            code.append(huffmanCode.charAt(i));
            for (Map.Entry<Character, String> entry : huffmanCodeTable.entrySet()) {
                if (entry.getValue().contentEquals(code)) {
                    char character = entry.getKey();
                    text.append(character);
                    code.setLength(0);
                    break;
                }
            }
        }
        if (!code.isEmpty()) {
            JOptionPane.showMessageDialog(null, """
                        Your huffman code input is incomplete
                        
                        conversion process aborted...""");
            return null;
        }
        return text.toString();
    } // end of convertToText

    /**
     * Method that will compute the bits that is saved based on the binary code
     * and huffman method equivalent
     *
     * @param binaryCode binary value of the input
     * @param huffmanCode huffman code value of the input
     * @return value of the difference between the binary code and the huffman code
     */
    public double computeSavings(String binaryCode, String huffmanCode) {
        return 100 - ((double) huffmanCode.length() / binaryCode.length() * 100);
    } // end of computeSavings

    /**
     * Recursively prints the binary tree starting from the given root node.
     * Each node's symbol and count are displayed along with proper formatting to indicate
     * the tree structure.
     *
     * @param root         The root node of the binary tree to be printed.
     * @param prefix       The prefix string representing the indentation for the current node.
     * @param isLeftChild  A boolean flag indicating whether the current node is the left child of its parent.
     *                     Determines the appropriate formatting for tree branches.
     */
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

            // Format and append the current node's information to the treeOutput StringBuilder.
            treeOutput.append(prefix).append(isLeftChild ? "└──  " : "├──  ")
                    .append(symbol).append(" (").append(root.getCount()).append(")\n");

            // Recursively print the left and right subtrees with updated indentation.
            if (root.getLeft() != null || root.getRight() != null) {
                if (root.getLeft() != null) {
                    printTree(root.getLeft(), prefix + (isLeftChild ? "     " : "│    "), false);
                }
                if (root.getRight() != null) {
                    printTree(root.getRight(), prefix + (isLeftChild ? "     " : "│    "), true);
                }
            }
        }
    } // end of printTree

    /**
     * Get the root of the huffman tree
     *
     * @return The root node of the huffman tree
     */
    public TreeNode getRoot() {
        return root;
    } // end of getRoot

    /**
     * Get the huffman code table, which maps characters to their huffman codes
     *
     * @return the huffman code table as a HashMap
     */
    public HashMap<Character, String> getHuffmanCodeTable() {
        return huffmanCodeTable;
    } // end of getHuffmanCodeTable

    /**
     * Get the output representation of the huffman tree
     *
     * @return A StringBuilder containing the representation of the huffman tree
     */
    public StringBuilder getTreeOutput() {
        return treeOutput;
    } // end of getTreeOutput
} // end of HuffmanCodec class
