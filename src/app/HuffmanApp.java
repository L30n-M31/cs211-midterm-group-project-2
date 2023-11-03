package app;

import gui.GUI;
import gui.MainPanel;
import gui.SidePanel;
import util.HuffmanCodec;

import javax.swing.*;

public class HuffmanApp {
    private final HuffmanCodec huffmanOperations = new HuffmanCodec();

    public static void main(String[] args) {
        HuffmanApp program;
        try {
            program = new HuffmanApp();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error detected");
        }
    }

    public void run() {
        SidePanel sidePanel = new SidePanel(huffmanOperations);
        MainPanel mainPanel = new MainPanel(huffmanOperations);

        GUI frame = new GUI();
        frame.add(sidePanel);
        frame.add(mainPanel);
    }
}
