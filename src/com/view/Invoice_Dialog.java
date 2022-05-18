package com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Invoice_Dialog extends JDialog {

    private JLabel dateLabel;
    private JTextField dateTextField;
    private JLabel customerNameLabel;
    private JTextField customerNameTextField;
    private JButton createInvoiceButton;
    private JButton cancelInvoiceButton;

    public Invoice_Dialog(Invoice_Frame frame) {
        dateLabel = new JLabel("Invoice Date: ");
        dateTextField = new JTextField(15);
        customerNameLabel = new JLabel("Customer Name: ");
        customerNameTextField = new JTextField(15);

        createInvoiceButton = new JButton("Create");
        cancelInvoiceButton = new JButton("Cancel");

        createInvoiceButton.setActionCommand("Create Invoice");
        cancelInvoiceButton.setActionCommand("Cancel Invoice");

        createInvoiceButton.addActionListener(frame.getController());
        cancelInvoiceButton.addActionListener(frame.getController());

        setLayout(new GridLayout(3, 2));

        add(dateLabel);
        add(dateTextField);
        add(customerNameLabel);
        add(customerNameTextField);
        add(createInvoiceButton);
        add(cancelInvoiceButton);

        pack();
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getCustomerNameTextField() {
        return customerNameTextField;
    }

}
