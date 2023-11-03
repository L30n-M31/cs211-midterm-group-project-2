package gui;

import util.BinaryEncoder;
import util.HuffmanCodec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class MainPanel extends JPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private final BinaryEncoder binaryEncoder = new BinaryEncoder();
    private final HuffmanCodec huffmanOperations;
    private JLabel inputLB, outputLB, compressionLB, decimalPoint, lengthLB, binaryLB, huffmanLB, bit1, bit2, horizontalLine;
    private JTextArea huffTreeDiagram, inputTA, outputTA;
    private JTextField digit1, digit2, decimal1, decimal2, binaryLength, huffmanLength;
    private JButton encodeOrDecodeBT, viewDiagramBT;

    public MainPanel(HuffmanCodec huffmanOperations) {
        this.huffmanOperations = huffmanOperations;
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
        addActionListeners();
    }

    private void initializeComponents() {
        // Label
        inputLB = new JLabel("INPUT");
        outputLB = new JLabel("OUTPUT");
        lengthLB = new JLabel("BIT LENGTH");
        binaryLB = new JLabel("BINARY:");
        huffmanLB = new JLabel("HUFFMAN:");
        bit1 = new JLabel("Bits");
        bit2 = new JLabel("Bits");
        horizontalLine = new JLabel("│");
        compressionLB = new JLabel("COMPRESSION PERCENTAGE");
        decimalPoint = new JLabel(".");

        // Button
        encodeOrDecodeBT = new JButton("ENCODE / DECODE");
        viewDiagramBT = new JButton("<html><div style = 'text-align: center;'>" +
                "VIEW TREE\n" +
                "DIAGRAM" +
                "</div></html>");

        // Text Field
        binaryLength = new JTextField();
        huffmanLength = new JTextField();
        digit1 = new JTextField();
        digit2 = new JTextField();
        decimal1 = new JTextField();
        decimal2 = new JTextField();

        // Text Area
        inputTA = new JTextArea();
        outputTA = new JTextArea();
        huffmanOperations.printTree(huffmanOperations.getRoot(), "", true);
        huffTreeDiagram = new JTextArea(huffmanOperations.getTreeOutput().toString());
    }

    private void setBounds() {
        // Label
        inputLB.setBounds(25, 5, 500, 50);
        outputLB.setBounds(565, 5, 300, 50);
        lengthLB.setBounds(25, 430, 500, 50);
        binaryLB.setBounds(25, 500, 100, 50);
        huffmanLB.setBounds(285, 500, 100, 50);
        bit1.setBounds(210, 500, 100, 50);
        bit2.setBounds(480, 500, 100, 50);
        horizontalLine.setBounds(260, 500, 20, 50);
        compressionLB.setBounds(25, 570, 500, 50);
        decimalPoint.setBounds(250, 640, 50, 100);

        // Button
        encodeOrDecodeBT.setBounds(25, 365, 290, 50);
        viewDiagramBT.setBounds(335, 365, 190, 50);

        // Text Field
        binaryLength.setBounds(105, 500, 100, 50);
        huffmanLength.setBounds(375, 500, 100, 50);
        digit1.setBounds(25, 630, 100, 100);
        digit2.setBounds(145, 630, 100, 100);
        decimal1.setBounds(305, 630, 100, 100);
        decimal2.setBounds(425, 630, 100, 100);

        this.setBounds(300, 0, 885, 800);
    }

    private void setGraphics() {
        // Label
        edit.setMainPanelHeadingGraphic(inputLB);
        edit.setMainPanelHeadingGraphic(outputLB);
        edit.setMainPanelHeadingGraphic(lengthLB);
        edit.setBitLabelGraphic(binaryLB);
        edit.setBitLabelGraphic(huffmanLB);
        edit.setBitLabelGraphic(bit1);
        edit.setBitLabelGraphic(bit2);
        edit.setMainPanelHeadingGraphic(horizontalLine);
        edit.setMainPanelHeadingGraphic(compressionLB);
        edit.setDecimalPointGraphic(decimalPoint);

        // Text Field
        edit.setBitTextFieldGraphic(binaryLength);
        edit.setBitTextFieldGraphic(huffmanLength);
        edit.setTextFieldGraphic(digit1);
        edit.setTextFieldGraphic(digit2);
        edit.setTextFieldGraphic(decimal1);
        edit.setTextFieldGraphic(decimal2);

        // Button
        edit.setButtonGraphic(encodeOrDecodeBT);
        edit.setButtonGraphic(viewDiagramBT);

        edit.setMainPanelGraphic(this);
    }

    private void addComponentsToPanel() {
        // Label
        this.add(inputLB);
        this.add(outputLB);
        this.add(lengthLB);
        this.add(binaryLB);
        this.add(huffmanLB);
        this.add(bit1);
        this.add(bit2);
        this.add(horizontalLine);
        this.add(compressionLB);
        this.add(decimalPoint);

        // Button
        this.add(encodeOrDecodeBT);
        this.add(viewDiagramBT);

        // Text Field
        this.add(binaryLength);
        this.add(huffmanLength);
        this.add(digit1);
        this.add(digit2);
        this.add(decimal1);
        this.add(decimal2);

        // Text Area
        this.add(edit.setInputTextArea(inputTA));
        this.add(edit.setOutputTextArea(outputTA));
    }

    private void addActionListeners() {
        viewDiagramBT.addActionListener(new ButtonAction());
        encodeOrDecodeBT.addActionListener(new ButtonAction());
    }

    class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == encodeOrDecodeBT) {
                if (Pattern.compile(".*[a-zA-Z].*", Pattern.DOTALL).matcher(inputTA.getText()).matches()) {
                    String binaryCode = binaryEncoder.convertToBinaryASCII(inputTA.getText());
                    String encodedText = huffmanOperations.convertToHuffmanCode(inputTA.getText());
                    double value = huffmanOperations.computeSavings(binaryCode, encodedText);


                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String percentage = decimalFormat.format(value);

                    outputTA.setText(
                            "Binary Code: " +
                            "===================================" +  "\n" +
                            binaryCode + "\n\n\n\n" +
                            "Huffman Code: " +
                            "===================================" + "\n" +
                            encodedText);

                    binaryLength.setText(String.valueOf(binaryCode.length()));
                    huffmanLength.setText(String.valueOf(encodedText.length()));
                    digit1.setText(String.valueOf(percentage.charAt(0)));
                    digit2.setText(String.valueOf(percentage.charAt(1)));
                    decimal1.setText(String.valueOf(percentage.charAt(3)));
                    decimal2.setText(String.valueOf(percentage.charAt(4)));

                } else {
                    String decode = huffmanOperations.convertToText(inputTA.getText());
                    outputTA.setText(decode);
                }
            }
            if (e.getSource() == viewDiagramBT) {
                JOptionPane.showMessageDialog(null, edit.setHuffTreeDiagram(huffTreeDiagram), "HUFFMAN TREE DIAGRAM", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
