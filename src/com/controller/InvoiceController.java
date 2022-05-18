package com.controller;

import com.model.InvoiceHeader_Data;
import com.model.InvoiceHeader_TableModel;
import com.model.InvoiceLine_Data;
import com.model.InvoiceLine_TableModel;
import com.view.Invoice_Dialog;
import com.view.Invoice_Frame;
import com.view.Line_Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InvoiceController implements ActionListener, ListSelectionListener {

    private Invoice_Frame frame;
    private Invoice_Dialog invoiceDialog;
    private Line_Dialog lineDialog;

    public InvoiceController(Invoice_Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionValue = e.getActionCommand();
        switch (actionValue) {
            case "Load File":
                loadFile();
                break;

            case "Save File":
                saveFile();
                break;

            case "Create New Invoice":
                createNewInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Create New Item":
                createNewItem();
                break;

            case "Delete Item":
                deleteItem();
                break;

            case "Create Invoice":
                createInvoice();
                break;

            case "Cancel Invoice":
                cancelInvoice();
                break;

            case "Create Line":
                createLine();
                break;

            case "Cancel Line":
                cancelLine();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = frame.getInvoiceTable().getSelectedRow();
        if (row != -1) {
            InvoiceHeader_Data currentInvoiceData = frame.getInvoices().get(row);
            frame.getInvoiceNumberValue().setText("" + currentInvoiceData.getNumber());
            frame.getInvoiceDateValue().setText(currentInvoiceData.getDate());
            frame.getCustomerNameValue().setText(currentInvoiceData.getCustomerName());
            frame.getInvoiceTotalValue().setText("" + currentInvoiceData.getInvoiceTotal());
            InvoiceLine_TableModel lineTableModel = new InvoiceLine_TableModel(currentInvoiceData.getLines());
            frame.getLineTable().setModel(lineTableModel);
            lineTableModel.fireTableDataChanged();
        }
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File invoiceHeaderFile = fileChooser.getSelectedFile();
                Path invoiceHeaderPath = Paths.get(invoiceHeaderFile.getAbsolutePath());
                List<String> invoiceHeaderLines = Files.readAllLines(invoiceHeaderPath);
                ArrayList<InvoiceHeader_Data> invoices = new ArrayList<>();
                for (String invoiceHeaderLine : invoiceHeaderLines) {
                    String[] invData = invoiceHeaderLine.split(",");
                    int invoiceNumber = Integer.parseInt(invData[0]);
                    String invoiceDate = invData[1];
                    String invoiceCustomer = invData[2];

                    InvoiceHeader_Data invoice = new InvoiceHeader_Data(invoiceNumber, invoiceDate, invoiceCustomer);
                    invoices.add(invoice);
                }
                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineHeaderFile = fileChooser.getSelectedFile();
                    Path lineHeaderPath = Paths.get(lineHeaderFile.getAbsolutePath());
                    List<String> lineHeaderLines = Files.readAllLines(lineHeaderPath);
                    for (String lineHeaderLine : lineHeaderLines) {
                        String[] lineData = lineHeaderLine.split(",");
                        int num = Integer.parseInt(lineData[0]);
                        String item = lineData[1];
                        double price = Double.parseDouble(lineData[2]);
                        int count = Integer.parseInt(lineData[3]);
                        InvoiceHeader_Data inv = null;
                        for (InvoiceHeader_Data invoice : invoices) {
                            if (num == invoice.getNumber()) {
                                inv = invoice;
                                break;
                            }
                        }
                        InvoiceLine_Data line = new InvoiceLine_Data(item, price, count, inv);
                        inv.getLines().add(line);
                    }

                }
                frame.setInvoices(invoices);
                InvoiceHeader_TableModel invoicesTableModel = new InvoiceHeader_TableModel(invoices);
                frame.setInvoicesTableModel(invoicesTableModel);
                frame.getInvoiceTable().setModel(invoicesTableModel);
                frame.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File invoiceHeaderFile = fileChooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(invoiceHeaderFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                String trial = "";
                writer.write(trial);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void createNewInvoice() {
        invoiceDialog = new Invoice_Dialog(frame);
        invoiceDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int row = frame.getInvoiceTable().getSelectedRow();
        if (row != -1) {
            frame.getInvoices().remove(row);
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    private void createNewItem() {
        lineDialog = new Line_Dialog(frame);
        lineDialog.setVisible(true);
    }

    private void deleteItem() {
        int row = frame.getLineTable().getSelectedRow();
        if (row != -1) {
            InvoiceLine_TableModel lineTableModel = (InvoiceLine_TableModel) frame.getLineTable().getModel();
            lineTableModel.getLineData().remove(row);
            lineTableModel.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
        }
    }

    private void createInvoice() {
        String dateText = invoiceDialog.getDateTextField().getText();
        String customerNameText = invoiceDialog.getCustomerNameTextField().getText();
        int invoiceNumber = frame.getNextInvoiceNumber();

        InvoiceHeader_Data createNewInvoice = new InvoiceHeader_Data(invoiceNumber, dateText, customerNameText);
        frame.getInvoices().add(createNewInvoice);
        frame.getInvoicesTableModel().fireTableDataChanged();

        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    private void cancelInvoice() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    private void createLine() {
        String itemName = lineDialog.getItemNameTextField().getText();
        double itemPrice = Double.parseDouble(lineDialog.getItemPriceTextField().getText());
        int itemCount = Integer.parseInt(lineDialog.getCountTextField().getText());

        int selectedInvoiceRow = frame.getInvoiceTable().getSelectedRow();
        if (selectedInvoiceRow != -1) {
            InvoiceHeader_Data invoice = frame.getInvoices().get(selectedInvoiceRow);
            InvoiceLine_Data createNewLine = new InvoiceLine_Data(itemName, itemPrice, itemCount, invoice);
            invoice.getLines().add(createNewLine);

            InvoiceLine_TableModel lineTableModel = (InvoiceLine_TableModel) frame.getLineTable().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
        }

        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void cancelLine() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

}
