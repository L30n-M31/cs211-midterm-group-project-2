import node.TreeNode;

import java.util.PriorityQueue;

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

        String text = "I Love You";

        char[] charactersUsed = determineCharactersUsed(text);
        int[] occurrences = determineNumberOfOccurrences(charactersUsed, text);

        System.out.printf("%-13s%-15s%n", "Character", "Occurrences");
        System.out.printf("%-13s%-15s%n", "===========", "=============");
        for (int i = 0; i < charactersUsed.length; i++) {
            String character = charactersUsed[i] == ' ' ? "Space" : String.valueOf(charactersUsed[i]);
            int occurrence = occurrences[i];
            System.out.printf("%-13s%-15d%n", character, occurrence);
        }

        System.out.println("\n" + "Binary Code: " + toBinaryCode(text));
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

    public void createHuffmanTree(char[] characters, int[] occurrences, PriorityQueue<TreeNode> huffmanTree) {

    } // end of createHuffmanTree method

    public void printCodeTable(TreeNode root, String s) {
        if (root.getLeft() == null && root.getRight() == null) {
            System.out.println(root.getSymbol() + " | " + s);
            return;
        }
        printCodeTable(root.getLeft(), s + "0");
        printCodeTable(root.getRight(), s + "1");
    } // end of printCodeTable
} // end of Test class
