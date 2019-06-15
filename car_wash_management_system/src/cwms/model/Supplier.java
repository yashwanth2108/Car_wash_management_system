/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.model;
import cwms.model.Item;
/**
 *
 * @author Asanka
 */
public class Supplier {
private int suppid;    
private String suppname;
private String suppaddress;
private String suppphone;
//private String suppcompany;
private String suppemail;
private int itemid;

    public Supplier() {
    }

    public Supplier(int suppid,String suppname, String suppaddress, String suppphone,String suppemail) {
        this.suppid = suppid;
        this.suppname = suppname;
        this.suppaddress = suppaddress;
        this.suppphone = suppphone;
        this.suppemail=suppemail;
        
       
    }
    public Supplier(String suppname, String suppaddress, String suppphone,String suppemail) {
        
        this.suppname = suppname;
        this.suppaddress = suppaddress;
        this.suppphone = suppphone;
        this.suppemail=suppemail;
    }
    
public Supplier(int suppid,String suppname, String suppaddress, String suppphone,String suppemail,int itemid) {
        
        this.suppid=suppid;
        this.suppname = suppname;
        this.suppaddress = suppaddress;
        this.suppphone = suppphone;
        this.suppemail=suppemail;
        //Item i=new Item();
        this.itemid=itemid;
    }
    public int getSuppId() {
        return suppid;
    }
    public String getSuppname() {
        return suppname;
    }

    public void setSuppname(String suppname) {
        this.suppname = suppname;
    }

    public String getSuppaddress() {
        return suppaddress;
    }

    public void setSuppaddress(String suppaddress) {
        this.suppaddress = suppaddress;
    }

    public String getSuppphone() {
        return suppphone;
    }

    public void setSuppphone(String suppphone) {
        this.suppphone = suppphone;
    }

    public String getSuppemail() {
        return suppemail;
    }

    public void setSuppemail(String suppemail) {
        this.suppemail = suppemail;
    }

  

    public int getItemID() {
        return itemid;
    }

    public int setItemID(int itemid) {
        this.itemid = itemid;
        return itemid;
    }

     
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
