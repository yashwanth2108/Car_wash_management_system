/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.controller;

import cwms.DataLayer.DataAccess;
import cwms.model.Employee;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class EmployeeHandler {
       
       private boolean status;
      
public boolean addEmployee(String emname,String empadd, String empcontact,String empgen,String empNic,String emp_pos)
{
    
    DataAccess da=new DataAccess();
    status=da.addEmployee(emname,empadd,empcontact,empgen,empNic,emp_pos);
    return status;
    
}
 public int deleteEmployee(int empid)
 {
     DataAccess da=new DataAccess();
     int result =da.deleteEmployee(empid); //da.deleteEmployee(empid);
     return result;
 }
 public boolean updateEmployee(int empid,String empname,String empNic,String empadd, String empcontact)
 {
     DataAccess da=new DataAccess();
     boolean result = da.updateEmployee(empid, empname,empNic, empadd, empcontact);
     return result;
 }
 

 public double calculateSalary(String emp_position,int empid,String currmnth,double d, double l, double a)
 {
     DataAccess da=new DataAccess();
     
    double tot_salary= da.calculateSalary(emp_position,empid, currmnth,d,l,a);
    return tot_salary;
 }
 /*public double calculateEPF(String emp_position,int emp_id)
 {
    DataAccess da=new DataAccess();
    double epf=da.calculateEPF(emp_position,emp_id);
    return epf;
 }
 public double calculatecompETF(String emp_position,int emp_id)
 {
     DataAccess da=new DataAccess();
     
     double etf=da.calculatecompETF(emp_position,emp_id);
     return etf;
     
 }
 public double calculatecompEPF(String emp_position,int emp_id)
 {
     DataAccess da=new DataAccess();
     
     double epf=da.calculatecompETF(emp_position,emp_id);
     
     return epf;
     
 }*/
 
 public void recordStTime(int inempid,String w_date,String sttime)
 {
     DataAccess da=new DataAccess();
     da.recordStartTimeofEmployee(inempid,w_date,sttime);
 }
  public void recordEndTime(int inempid,String etime,String wdate)
 {
     DataAccess da=new DataAccess();
     da.updateEndTimeOfEmployee(inempid,etime,wdate);
 }
 
 public boolean isSuccessful(){
        return status;
    }
}
