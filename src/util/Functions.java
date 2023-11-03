package util;

import javax.swing.*;
import java.io.*;

public class Functions {
    public char[] getCharacters() {
        String uppercaseSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseSet = "abcdefghijklmnopqrstuvwxyz";
        String specialSet = " .?'!,";

        return (uppercaseSet + lowercaseSet + specialSet).toCharArray();
    } // end of determineCharacters method

    public int[] determineFrequency(char[] characters, String filename) {
        File file = new File(filename);
        String contents = readFile(file);

        int[] frequencies = new int[characters.length];
        int frequency = 0;

        for (int i = 0; i < characters.length; i++, frequency = 0) {
            for (int j = 0; j < contents.length(); j++) {
                char character = contents.charAt(j);
                if (character == characters[i]) {
                    frequency++;
                }
            }
            frequencies[i] = frequency;
        }
        return frequencies;
    } // end of determineFrequency method

    private String readFile(File file){
        StringBuilder contents = new StringBuilder();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                contents.append(line);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "There seems to be an error during a process\naborting...");
        }
        return contents.toString();
    }
} // end of Functions class