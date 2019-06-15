/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;

/**
 *
 * @author USER
 */
public class SupplierHandler {
private String supname;
private String supaddress;
private String supphone;
private String supemail;
private int supid;
private int itemid;
private boolean SupplierAdd =false;


public void addSupplier(String supname, String supaddress, String supphone, String supemail) {
        
    DataAccess da=new DataAccess();
    da.addSupplier(supname, supaddress, supphone, supemail);
 }


 public int deleteSupplier(int Suppid)
 {
   DataAccess da=new DataAccess();
     int result =da.deleteSupplier(Suppid); //da.deleteEmployee(empid);
     return result;
 }
 public int updateSupplier(int supid,String supname, String supaddress, String supphone, String supemail)
 {
     DataAccess da=new DataAccess();
     int result = da.updateSupplier(supid, supname,supaddress, supphone, supemail);
     return result;
 }
 public void searchSupplier(String supid)
 {
     
 }
 public void viewSupplier()
 {
     
      /* DataAccess da=new DataAccess();
      int result = da.viewSupplier(supname,supaddress,supphone,supemail,itemid);
      return result;*/
 }
 
 public boolean isSuccessful(){
        return SupplierAdd;
    }
}
