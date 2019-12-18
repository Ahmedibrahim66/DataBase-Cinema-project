/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ELIFE
 */
public class order {
     
     public  String pname; 
     public int pid;
     public  double price; 
     public  int quantity; 
     public  double expenses;
     public double Total ; 
Connection con = null; 
     public order(String pname, int quantity) throws SQLException {
           con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
          this.pname = pname;
          this.quantity = quantity;
          this.price =  getPrice();
         this.expenses = getCost(); 
          this.Total= getTotal(); 
          this.pid = getPid(); 
          
          
          
     }

     order(int prod, int quantity) {
             try {
             con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT p_name\n From rest_prod \n WHERE product_id= '"+ prod +"'";
             Statement st;
           st = con.createStatement();
          ResultSet rs =  st.executeQuery(query); 
               if(rs.next()){
                 pname = rs.getString(1) ;
            }
                this.quantity = quantity;
          this.price =  getPrice();
         this.expenses = getCost(); 
          this.Total= getTotal(); 
          this.pid = getPid(); 
         }catch(Exception ex){
              System.out.println(ex) ;
               
         } 
     }

     public String getPname() {
          return pname;
     }

     public double getPrice() {
         try {
          //  con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT p_price\n From rest_prod \n WHERE p_name= '"+ pname +"'";
           
           
           Statement st;
           st = con.createStatement();
          ResultSet rs =  st.executeQuery(query); 
               if(rs.next()){
                 price = rs.getDouble(1) ;
            }
         }catch(Exception ex){
              System.out.println(ex) ;
              price = 0 ; 
         } 
            return price;
     }

     public int getQuantity() {
                    
          return quantity;
     }

     
     
     public double getCost() {
            try {
           // con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT p_cost\n From rest_prod \n WHERE p_name= '"+ pname +"'";
           
           
           Statement st;
           st = con.createStatement();
          ResultSet rs =  st.executeQuery(query); 
               if(rs.next()){
                 expenses = rs.getDouble(1) ;
            }
         }catch(Exception ex){
              System.out.println(ex) ;
              expenses = 0 ; 
         }        return (expenses* quantity) ;
     }
           
     
     
     
     public int getPid() {
            try {
           // con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT product_id\n From rest_prod \n WHERE p_name= '"+ pname +"'";
           
           
           Statement st;
           st = con.createStatement();
          ResultSet rs =  st.executeQuery(query); 
               if(rs.next()){
                 pid = rs.getInt(1) ;
            }
         }catch(Exception ex){
              System.out.println(ex) ;
              pid = 0 ; 
         } 
          return (pid) ;
     }
  
     
     public double getTotal() {
          return (price* quantity) ;
     }
}
