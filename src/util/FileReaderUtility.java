package util;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderUtility {

    /**
     * Method that will read a file and get its content that will be used on converting its huffman code
     * equivalent
     *
     * @param file file wherein the input is from
     * @return the contents of the file as a string value
     */
    public String readFile(File file) {
        StringBuilder fileContents = new StringBuilder();

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                fileContents.append(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
        return fileContents.toString();
    } // end of readFile
} // end of FileReaderUtility class
