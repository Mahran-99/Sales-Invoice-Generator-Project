package com.model;

public class InvoiceLine_Data {

    private String item;
    private double price;
    private int count;
    private InvoiceHeader_Data invoice;

    public InvoiceLine_Data() {
    }

    public InvoiceLine_Data( String item, double price, int count, InvoiceHeader_Data invoice) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader_Data getInvoice() {
        return invoice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getInvoiceLineTotal() {
        return price * count;
    }

    @Override
    public String toString() {
        return "InvoiceLine_Data{" + "number=" + invoice.getNumber() + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

}
