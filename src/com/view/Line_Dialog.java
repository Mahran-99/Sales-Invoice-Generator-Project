package com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Line_Dialog extends JDialog {

    private JLabel itemNameLabel;
    private JTextField itemNameTextField;
    private JLabel itemPriceLabel;
    private JTextField itemPriceTextField;
    private JLabel countLabel;
    private JTextField countTextField;
    private JButton createLineButton;
    private JButton cancelLineButton;

    public Line_Dialog(Invoice_Frame frame) {
        itemNameLabel = new JLabel("Item Name: ");
        itemNameTextField = new JTextField(15);
        itemPriceLabel = new JLabel("Item Price: ");
        itemPriceTextField = new JTextField(15);
        countLabel = new JLabel("Count: ");
        countTextField = new JTextField(15);

        createLineButton = new JButton("Create");
        cancelLineButton = new JButton("Cancel");

        createLineButton.setActionCommand("Create Line");
        cancelLineButton.setActionCommand("Cancel Line");

        createLineButton.addActionListener(frame.getController());
        cancelLineButton.addActionListener(frame.getController());

        setLayout(new GridLayout(4, 2));

        add(itemNameLabel);
        add(itemNameTextField);
        add(itemPriceLabel);
        add(itemPriceTextField);
        add(countLabel);
        add(countTextField);
        add(createLineButton);
        add(cancelLineButton);

        pack();
    }

    public JTextField getItemNameTextField() {
        return itemNameTextField;
    }

    public JTextField getItemPriceTextField() {
        return itemPriceTextField;
    }

    public JTextField getCountTextField() {
        return countTextField;
    }

}
