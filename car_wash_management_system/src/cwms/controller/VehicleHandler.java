/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author USER
 */
public class VehicleHandler {
        private String invehicleno;
        private String invehbrand;
        private String invehmodel;
        private String incategory;
        
        
        public void addVehicle(String vehno, String brand, String model,String category,int cusid)
        {
            DataAccess d = new DataAccess();
            d.addvehicle(vehno, brand, model, category, cusid);
            
        }
        public void deleteVehicle(String vehicleno)
        {
            DataAccess da=new DataAccess();
            da.deleteVehicle(vehicleno);
        }
        public void updateVehicle(String vehicleno,String cusid)
        {
           int cusidint=Integer.parseInt(cusid);
            DataAccess dac=new DataAccess();
            dac.updateVehicle(cusidint, cusid);
        }
         
        public void viewVehicle()
        {
     
        }
}
