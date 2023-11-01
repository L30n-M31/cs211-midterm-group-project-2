import com.sun.source.tree.Tree;
import node.TreeNode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Test program;
        try {
            program = new Test();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of main method

    public void run() {
        /*
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum.";

         */

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String text = keyboard.nextLine();

        char[] charactersUsed = determineCharactersUsed(text);
        int[] occurrences = determineNumberOfOccurrences(charactersUsed, text);
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<String> code = new ArrayList<>();

        // Display the character used and the frequency of the character
        System.out.printf("%-13s%-15s%n", "Character", "Occurrences");
        System.out.printf("%-13s%-15s%n", "===========", "=============");
        for (int i = 0; i < charactersUsed.length; i++) {
            String character = charactersUsed[i] == ' ' ? "Space" : String.valueOf(charactersUsed[i]);
            int occurrence = occurrences[i];
            System.out.printf("%-13s%-15d%n", character, occurrence);
        }

        // Decode the text in its binary form
        System.out.println("\n" + "Binary Code: " + toBinaryCode(text));
        System.out.println("\n" + "Huffman Tree" + "\n");

        // Create the huffman tree
        PriorityQueue<TreeNode> huffmanTree = createHuffmanTree(charactersUsed, occurrences);
        TreeNode root = getTreeRoot(huffmanTree);

        // Create and display the huffman code table
        createHuffmanCode(root, "", characters, code);
        viewHuffmanTable(characters, code);

        // Display the Encoded text
        System.out.println("\n" + "Original Text: " + text);
        System.out.println("Encoded Huffman Code: " + toHuffmanCode(text, characters, code));

        System.out.println("\n\n");
    } // end of run method

    /**
     * Method that determines the characters that are used in a text
     * @param text String given by the user
     * @return array of char (characters used)
     */
    public char[] determineCharactersUsed(String text) {
        char[] lowercaseChar = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] uppercaseChar = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] specialChar = {' ','.','?','’','!',','};

        StringBuilder charactersUsed = new StringBuilder();

        for (int i = 0; i < lowercaseChar.length; i++) {
            if (text.contains(String.valueOf(lowercaseChar[i]))) {
                charactersUsed.append(lowercaseChar[i]);
            }
            if (text.contains(String.valueOf(uppercaseChar[i]))) {
                charactersUsed.append(uppercaseChar[i]);
            }
        }

        for (char character : specialChar) {
            if (text.contains(String.valueOf(character))) {
                charactersUsed.append(character);
            }
        }
        return charactersUsed.toString().toCharArray();
    } // end of determineCharacters method

    /**
     * Method that determines the number of occurrences a character has been used
     * @param characterArray Array that contains the characters used in the text
     * @param text String given by the user
     * @return array of integers (occurrences)
     */
    public int[] determineNumberOfOccurrences(char[] characterArray, String text) {
        int[] occurrenceArray = new int[text.length()];
        int occurrence = 0;

        for (int i = 0; i < characterArray.length; i++, occurrence = 0) {
            for (int j = 0; j < text.length(); j++) {
                if (characterArray[i] == text.charAt(j)) {
                    occurrence++;
                }
            }
            occurrenceArray[i] = occurrence;
        }
        return occurrenceArray;
    } // end of determineOccurrences method

    /**
     * Method that converts a given text to its binary counterpart
     * @param text text given by the user at runtime
     * @return the binary code of the text
     */
    public String toBinaryCode(String text) {
        StringBuilder binaryCode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            int ascii = Integer.valueOf(character);
            String binaryValue = Integer.toBinaryString(ascii);
            binaryCode.append(binaryValue);
        }
        return binaryCode.toString();
    } // end of toBinaryCode method

    /**
     * Method that encodes the huffman code of a text
     * @param text text given by the user at runtime
     * @param characters the characters used in the text
     * @param code the huffman code of each character
     * @return the encoded text in huffman code
     */
    public String toHuffmanCode(String text, ArrayList<Character> characters, ArrayList<String> code) {
        StringBuilder huffmanCode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            for (int j = 0; j < characters.size(); j++) {
                if (character == characters.get(j)) {
                    huffmanCode.append(code.get(j)).append(" ");
                    break;
                }
            }
        }
        return huffmanCode.toString();
    } // end of toHuffmanCode method

    /**
     * Method that creates a huffman tree
     * @param characters the characters used in the text
     * @param occurrences the number of times a character was used in the text
     * @return populated huffman tree
     */
    public PriorityQueue<TreeNode> createHuffmanTree(char[] characters, int[] occurrences) {
        int n = characters.length;
        PriorityQueue<TreeNode> huffmanTree = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            TreeNode huffmanNode = new TreeNode(occurrences[i], characters[i], null, null);
            huffmanTree.add(huffmanNode);
        }
        return huffmanTree;
    } // end of createHuffmanTree method

    /**
     * Method that retrieves the root of the huffman tree
     * @param huffmanTree the tree to retrieve its root
     */
    public TreeNode getTreeRoot(PriorityQueue<TreeNode> huffmanTree) {
        TreeNode root = null;

        while (huffmanTree.size() > 1) {
            TreeNode left = huffmanTree.peek();
            huffmanTree.poll();

            TreeNode right = huffmanTree.peek();
            huffmanTree.poll();

            TreeNode v = new TreeNode((left.getCount() + right.getSymbol()), '-', left, right);
            huffmanTree.add(v);
        }
        return huffmanTree.poll();
    }

    /**
     * Method that displays the huffman table of a given text
     * @param characters the characters used in the text
     * @param code the huffman code of each character
     */
    public void viewHuffmanTable(ArrayList<Character> characters, ArrayList<String> code) {

        System.out.println("Char | Huffman Code");
        System.out.println("-------------------");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + " | " + code.get(i));
        }
    } // end of viewHuffmanTable method

    /**
     * Method that creates the reference table of the huffman code
     * @param root the root of the huffman tree
     * @param s the binary representation of a character
     * @param characters the characters used in the text
     * @param code the huffman code of each character
     */
    public void createHuffmanCode(TreeNode root, String s, ArrayList<Character> characters, ArrayList<String> code) {
        if (root.getLeft() == null && root.getRight() == null) {
            characters.add(root.getSymbol());
            code.add(s);
            return;
        }
        createHuffmanCode(root.getLeft(), s + "0", characters, code);
        createHuffmanCode(root.getRight(), s + "1", characters, code);
    } // end of createHuffmanCode method
} // end of Test class
