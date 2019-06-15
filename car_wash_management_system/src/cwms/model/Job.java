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
public class Job {

      private String jobid;
      private String jobname;
      private String jobamount;
      private String customjob;
      private String starttime;
      private String endtime;

    public Job() {
    }

    public Job(String jobid, String jobname, String jobamount, String customjob, String starttime, String endtime) {
        this.jobid = jobid;
        this.jobname = jobname;
        this.jobamount = jobamount;
        this.customjob = customjob;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public Job(String jobid, String jobname, String jobamount, String starttime, String endtime) {
        this.jobid = jobid;
        this.jobname = jobname;
        this.jobamount = jobamount;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public Job(String jobid) {
        this.jobid = jobid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobamount() {
        return jobamount;
    }

    public void setJobamount(String jobamount) {
        this.jobamount = jobamount;
    }

    public String getCustomjob() {
        return customjob;
    }

    public void setCustomjob(String customjob) {
        this.customjob = customjob;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
      
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
