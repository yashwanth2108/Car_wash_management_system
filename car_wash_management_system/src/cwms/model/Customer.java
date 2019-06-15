/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.model;

/**
 *
 * @author Asanka
 */
public class Customer {

    private String custId;
    private String custname;
    private String title;
    private String NIC;
    private String custaddress;
    private String custmobi;
    private String custland;

    public Customer(String custId, String custname, String title, String NIC, String custaddress, String custmobi, String custland) {
        this.custId = custId;
        this.custname = custname;
        this.title = title;
        this.NIC = NIC;
        this.custaddress = custaddress;
        this.custmobi = custmobi;
        this.custland = custland;
    }

    public Customer() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getCustaddress() {
        return custaddress;
    }

    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }

    public String getCustmobi() {
        return custmobi;
    }

    public void setCustmobi(String custmobi) {
        this.custmobi = custmobi;
    }

    public String getCustland() {
        return custland;
    }

    public void setCustland(String custland) {
        this.custland = custland;
    }
    
    
          
 
    
}
