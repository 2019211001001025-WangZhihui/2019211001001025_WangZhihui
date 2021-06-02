package com.wangzhihui.model;

import java.io.Serializable;

public class Item implements Serializable {
    private Product product;
    private  int qunatity;
    private int quantity;

    public  Item(){
        super();
    }
    public Item(Product product, int qunatity) {
        this.product = product;
        this.qunatity = qunatity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", qunatity=" + qunatity +
                '}';
    }


    public int getQunatity() {
        return qunatity;
    }

    public void setQuantity(int quantity) {
        this.qunatity = quantity;
    }
}
