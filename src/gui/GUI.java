package gui;

import javax.swing.*;

public class GUI extends JFrame {
    public GUI() {
        initializeFrame();
    } // end of default constructor

    private void initializeFrame() {
        this.setLayout(null);
        this.setTitle("Huffman Codec Application");
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    } // end of initializeFrame
} // end of GUI class
