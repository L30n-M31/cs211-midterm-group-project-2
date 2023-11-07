package app;

import gui.GUI;
import gui.MainPanel;
import gui.SidePanel;
import util.HuffmanCodec;
import javax.swing.*;
import java.io.File;

public class HuffmanCodecApp {
    private final HuffmanCodec huffmanCodec = new HuffmanCodec();

    /**
     * The main method of the application.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        HuffmanCodecApp program;
        try {
            program = new HuffmanCodecApp();
            program.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error detected");
        }
    } // end of main

    /**
     * Initializes and runs the application, setting up the user interface and Huffman coding functionality.
     */
    public void run() {
        File file = new File(fileToUse());

        SidePanel sidePanel = new SidePanel(huffmanCodec, file);
        MainPanel mainPanel = new MainPanel(huffmanCodec, file);

        GUI frame = new GUI();
        frame.add(sidePanel);
        frame.add(mainPanel);
    } // end of run

    /**
     * Determines the text file to be used to create the huffman codes
     *
     * @return the filename of the text file
     */
    public String fileToUse() {
        boolean proceedToApplication;
        StringBuilder filename = new StringBuilder("Character Set/");

        do {
            proceedToApplication = true;

            int choice = JOptionPane.showConfirmDialog(null, """
                    The program utilizes a predefined text file to get the huffman codes.
                    
                    NOTE FOR CUSTOM TEXT FILES: Ensure that the text file is stored in the Character Set directory.
                    Also make sure that the characters stored in the text file complies with the following accepted
                    characters:
                    - Uppercase Characters: A-Z
                    - Lowercase Characters: a-z
                    - Special Characters: space, period, question mark, apostrophe, exclamation point,
                      comma, and newLine (\\n)
                    
                    Should an invalid character be detected in the text file, an error message will occur and you
                    may have to open the application again.
                    
                    Do you want to use another text file of your own?
                    """, "TEXT FILE TO UTILIZE", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                String input = JOptionPane.showInputDialog(null, "Enter the name of the file");

                filename.append(input);
            } else if (choice == JOptionPane.NO_OPTION) {
                filename.append("char_set.txt");
            } else {
                System.exit(0);
            }

            File fileToCheck = new File(filename.toString());
            if (!fileToCheck.exists()) {
                JOptionPane.showMessageDialog(null, "File not found. Please try again");
                proceedToApplication = false;
            }
        } while (!proceedToApplication);
        return filename.toString();
    } // end of fileToUse
} // end of HuffmanCodecApp class
