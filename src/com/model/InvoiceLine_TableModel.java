package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceLine_TableModel extends AbstractTableModel {
    
    private ArrayList<InvoiceLine_Data> lineData;
    private String[] invoiceLineColumns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public InvoiceLine_TableModel(ArrayList<InvoiceLine_Data> lineData) {
        this.lineData = lineData;
    }

    @Override
    public int getRowCount() {
        return lineData.size();
    }

    @Override
    public int getColumnCount() {
        return invoiceLineColumns.length;
    }
    
    @Override
    public String getColumnName(int column){
        return invoiceLineColumns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return lineData.get(rowIndex).getInvoice().getNumber();
            case 1:
                return lineData.get(rowIndex).getItem();
            case 2:
                return lineData.get(rowIndex).getPrice();
            case 3:
                return lineData.get(rowIndex).getCount();
            case 4:
                return lineData.get(rowIndex).getInvoiceLineTotal();
            default:
                return "";
        }
    }
    
}
