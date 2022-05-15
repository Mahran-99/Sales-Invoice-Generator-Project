package com.model;

public class InvoiceLine_Data {
    private int number;
    private String item;
    private double price;
    private int count;
    private InvoiceHeader_Data invoice;

    public InvoiceLine_Data() {
    }

    public InvoiceLine_Data(int number, String item, double price, int count, InvoiceHeader_Data invoice) {
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "InvoiceLine_Data{" + "number=" + number + ", item=" + item + ", price=" + price + ", count=" + count + '}';
    }

   
}
