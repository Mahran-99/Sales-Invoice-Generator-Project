package com.controller;

import com.model.InvoiceHeader_Data;
import com.model.InvoiceHeader_TableModel;
import com.model.InvoiceLine_Data;
import com.model.InvoiceLine_TableModel;
import com.view.Invoice_Frame;
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

public class Controller implements ActionListener, ListSelectionListener {

    private Invoice_Frame frame;

    public Controller(Invoice_Frame frame) {
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
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
        InvoiceHeader_Data currentInvoiceData = frame.getInvoices().get(selectedRow);
        frame.getInvoiceNumberValue().setText("" + currentInvoiceData.getNumber());
        frame.getInvoiceDateValue().setText(currentInvoiceData.getDate());
        frame.getCustomerNameValue().setText(currentInvoiceData.getCustomerName());
        frame.getInvoiceTotalValue().setText("" + currentInvoiceData.getInvoiceTotal());
        InvoiceLine_TableModel lineTableModel = new InvoiceLine_TableModel(currentInvoiceData.getLines());
        frame.getLineTable().setModel(lineTableModel);
        lineTableModel.fireTableDataChanged();
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
                String trial = "4,15-5-2022,omar";
                writer.write(trial);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void createNewInvoice() {

    }

    private void deleteInvoice() {

    }

    private void createNewItem() {

    }

    private void deleteItem() {

    }

}
