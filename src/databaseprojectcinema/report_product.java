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
public class report_product {
     public String name; 
     public int quantity; 

     public report_product(String name, int quantity) {
          this.name = name;
          this.quantity = quantity;
     }

     public String getName() {
          return name;
     }

     public int getQuantity() {
          return quantity;
     }
     
     
}
