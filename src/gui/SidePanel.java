package gui;

import node.TreeNode;
import util.Functions;
import util.HuffmanCodec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class SidePanel extends JPanel {
    private final Functions functions = new Functions();
    private final HuffmanCodec huffmanOperations;
    private final GraphicEditor edit = new GraphicEditor();

    private JLabel title, tableTitle;

    public SidePanel(HuffmanCodec huffmanOperations) {
        this.huffmanOperations = huffmanOperations;
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
    }

    private void initializeComponents() {
        title = new JLabel("<html><div style = 'text-align: center;'>" +
                "HUFFMAN<br>" +
                "APPLICATION" +
                "</div></html>");
        tableTitle = new JLabel("HUFFMAN TABLE");
    }

    private void setBounds() {
        title.setBounds(30, 5, 280, 100);
        tableTitle.setBounds(0, 705, 300, 50);
        this.setBounds(0, 0, 300, 800);
    }

    private void setGraphics() {
        edit.setTitleFormat(title);
        edit.setTableLabelFormat(tableTitle);
        edit.setSidePanelGraphic(this);
    }

    private void addComponentsToPanel() {
        this.add(title);
        this.add(tableTitle);
        setTable();
    }

    private void setTable() {
        char[] characters = functions.getCharacters();
        String filename = "res/character-set.txt";
        int[] frequencies = functions.determineFrequency(characters, filename);

        huffmanOperations.createHuffmanTree(characters, frequencies);
        TreeNode root = huffmanOperations.getRoot();
        huffmanOperations.initializeHuffmanTable(root, "");
        HashMap<Character, String> data = huffmanOperations.getTable();

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("Characters");
        tableModel.addColumn("Frequencies");

        for (Map.Entry<Character, String> entry : data.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            if (character == ' ') {
                tableModel.addRow(new Object[]{"Space", code});
            } else if (character == '\n') {
                tableModel.addRow(new Object[]{"Newline", code});
            } else {
                tableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        edit.setTableFormat(table, scrollPane);

        this.add(scrollPane);
    }
} // end of SidePanel class
