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
public class JobHandler {
    private String jobid;
    private int serviceid;
    private double totalamount;
    private String date;
    private String time;
    private String jobno;
    private String jobdescription;
    private double jobprice;
    private String cusid;
    private String cusname;
    private String vehregno;
    private String vehcategory;
    private String jobstatus;
    
   
 public void addJob(int sid, String jobdescription, double jobamount, String time, String date, String cusid, String cusname, String vehregno, String vehcategory, String jobstatus, double totalamount) {

           DataAccess da = new DataAccess();
           da.addJob(sid,jobdescription,jobamount, time, date, cusid,cusname, vehregno, vehcategory, jobstatus, totalamount);
    }
            
         
 public boolean endJob(String jobstatus, int sid)
{
    boolean result = false;
      DataAccess da = new DataAccess();
      result = da.endJob(jobstatus,sid);
     return result;

}


 public boolean deleteJob(String jid)
 {
     boolean result = false;
     DataAccess da = new DataAccess();
     result = da.deleteJob(jid);
     return result;
 }
 public boolean updateJob(String jid, String jobname, double jobprice, String time, String date, String cusname, String vehregno, String vehcategory,  String jstatus)
 {
      boolean result = false;
      DataAccess da = new DataAccess();
      result = da.updateJob(jid, jobname, jobprice, time, date, cusname, vehregno, vehcategory, jstatus);
     return result;
 }
 public void searchJob(String searchjob)
 {
     DataAccess da = new DataAccess();
     da.searchJob(searchjob);
 }

  public void addJobPrices(String jobdescription, double jobprice)
  {
      DataAccess da = new DataAccess();
      da.addJobPrices(jobdescription,jobprice );
  }
  
  public void searchJobPrices(String searchservices)
 {
     DataAccess da = new DataAccess();
     da.searchJobPrices(searchservices);
 }
  
  
  
  public boolean updateJobPrices(String jobno, String jobdescription, double jobprice)
  {
      boolean result =false;
      DataAccess da = new DataAccess();
      result = da.updateJobPrices(jobno, jobdescription, jobprice);
      return result;
  }
  
  public boolean deleteJobPrices(String jobno)
 {
     boolean result = false;
     DataAccess da = new DataAccess();
     result = da.deleteJobPrices(jobno);
     return result;
 }

  

 }
