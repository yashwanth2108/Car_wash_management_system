/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.model;

/**
 *
 * @author Rasika
 */
public class Directsale {

    private String custId;
    private String custname;
    private String paymenttype;
    private String itemid;
    private String itemname;
    private int qty;
    private double total;
    private double discount;

    public Directsale(String custId, String custname, String paymenttype, String itemid, String itemname, int qty, double total, double discount) {
        this.custId = custId;
        this.custname = custname;
        this.paymenttype = paymenttype;
        this.itemid = itemid;
        this.itemname = itemname;
        this.qty = qty;
        this.total = total;
        this.discount = discount;
    }

    public Directsale() {
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    

    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
