package util;

import javax.swing.*;
import java.io.*;

public class CharacterFrequencyCounter {
    private final FileReaderUtility utility = new FileReaderUtility();

    /**
     * Retrieves the character set of a file used for frequency determination.
     * This method returns an array of characters representing the character set used for frequency analysis.
     *
     * @param file the file to be read
     * @return The character set as an array of characters.
     */
    public char[] getCharacterSet(File file) {
        StringBuilder characterSet = new StringBuilder();
        String fileContents = utility.readFile(file);

        String uppercaseSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseSet = "abcdefghijklmnopqrstuvwxyz";
        String specialSet = " .?'!,\n";
        String acceptedCharacters = uppercaseSet + lowercaseSet + specialSet;
        StringBuilder unacceptedCharacters = new StringBuilder();

        for (int i = 0; i < fileContents.length(); i++) {
            for (int j = 0; j < acceptedCharacters.length(); j++) {
                char character = fileContents.charAt(i);
                if (character == '\\') {
                    character = '\n';
                    i++;
                }

                if (acceptedCharacters.contains(String.valueOf(character))) {
                    if (characterSet.indexOf(String.valueOf(character)) == -1) {
                        characterSet.append(character);
                    }
                } else {
                    unacceptedCharacters.append(character);
                }
            }
        }
        if (!unacceptedCharacters.isEmpty()) {
            JOptionPane.showMessageDialog(null, """
                    Invalid character detected in the textfile.
                    
                    Please run the program again to load the text file
                    with the accepted character sets only.
                    """);
            System.exit(0);
        }
        return characterSet.toString().toCharArray();
    } // end of getCharacterSet

    /**
     * Determines the frequency of specified characters in a given file's contents.
     * This method reads the contents of the provided file, counts the occurrences of each character
     * in the 'characters' array, and returns an array of frequencies.
     *
     * @param characters An array of characters for which frequency needs to be determined.
     * @param file The name of the file to read the contents from.
     * @return An array of integers representing the frequency of each character in the 'characters' array.
     */
    public int[] determineFrequency(char[] characters, File file) {
        String contents = utility.readFile(file);

        int[] frequencies = new int[characters.length];

        for (int i = 0; i < characters.length; i++) {
            char characterToCount = characters[i];
            for (int j = 0; j < contents.length(); j++) {
                char character = contents.charAt(j);
                if (character == '\\') {
                    character = '\n';
                    j++;
                }
                if (character == characterToCount) {
                    frequencies[i]++;
                }
            }
        }
        return frequencies;
    } // end of determineFrequency
} // end of CharacterFrequencyCounter class