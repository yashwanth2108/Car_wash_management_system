/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.Common.ConnectionType;
import cwms.DataLayer.Common.DataBase;
import cwms.DataLayer.Common.DataConnection;
import cwms.DataLayer.Common.IDataConnection;
import cwms.DataLayer.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import cwms.model.Customer;
import java.util.Vector;
import javax.swing.JTable;
/**
 *
 * @author USER
 */
public class CustomerHandler {
    private String incustId;
    private String incustname;
    private String intitle;
    private String inNIC;
    private String incustaddress;
    private String incustmobi;
    private String incustland;
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public void addCustomer(String custname,String custemail, String custadd, String custcontact,String custgen,String custNic,String custland)
    {
                
        DataAccess da = new DataAccess();
        da.addCustomer(custname, custemail, custadd, custcontact, custgen, custNic, custland);
        
    }
 public boolean deleteCustomer(String custid)
 {
     boolean result = false;
     
     DataAccess da = new DataAccess();
     result = da.deleteCustomer(custid);
     
     return result;
 }
 public boolean updateCustomer(String custid,String name,String nic , String address , String phone , String land ,String email)
 {
      boolean result = false;
     
      DataAccess da = new DataAccess();
      result = da.updateCustomer(custid, name, nic, address, phone, land, email);
      
      return result;
 }
 public void searchCustomer(String custid,String name)
 {
     DataAccess da = new DataAccess();
   //  da.searchCustomer(custid,name);
    
 }
 public void viewCustomer()
 {
      try {
         String sql = "SELECT * FROM customer";
         DataBase db = new DataBase(ConnectionType.MYSQL);
         con = db.getConnection();
         ps = con.prepareStatement(sql);
         ps.executeQuery();
         
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"DB Connection error.....");
     }
 }
   
}
