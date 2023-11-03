import com.sun.source.tree.Tree;
import node.TreeNode;
import util.BinaryEncoder;
import util.Functions;
import util.HuffmanCodec;

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
        Functions function = new Functions();
        BinaryEncoder binaryEncoder = new BinaryEncoder();
        HuffmanCodec huffmanOperations = new HuffmanCodec();
        Scanner keyboard = new Scanner(System.in);

        // Create the array of the character set and its frequency from a given text file (character-set.txt)
        char[] characters = function.getCharacters();
        String filename = "res/character-set.txt";
        int[] frequencies = function.determineFrequency(characters, filename);

        // Display the character used and the frequency of the character
        System.out.println("CHARACTER AND FREQUENCY TABLE:\n");
        System.out.printf("%-13s%-15s%n", "Character", "Frequency");
        System.out.printf("%-13s%-15s%n", "===========", "=============");
        for (int i = 0; i < characters.length; i++) {
            String character = characters[i] == ' ' ? "Space" : String.valueOf(characters[i]);
            int frequency = frequencies[i];
            System.out.printf("%-13s%-15d%n", character, frequency);
        }

        // Ask a text input from the user
        System.out.println("\nENCODE TEXT TO HUFFMAN CODE");
        System.out.println("==============================");
        System.out.print("Enter a text: ");
        String text = keyboard.nextLine();

        // Encode the text to its binary form
        String binaryCode = binaryEncoder.convertToBinaryASCII(text);

        // Create the huffman tree
        huffmanOperations.createHuffmanTree(characters, frequencies);
        TreeNode root = huffmanOperations.getRoot();


        // Create and display the huffman code table
        huffmanOperations.initializeHuffmanTable(root, "");
        huffmanOperations.viewHuffmanTable();

        // Encode the text to its huffman code
        String huffmanCode = huffmanOperations.convertToHuffmanCode(text);

        // Display the encoded binary and huffman codes with the savings gained
        System.out.println("------------------------------");
        System.out.println("Original Text: " + text);
        System.out.println("Encoded Binary Code: " + binaryCode);
        System.out.println("Encoded Huffman Code: " + huffmanCode);
        System.out.printf("%s%.2f%s%n", "Savings: ", huffmanOperations.computeSavings(binaryCode, huffmanCode), "%");

        // Ask a huffman code from the user
        System.out.println("\nDECODED HUFFMAN CODE");
        System.out.println("=======================");
        System.out.print("Enter a huffman code (base it on the huffman table): ");
        String huffmanCodeInput = keyboard.nextLine();

        // Decode the huffman code
        String decodedCode = huffmanOperations.convertToText(huffmanCodeInput);

        // Display the decoded huffman code
        System.out.println("-----------------------");
        System.out.println("Huffman Code: " + huffmanCodeInput);
        System.out.println("Decoded Code: " + decodedCode);

        // Display the huffman tree
        System.out.println("\nHUFFMAN TREE");
        System.out.println("===============");
        huffmanOperations.printTree(huffmanOperations.getRoot(), " ", true);
    } // end of run method
} // end of Test class
