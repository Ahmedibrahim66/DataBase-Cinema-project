/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.sql.Date;


public class employees {

   
    
    
    int Eid;
    String Ename;
    Date dob;
    String Phone;
    Date sd;
    String job;
    int salary;
    
    
    
     public employees(int Eid, String Phone, String Ename, Date dob,  Date sd , String job ,int salary) {
        this.Eid = Eid;
        this.Ename = Ename;
        this.dob = dob;
        this.Phone = Phone;
        this.sd = sd;
        this.job = job;
        this.salary = salary;
    }

    public employees() {
    }

    public int getEid() {
        return Eid;
    }

    public void setEid(int Eid) {
        this.Eid = Eid;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String Ename) {
        this.Ename = Ename;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Date getSd() {
        return sd;
    }

    public void setSd(Date sd) {
        this.sd = sd;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

     
     
     
    
    
    
}
