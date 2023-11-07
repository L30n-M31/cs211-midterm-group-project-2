package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GraphicEditor {
    private final Color babyBlue = new Color(206, 230, 242);
    private final Color salmonOrange = new Color(233, 183, 150);
    private final Color peach = new Color(227, 134, 125);
    private final Color rubyRed = new Color(150, 46, 42);

    //-------------------------------------------------< Panels >-------------------------------------------------------
    public void setSidePanelGraphic(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(rubyRed);
    } // end of setSidePanelGraphic

    public void setMainPanelGraphic(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(peach);
    } // end of setMainPanelGraphic

    //--------------------------------------------< Title & Heading >---------------------------------------------------
    public void setTitleFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 36));
        label.setForeground(babyBlue);
    } // end of setTitleFormat

    public void setHeadingFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(rubyRed);
    } // end of setHeadingFormat

    //------------------------------------------------< Labels >--------------------------------------------------------
    public void setTableLabelFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.ITALIC, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(babyBlue);
    } // end of setTableLabelFormat

    public void setBitLabelFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        label.setForeground(rubyRed);
    } // end of setBitLabelFormat

    public void setDecimalPointFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(rubyRed);
    } // end of setDecimalPointFormat

    //-----------------------------------------------< Text Fields >----------------------------------------------------
    public void setCompressionTextFieldGraphic(JTextField textField) {
        textField.setFont(new Font("Monospaced", Font.ITALIC, 50));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEnabled(false);
        textField.setBackground(babyBlue);
        textField.setDisabledTextColor(rubyRed);
    } // end of setCompressionTextFieldGraphic

    public void setBitTextFieldGraphic(JTextField textField) {
        textField.setFont(new Font("Monospaced", Font.ITALIC, 24));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEnabled(false);
        textField.setBackground(babyBlue);
        textField.setDisabledTextColor(rubyRed);
    } // end of setBitTextFieldGraphic

    //-------------------------------------------------< Buttons >------------------------------------------------------
    public void setButtonGraphic(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setBackground(babyBlue);
        button.setForeground(rubyRed);
    }

    //--------------------------------------------------< Table >-------------------------------------------------------
    public void setTableFormat(JTable table, JScrollPane scrollPane) {
        table.setOpaque(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(center);
        table.getColumnModel().getColumn(1).setCellRenderer(center);

        setTableGraphic(table, scrollPane);
    } // end of setTableFormat

    private void setTableGraphic(JTable table, JScrollPane scrollPane) {
        table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
        table.setFont(new Font("Monospaced", Font.PLAIN, 18));
        table.setBackground(babyBlue);
        table.setForeground(rubyRed);
        table.getTableHeader().setBackground(peach);
        table.getTableHeader().setForeground(rubyRed);
        scrollPane.getViewport().setBackground(peach);
        scrollPane.getVerticalScrollBar().setBackground(peach);
    } // end of setTableGraphic

    //------------------------------------------------< Scroll Panes >--------------------------------------------------
    public JScrollPane setInputTextAreaFormat(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(25, 65, 500, 290);

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(true);
        textArea.setBackground(salmonOrange);
        textArea.setForeground(rubyRed);

        return scrollPane;
    } // end of setInputTextAreaFormat

    public JScrollPane setOutputTextAreaFormat(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(560, 65, 300, 665);

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        textArea.setBackground(salmonOrange);
        textArea.setDisabledTextColor(rubyRed);

        return scrollPane;
    } // end of setOutputTextAreaFormat

    public JScrollPane setHuffTreeDiagramFormat(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(480, 600));

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(salmonOrange);
        textArea.setForeground(rubyRed);

        return scrollPane;
    } // end of setHuffTreeDiagramFormat
} // end of GraphicEditor class
