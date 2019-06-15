
package cwms.controller;

import cwms.ui.ViewSales;
import cwms.DataLayer.DataAccess;
//import dbconnect.DBconnect;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.text.View;

public class DirectSalesHandler {
    
     Connection con = null; //database connect
     
    public String invoiceID;
    private String custId;
    private String custname;
    private String paymenttype;
    private String itemid;
    private String itemname;
    private int qty;
    private double total;
    private double discount;
    
    public void addInvoice(String cid,String cname,String paymenttype,String itid,String itname,String itqty,double discount)
    {
        
    }

    public void paidInvoice (String invId, String cusId, String customerName, String paymentType, String date, String itemid, String itemname, String qty, double unitprice, double total, double ftotal,double paidamount, double balance)        
    {
        
    }

    public void searchInvoice(String invoiceID)
    {
        
    }
    
    public double calculateTotal()
    {
     double total=10000;
     return total;
    }
    public double calculateDiscount()
    {
      double discount=100;
      return discount;
    }
  public void tableload()
    {
        DataAccess dr = new DataAccess();
        dr.tableLoad();
        
    }
}
