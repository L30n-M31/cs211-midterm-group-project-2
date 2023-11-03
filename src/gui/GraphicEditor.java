package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GraphicEditor {
    private final Color babyBlue = new Color(206, 230, 242);
    private final Color salmonOrange = new Color(233, 183, 150);
    private final Color peach = new Color(227, 134, 125);
    private final Color rubyRed = new Color(150, 46, 42);

    public void setTitleFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 36));
        label.setForeground(babyBlue);
    }

    public void setTableLabelFormat(JLabel label) {
        label.setFont(new Font("Monospaced", Font.ITALIC, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(babyBlue);
    }

    public void setSidePanelGraphic(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(rubyRed);
    } // end of setSidePanelGraphic

    public void setMainPanelGraphic(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(peach);
    }

    public void setMainPanelHeadingGraphic(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(rubyRed);
    }

    public void setButtonGraphic(JButton button) {
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setBackground(babyBlue);
        button.setForeground(rubyRed);
    }

    public void setTextFieldGraphic(JTextField textField) {
        textField.setFont(new Font("Monospaced", Font.ITALIC, 50));
        textField.setEnabled(false);

        textField.setHorizontalAlignment(SwingConstants.CENTER);

        textField.setBackground(babyBlue);
        textField.setDisabledTextColor(rubyRed);
    }

    public void setBitLabelGraphic(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        label.setForeground(rubyRed);
    }

    public void setBitTextFieldGraphic(JTextField textField) {
        textField.setEnabled(false);
        textField.setFont(new Font("Monospaced", Font.ITALIC, 24));
        textField.setHorizontalAlignment(SwingConstants.CENTER);

        textField.setBackground(babyBlue);
        textField.setDisabledTextColor(rubyRed);
    }

    public void setDecimalPointGraphic(JLabel label) {
        label.setFont(new Font("Monospaced", Font.BOLD, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(rubyRed);
    }

    public JScrollPane setInputTextArea(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        textArea.setBackground(salmonOrange);
        textArea.setForeground(rubyRed);
        scrollPane.setBounds(25, 65, 500, 290);

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        return scrollPane;
    }

    public JScrollPane setOutputTextArea(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        scrollPane.setBounds(560, 65, 300, 665);

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(salmonOrange);
        textArea.setDisabledTextColor(rubyRed);
        return scrollPane;
    }

    public void setTableFormat(JTable table, JScrollPane scrollPane) {
        table.setOpaque(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        scrollPane.setBounds(10, 140, 280, 570);
        table.setSize(280, 570);

        table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
        table.setFont(new Font("Monospaced", Font.PLAIN, 18));
        table.setBackground(babyBlue);
        table.setForeground(rubyRed);
        table.getTableHeader().setBackground(peach);
        table.getTableHeader().setForeground(rubyRed);
        scrollPane.getViewport().setBackground(peach);
        scrollPane.getVerticalScrollBar().setBackground(peach);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    } // end of setTableFormat method

    public JScrollPane setHuffTreeDiagram(JTextArea textArea) {
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(480, 600));

        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(salmonOrange);
        textArea.setForeground(rubyRed);

        return scrollPane;
    }
}
