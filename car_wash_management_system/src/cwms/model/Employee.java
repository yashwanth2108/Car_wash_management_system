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
public class Employee {

       private String empname;
       private int empid;
       private String address;
       private String contactno;
       private String gender;
       private String NIC;
       private int noofworkingdayspermonth;
       private String position;
    
       
    public Employee() {
        
    }
 
    public Employee(int empid,String empname, String NIC,String address, String contactno, String gender,String position ) {
        this.empname = empname;
        this.empid = empid;
        this.address = address;
        this.contactno = contactno;
        this.gender = gender;
        this.NIC = NIC;
        this.position=position;
    }

    public Employee(int empid,String empname, String address, String contactno, String gender, String NIC) {
        this.empname = empname;
        this.empid = empid;
        this.address = address;
        this.contactno = contactno;
        this.gender = gender;
        this.NIC = NIC;
    }
    
    public Employee(String position) {
        this.position = position;
    }

    public Employee(String empname, int empid, String address) {
        this.empname = empname;
        this.empid = empid;
        this.address = address;
    }
    
/*
    public Employee( int empid,String empname, String contactno) {
        this.empid = empid;
        this.empname = empname;
        this.contactno = contactno;
    }*/
     public Employee(int noofworkingdaysprmonth) {
        this.noofworkingdayspermonth = noofworkingdaysprmonth;
        
     }  
 /*   public Employee(int empid) {
        this.empid = empid;
    }
*/
    
   

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public int getNoofworkingdayspermonth() {
        return noofworkingdayspermonth;
    }

    public void setNoofworkingdayspermonth(int noofworkingdayspermonth) {
        this.noofworkingdayspermonth = noofworkingdayspermonth;
    }
  
}
