package com.model;

import java.util.ArrayList;

public class InvoiceHeader_Data {

    private int number;
    private String date;
    private String customerName;
    private ArrayList<InvoiceLine_Data> lines;

    public InvoiceHeader_Data() {
    }

    public InvoiceHeader_Data(int number, String date, String customerName) {
        this.number = number;
        this.date = date;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine_Data> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (InvoiceLine_Data line : getLines()) {
            total += line.getInvoiceLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "InvoiceHeader_Data{" + "number=" + number + ", date=" + date + ", customerName=" + customerName + '}';
    }

}
