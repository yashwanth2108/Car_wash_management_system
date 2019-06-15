/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the titemlate in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;
import cwms.DataLayer.DataAccess;

/**
 *
 * @author USER
 */
public class ItemHandler {
    private String initemid;
    private String incategory;
    private String inpurchasedDate;
    private double inunitPrice;
    private double indiscountrate;
    private String inname;
    private int inquantity;
    private double incost;

 public void addItem(String itid,String itname,String prdate,String itcat,String itunipr,String iqty,String discount) {
     DataAccess dac=new DataAccess();
     dac.addItem(itid,itname,prdate,itcat,itunipr,iqty,discount);
 }
         
 public void deleteItem(String itemid)
 {
     DataAccess dacc=new DataAccess();
     dacc.deleteItem(itemid);
  
 }
 public void updateItem(String itid,String itname,String prdate,String itunipr,String iqty,String discount)
 {
      DataAccess dac=new DataAccess();
     dac.updateItem(itid,itname,prdate,itunipr,iqty,discount);
 }
 public void searchItem(String itemid)
 {
     
 }
 public void viewItem()
 {
     
 }
}
