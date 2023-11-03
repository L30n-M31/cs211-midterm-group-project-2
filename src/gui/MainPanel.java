package gui;

import util.HuffmanCodec;

import javax.swing.*;

public class RightPanel extends JPanel {
    private GraphicEditor edit = new GraphicEditor();
    private HuffmanCodec huffmanOperations;
    private JTextArea huffTreeDiagram;
    private JScrollPane scrollPane;

    public RightPanel(HuffmanCodec huffmanOperations) {
        this.huffmanOperations = huffmanOperations;
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
    }

    private void initializeComponents() {
        huffmanOperations.printTree(huffmanOperations.getRoot(), "", true);
        huffTreeDiagram = new JTextArea(huffmanOperations.getTreeOutput().toString(), 100, 40);
    }

    private void setBounds() {
        this.setBounds(684, 0, 500, 800);
    }

    private void setGraphics() {
        edit.setRightPanelGraphic(this);
    }

    private void addComponentsToPanel() {
        setHuffTreeDiagram();
    }

    private void setHuffTreeDiagram() {
        huffTreeDiagram.setLineWrap(false);
        huffTreeDiagram.setWrapStyleWord(true);
        huffTreeDiagram.setEditable(false);

        scrollPane = new JScrollPane(huffTreeDiagram);


        edit.setHuffTreeDiagramFormat(huffTreeDiagram, scrollPane);

        this.add(scrollPane);
    }
}
