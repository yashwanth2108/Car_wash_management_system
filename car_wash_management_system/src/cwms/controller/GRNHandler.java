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
public class GRNHandler {
    private String grnno;
    private String insuppid;
    private String inJID;
    private String inpurdate;
    private double intotal;
 public void addGRN(String ingrn_id,String insup_id,String insup_name,String inpur_date,String intotal)
        {
            DataAccess dc=new DataAccess();
            dc.addGRN(ingrn_id, insup_id, insup_name, inpur_date, intotal);
        }
 public void deleteGRN(String grnno)
        {
           DataAccess dacc=new DataAccess();
           dacc.deleteGRN(grnno);
        }
 public void updateGRN(String grnno,String pudate,String tota)
        {
            DataAccess da=new DataAccess();
            da.updateGRN(grnno, pudate, tota);
        }
         public void searchGRN(String grnid)
        {
     
        }
        public void viewGRN()
        {
     
        }   
}
