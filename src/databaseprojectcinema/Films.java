/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

/**
 *
 * @author fcbar
 */

            
public  class Films {
    
    
   public  int Fid;
   public String fname;
   public int price;
   public  String type;
   public  float rating;
   public String audence;
   public int year;
   public int Quantity;
   
   
   
   

    public Films() {
    }

    

    public Films(int Fid, String fname, int price,  String type, int year , String audence , float rating ,int Quantity) {
        this.Fid = Fid;
        this.fname = fname;
        this.price = price;
        this.type = type;
        this.year = year;
        this.audence = audence;
        this.rating = rating;
        this.Quantity = Quantity;
        
        
        
    }
    

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAudence() {
        return audence;
    }

    public void setAudence(String audence) {
        this.audence = audence;
    }

    public   int getFid() {
        return Fid;
    }

    public void setFid(int Fid) {
        this.Fid = Fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

  

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
   
    
    
    
}
