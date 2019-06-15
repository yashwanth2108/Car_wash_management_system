/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.model;

/**
 *
 * @author USER
 */
public class Item {
    private String itemid;
    private String category;
    private String purchasedDate;
    private double unitPrice;
    private double discountrate;
    private String name;
    private int quantity;
    private double cost;

    public Item() {
    }

    public Item(String itemid, String category, String purchasedDate, double unitPrice, double discountrate, String name, int quantity, double cost) {
        this.itemid = itemid;
        this.category = category;
        this.purchasedDate = purchasedDate;
        this.unitPrice = unitPrice;
        this.discountrate = discountrate;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(double discountrate) {
        this.discountrate = discountrate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
