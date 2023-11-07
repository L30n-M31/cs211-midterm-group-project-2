package gui;

import node.TreeNode;
import util.CharacterFrequencyCounter;
import util.HuffmanCodec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SidePanel extends JPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private final CharacterFrequencyCounter function = new CharacterFrequencyCounter();
    private final HuffmanCodec huffmanCodec;
    private JLabel title, tableTitle;
    private JTable table;
    private JScrollPane scrollPane;
    private final File file;

    public SidePanel(HuffmanCodec huffmanCodec, File file) {
        this.huffmanCodec = huffmanCodec;
        this.file = file;
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
    } // end of constructor

    private void initializeComponents() {
        title = new JLabel("<html><div style = 'text-align: center;'>" +
                "HUFFMAN<br>" +
                "APPLICATION" +
                "</div></html>");
        tableTitle = new JLabel("HUFFMAN TABLE");

        table = new JTable();
        scrollPane = new JScrollPane(table);
        setTable();
    } // end of initializeComponents

    private void setBounds() {
        title.setBounds(30, 5, 280, 100);
        tableTitle.setBounds(0, 705, 300, 50);
        scrollPane.setBounds(10, 140, 280, 570);
        table.setSize(280, 570);

        this.setBounds(0, 0, 300, 800);
    } // end of setBounds

    private void setGraphics() {
        edit.setTitleFormat(title);
        edit.setTableLabelFormat(tableTitle);
        edit.setTableFormat(table, scrollPane);
        edit.setSidePanelGraphic(this);
    } // end of setGraphics

    private void addComponentsToPanel() {
        this.add(title);
        this.add(tableTitle);
        this.add(scrollPane);
    } // end of addComponentsToPanel

    private void setTable() {
        char[] characterSet = function.getCharacterSet(file);
        int[] frequencies = function.determineFrequency(characterSet, file);

        huffmanCodec.createHuffmanTree(characterSet, frequencies);
        TreeNode root = huffmanCodec.getRoot();
        huffmanCodec.initializeHuffmanTable(root, "");
        HashMap<Character, String> data = huffmanCodec.getHuffmanCodeTable();

        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("Char");
        tableModel.addColumn("Freq");
        tableModel.addColumn("Code");

        for (Map.Entry<Character, String> entry : data.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            int frequency = 0;
            for (int i = 0; i < characterSet.length; i++) {
                if (characterSet[i] == character) {
                    frequency = frequencies[i];
                    break;
                }
            }

            if (character == ' ') {
                tableModel.addRow(new Object[]{"Space", frequency, code});
            } else if (character == '\n') {
                tableModel.addRow(new Object[]{"Newline", frequency, code});
            } else {
                tableModel.addRow(new Object[]{character, frequency, code});
            }
        }

        table.setModel(tableModel);
    } // end of setTable
} // end of SidePanel class
