package gui;

import util.BinaryEncoder;
import util.CharacterFrequencyCounter;
import util.HuffmanCodec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MainPanel extends JPanel {
    private final GraphicEditor edit = new GraphicEditor();
    private final CharacterFrequencyCounter function = new CharacterFrequencyCounter();
    private final BinaryEncoder binaryEncoder = new BinaryEncoder();
    private final HuffmanCodec huffmanCodec;
    private JLabel inputLB, outputLB, compressionLB, decimalPoint, lengthLB, binaryLB, huffmanLB, bit1, bit2, horizontalLine;
    private JTextField digit1, digit2, decimal1, decimal2, binaryLength, huffmanLength;
    private JTextArea huffTreeDiagram, inputTA, outputTA;
    private JButton encodeOrDecodeBT, viewDiagramBT;
    private final File file;

    public MainPanel(HuffmanCodec huffmanCodec, File file) {
        this.huffmanCodec = huffmanCodec;
        this.file = file;
        initializeComponents();
        setBounds();
        setGraphics();
        addComponentsToPanel();
        addActionListeners();
    } // end of constructor

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
        huffmanCodec.printTree(huffmanCodec.getRoot(), "", true);
        huffTreeDiagram = new JTextArea(huffmanCodec.getTreeOutput().toString());
    } // end of initializeComponents

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
    } // end of setBounds

    private void setGraphics() {
        // Label
        edit.setHeadingFormat(inputLB);
        edit.setHeadingFormat(outputLB);
        edit.setHeadingFormat(lengthLB);
        edit.setBitLabelFormat(binaryLB);
        edit.setBitLabelFormat(huffmanLB);
        edit.setBitLabelFormat(bit1);
        edit.setBitLabelFormat(bit2);
        edit.setHeadingFormat(horizontalLine);
        edit.setHeadingFormat(compressionLB);
        edit.setDecimalPointFormat(decimalPoint);

        // Text Field
        edit.setBitTextFieldGraphic(binaryLength);
        edit.setBitTextFieldGraphic(huffmanLength);
        edit.setCompressionTextFieldGraphic(digit1);
        edit.setCompressionTextFieldGraphic(digit2);
        edit.setCompressionTextFieldGraphic(decimal1);
        edit.setCompressionTextFieldGraphic(decimal2);

        // Button
        edit.setButtonGraphic(encodeOrDecodeBT);
        edit.setButtonGraphic(viewDiagramBT);

        edit.setMainPanelGraphic(this);
    } // end of setGraphics

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
        this.add(edit.setInputTextAreaFormat(inputTA));
        this.add(edit.setOutputTextAreaFormat(outputTA));
    } // end of addComponentsToPanel

    private void addActionListeners() {
        viewDiagramBT.addActionListener(new ButtonAction());
        encodeOrDecodeBT.addActionListener(new ButtonAction());
    } // end of addActionListeners

    private class ButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == encodeOrDecodeBT) {
                String acceptedCharacters = Arrays.toString(function.getCharacterSet(file));
                if (Pattern.compile("[" + Pattern.quote(acceptedCharacters) + "]+").matcher(inputTA.getText()).matches()) {
                    String binaryCode = binaryEncoder.convertToBinaryASCII(inputTA.getText());
                    String encodedText = huffmanCodec.convertToHuffmanCode(inputTA.getText());
                    double value = huffmanCodec.computeSavings(binaryCode, encodedText);

                    DecimalFormat decimalFormat = new DecimalFormat("00.00");
                    String percentage = decimalFormat.format(value);

                    outputTA.setText(
                            "Binary Code: " +
                                    "===================================" +  "\n" +
                                    binaryCode + "\n\n\n\n" +
                                    "Huffman Code: " +
                                    "===================================" + "\n" +
                                    encodedText);

                    setTextFields(String.valueOf(binaryCode.length()),
                            String.valueOf(encodedText.length()),
                            String.valueOf(percentage.charAt(0)),
                            String.valueOf(percentage.charAt(1)),
                            String.valueOf(percentage.charAt(3)),
                            String.valueOf(percentage.charAt(4)));

                } else if (inputTA.getText().matches("[01]+")){
                    String decode = huffmanCodec.convertToText(inputTA.getText());
                    outputTA.setText(decode);

                    setTextFields("", "", "", "", "", "");
                }
                else {
                    JOptionPane.showMessageDialog(null, """
                        Invalid character or digit detected
                        
                        conversion process aborted...
                        """);
                }
            }
            if (e.getSource() == viewDiagramBT) {
                JOptionPane.showMessageDialog(null, edit.setHuffTreeDiagramFormat(huffTreeDiagram), "HUFFMAN TREE DIAGRAM", JOptionPane.PLAIN_MESSAGE);
            }
        } // end of actionPerformed

        public void setTextFields(String one, String two, String three, String four, String five, String six) {
            binaryLength.setText(one);
            huffmanLength.setText(two);
            digit1.setText(three);
            digit2.setText(four);
            decimal1.setText(five);
            decimal2.setText(six);
        } // end of setTextFields
    } // end of ButtonAction class
} // end of MainPanel class
