/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.sql.Date;

/**
 *
 * @author fcbar
 */
public class TicketRevenue {
    
   public  int billno;
   public String fname;
   public int Revenue;
   Date date;
   
   
   
   
   

    public TicketRevenue() {
    }

    public TicketRevenue(int billno, String fname, int Revenue, Date date) {
        this.billno = billno;
        this.Revenue = Revenue;
        this.date = date;
        this.fname = fname;
    }

    
    public int getBillno() {
        return billno;
    }

    public void setBillno(int billno) {
        this.billno = billno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    
    public int getRevenue() {
        return Revenue;
    }

    public void setRevenue(int Revenue) {
        this.Revenue = Revenue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    
    

    
}
