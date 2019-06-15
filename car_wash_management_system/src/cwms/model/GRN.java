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
public class GRN {
    private String suppid;
    private String JID;
    private String purdate;
    private double total;

    public GRN() {
    }

    public GRN(String suppid, String JID, String purdate, double total) {
        this.suppid = suppid;
        this.JID = JID;
        this.purdate = purdate;
        this.total = total;
    }

    public String getSuppid() {
        return suppid;
    }

    public void setSuppid(String suppid) {
        this.suppid = suppid;
    }

    public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public String getPurdate() {
        return purdate;
    }

    public void setPurdate(String purdate) {
        this.purdate = purdate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
