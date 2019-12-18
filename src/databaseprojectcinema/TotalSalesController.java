/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class TotalSalesController implements Initializable {
    
    Stage stage;
    Parent root;
    Scene scene;
    
            Connection con = null;
            
    
    @FXML
    private Button Emp;

            
    @FXML
    private Button MonthlyRevenue;

    @FXML
    private Button TodayRevenue;

    @FXML
    private Button YearlyRevenue;

    @FXML
    private DatePicker day;

    @FXML
    private Button back;

    @FXML
    private ComboBox<String> Month;

    @FXML
    private TextField year;
    
    
    
    

    @FXML
    void MonthRevenue(ActionEvent event) throws SQLException {
        
        
        int TotalRevenueTick = 0 ; 
        double  TotalCafSell = 0;
        double  TotalCafExp = 0;
        double  TotalRevenueCaf = 0 ; 
        double total=0;
        
         con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"WHERE MONTH(date) = "+Month.getValue()+" ";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                            TotalRevenueTick = rs.getInt(1);

                
            }
                  
       
                  
                  String query1 = "SELECT sum(total)\n" +
"FROM tbill2\n" +
"WHERE MONTH(date) = "+Month.getValue()+" ";
        
           st = con.createStatement();
            ResultSet rs1= st.executeQuery(query1);
                 while (rs1.next())
            {
                TotalCafSell = rs1.getDouble(1);            
            }
                
                  
                  
                        String query2 = "SELECT sum(exp)\n" +
"FROM tbill2\n" +
"WHERE MONTH(date) = "+Month.getValue()+" ";
        
           st = con.createStatement();
            ResultSet rs2= st.executeQuery(query2);
                 while (rs2.next())
            {
                TotalCafExp = rs2.getDouble(1);            
            }
                 

                 
            TotalRevenueCaf = TotalCafSell - TotalCafExp;
            total = TotalRevenueCaf + TotalRevenueTick;
            
            
            
            JOptionPane.showMessageDialog(null , "Total Revenue for Month " +Month.getValue()+ "  is :- "+ total);

                   
    

    }

    @FXML
    void YearRevenue(ActionEvent event) throws SQLException {

        
        int TotalRevenueTick = 0 ; 
        double  TotalCafSell = 0;
        double  TotalCafExp = 0;
        double  TotalRevenueCaf = 0 ; 
        double total=0;
        
         con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"WHERE YEAR(date) = "+year.getText()+" ";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                            TotalRevenueTick = rs.getInt(1);

                
            }
                  
       
                  
                  String query1 = "SELECT sum(total)\n" +
"FROM tbill2\n" +
"WHERE YEAR(date) = "+year.getText()+" ";
        
           st = con.createStatement();
            ResultSet rs1= st.executeQuery(query1);
                 while (rs1.next())
            {
                TotalCafSell = rs1.getDouble(1);            
            }
                
                  
                  
                        String query2 = "SELECT sum(exp)\n" +
"FROM tbill2\n" +
"WHERE YEAR(date) = "+year.getText()+" ";
        
           st = con.createStatement();
            ResultSet rs2= st.executeQuery(query2);
                 while (rs2.next())
            {
                TotalCafExp = rs2.getDouble(1);            
            }
                 

                 
            TotalRevenueCaf = TotalCafSell - TotalCafExp;
            total = TotalRevenueCaf + TotalRevenueTick;
            
            
            
            JOptionPane.showMessageDialog(null , "Total Revenue for Year " +year.getText()+ "  is :- "+ total);

                   
            
    }

    @FXML
    void backbutton(ActionEvent event) throws IOException {
        
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("mangReport.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void dayRevenue(ActionEvent event) throws SQLException {
        
        int TotalRevenueTick = 0 ; 
        double  TotalCafSell = 0;
        double  TotalCafExp = 0;
        double  TotalRevenueCaf = 0 ; 
        double total=0;
        

        
              con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"where date='"+day.getValue()+"'";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                TotalRevenueTick = rs.getInt(1);            
            }
                  
                  
              String query1 = "SELECT sum(total)\n" +
"FROM tbill2\n" +
"where date='"+day.getValue()+"'";
              
              
            st = con.createStatement();
            ResultSet rs1= st.executeQuery(query1);
                 while (rs1.next())
            {
                TotalCafSell = rs1.getDouble(1);            
            }
                 
                 
                   String query2 = "SELECT sum(exp)\n" +
"FROM tbill2\n" +
"where date='"+day.getValue()+"'";
              
              
            st = con.createStatement();
            ResultSet rs2= st.executeQuery(query2);
                 while (rs2.next())
            {
                TotalCafExp = rs2.getDouble(1);            
            }
                 
                 
            TotalRevenueCaf = TotalCafSell - TotalCafExp;
            total = TotalRevenueCaf + TotalRevenueTick;
            
            
            
            JOptionPane.showMessageDialog(null , "Total Revenue for " +day.getValue()+ "  is :- "+ total);

                 
                 
                  
                  

    }
    
    
        @FXML
    void empcost(ActionEvent event) throws SQLException {
        
          con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(salary)\n" + "FROM employees" ;

        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                
            JOptionPane.showMessageDialog(null , "Total Salaries of Employees Monthly is " + rs.getInt(1) );
            
            }
        

    }

    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                 Month.getItems().addAll("1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10", "11" , "12");

                 
    }    
    
}
