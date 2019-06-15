/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.model;

/**
 *
 * @author Theekshana
 */
public class Vehicle {

        private String vehicleno;
        private String vehbrand;
        private String vehmodel;
        private String category;

    public Vehicle() {
    }

    public Vehicle(String vehicleno, String vehbrand, String vehmodelno, String category) {
        this.vehicleno = vehicleno;
        this.vehbrand = vehbrand;
        this.vehmodel = vehmodel;
        this.category = category;
    }

   
    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }


   

    public String getVehbrand() {
        return vehbrand;
    }

    public void setVehbrand(String vehbrand) {
        this.vehbrand = vehbrand;
    }

    public String getVehmodelno() {
        return vehmodel;
    }

    public void setVehmodelno(String vehmodelno) {
        this.vehmodel = vehmodel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
        
        
        

   

   
}
