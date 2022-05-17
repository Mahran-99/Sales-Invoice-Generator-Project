package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceHeader_TableModel extends AbstractTableModel {

    private ArrayList<InvoiceHeader_Data> invoicesData;
    private String[] invoiceHeaderColumns = {"No.", "Date", "Customer", "Total"};

    public InvoiceHeader_TableModel(ArrayList<InvoiceHeader_Data> invoicesData) {
        this.invoicesData = invoicesData;
    }

    @Override
    public int getRowCount() {
        return invoicesData.size();
    }

    @Override
    public int getColumnCount() {
        return invoiceHeaderColumns.length;
    }

    @Override
    public String getColumnName(int column) {
        return invoiceHeaderColumns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return invoicesData.get(rowIndex).getNumber();
            case 1:
                return invoicesData.get(rowIndex).getDate();
            case 2:
                return invoicesData.get(rowIndex).getCustomerName();
            case 3:
                return invoicesData.get(rowIndex).getInvoiceTotal();
            default:
                return "";
        }
    }

}
