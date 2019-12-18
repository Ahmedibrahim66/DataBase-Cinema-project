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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class MangReportController implements Initializable {

   
            Connection con = null;

            
        @FXML
    private Button ticketrevenue;
        
        
        @FXML
    private Button back;
        
        
    @FXML
    private Button cafRev;
    
    @FXML
    private Button totalsales;
    
    
    @FXML
    private Button mostsold;

    @FXML
    private Button bestrev;
    
    
    


        
    Stage stage;
    Parent root;
    Scene scene;
    
    

    @FXML
    void cafRev(ActionEvent event) throws IOException {
        
        stage = (Stage) ticketrevenue.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Cafeteria_account.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    
    
    
        
     @FXML
    void ticketrevenue(ActionEvent event) throws IOException {

        
        stage = (Stage) ticketrevenue.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TicketRevenuePage.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        
        
        
        
    }
    
    
          
     @FXML
    void backbutton(ActionEvent event) throws IOException {

        
        stage = (Stage) ticketrevenue.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
        
        
    }
    
     @FXML
    void totalsalesbutton(ActionEvent event) throws IOException {

        stage = (Stage) ticketrevenue.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TotalSales.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    
     @FXML
    void mostSoldFilm(ActionEvent event) throws IOException, SQLException {

                           
                   List<mostsold> array = new ArrayList<>();

                 
                 
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
                    String query = "SELECT fid \n" +
"FROM films";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            
         
                int fid;
                
                
                  while (rs.next())
            {
                fid = rs.getInt(1);
                
                String query1 = "SELECT sum(quantity)\n" +
"FROM shows1\n" +
"WHERE fid = "+fid+" ";
                
                
            st = con.createStatement();
            ResultSet rs1= st.executeQuery(query1);
            int tickets = 50;
            int soldtickets = 0; 
            

            
         

                  while (rs1.next())
            {
               int quantity= rs1.getInt(1);

                                      String query2 = "SELECT COUNT(quantity)\n" +
"FROM shows1\n" +
"where fid = "+fid+"  ";


            st = con.createStatement();
            ResultSet rs2= st.executeQuery(query2);

            rs2.next();
            
            int number = rs2.getInt(1);
            soldtickets = number*tickets - quantity;
             mostsold value = new mostsold(fid, soldtickets);

           array.add(value);   
             
        
                
            }
            }
                  
        mostsold array1 =  Collections.max(array, Comparator.comparing(s -> s.getRev()));
        
        

            String query4 = "SELECT fname \n" +
"FROM films where fid = '"+array1.getFid()+"'  " ;
        
            Statement st4;
            st4 = con.createStatement();
            ResultSet rs4= st4.executeQuery(query4);
            
            rs4.next();
            
            JOptionPane.showMessageDialog(null , "Most Sold Film is "+rs4.getString(1) + " with Tickets Sold = " +array1.getRev() + " Ticket");
            
            System.out.println("Most sold Film is" + rs4.getString(1));
            
                  
                } catch (SQLException ex) {
                    Logger.getLogger(MangReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
        
    }
    
    
    
    
       @FXML
    void bestrevfilm(ActionEvent event) {
        
                      
                   List<mostsold> array = new ArrayList<>();

                 
                 
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
                    String query = "SELECT fid \n" +
"FROM films";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            
         
                int fid;
                
                
                  while (rs.next())
            {
                fid = rs.getInt(1);
                
                String query1 = "SELECT sum(quantity)\n" +
"FROM shows1\n" +
"WHERE fid = "+fid+" ";
                
                
            st = con.createStatement();
            ResultSet rs1= st.executeQuery(query1);
            int tickets = 50;
            int soldtickets = 0; 
            

            
         

                  while (rs1.next())
            {
               int quantity= rs1.getInt(1);

                                      String query2 = "SELECT COUNT(quantity)\n" +
"FROM shows1\n" +
"where fid = "+fid+"  ";


            st = con.createStatement();
            ResultSet rs2= st.executeQuery(query2);

            rs2.next();
            
            
            int number = rs2.getInt(1);
            soldtickets = number*tickets - quantity;
            
            
             String query5 = "SELECT price \n" +
"FROM films\n" +
"WHERE fid = "+fid+" ";

                
                
            st = con.createStatement();
            ResultSet rs5= st.executeQuery(query5);
            rs5.next();
            int price;
            
            price = rs5.getInt(1);
            
            
            
            int Revenue = soldtickets * price;
                    
             mostsold value = new mostsold(fid, Revenue);

           array.add(value);   
             
        
                
            }
            }
                  
        mostsold array1 =  Collections.max(array, Comparator.comparing(s -> s.getRev()));
        
        

            String query4 = "SELECT fname \n" +
"FROM films where fid = '"+array1.getFid()+"'  " ;
        
            Statement st4;
            st4 = con.createStatement();
            ResultSet rs4= st4.executeQuery(query4);
            
            rs4.next();
            
            JOptionPane.showMessageDialog(null , "Most Revenue From  Film is  "+rs4.getString(1) + " with Revenue = " +array1.getRev() + " NIS ");
            
            
                  
                } catch (SQLException ex) {
                    Logger.getLogger(MangReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
   
                  
                  
    }    
    
}
