/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

/**
 *
 * @author ELIFE
 */
public class products {
    public int id; 
    public String pname; 
    public double cost ; 
    public double price ; 

     public products(int id, String pname, double cost, double price) {
          this.id = id;
          this.pname = pname;
          this.cost = cost;
          this.price = price;
     }

     public int getId() {
          return id;
     }

     public String getPname() {
          return pname;
     }

     public double getCost() {
          return cost;
     }

     public double getPrice() {
          return price;
     }
    
     


     
     

     
}
