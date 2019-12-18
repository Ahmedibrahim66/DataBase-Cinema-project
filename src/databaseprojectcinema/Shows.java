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


public class Shows {
    int sid;
    int fid;
    int price;
    String fname;
    Date Date;
    int Quantity;

    public Shows(int sid ,int fid, String fname,  int price, int Quantity, Date Date) {
        this.sid = sid;
        this.fid = fid;
        this.price = price;
        this.fname = fname;
        this.Date = Date;
        this.Quantity = Quantity;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
    
    
    
    
            
    
}
