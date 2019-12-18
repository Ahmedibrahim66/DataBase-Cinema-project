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
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ELIFE
 */
public class Add_productController implements Initializable {
@FXML 
TextField tf2;
   @FXML 
TextField tf1;
   @FXML 
TextField tf21;
    
        @FXML 
Button add;
            @FXML 
Button back;
                @FXML 
Button clear;
       @FXML
    private void handleButtonActionAdd(ActionEvent event) throws IOException {
         try{
         
       Connection con; 
           try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           String test = tf1.getText();
           
           double test1 = Double.parseDouble( tf2.getText());
           double test2 = Double.parseDouble(tf21.getText());
                     
            
           String query = "INSERT INTO rest_prod (p_name,p_cost,p_price)\n" +
"VALUES ('"+test+"','"+test1+"','"+test2+"');";
           
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           JOptionPane.showMessageDialog(null , "Insert done");
           tf1.setText(""); 
           tf2.setText(""); 
           tf21.setText(""); 

           
        } catch (SQLException ex) {
            Logger.getLogger(Add_productController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
         }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "Please check the fields... Error in Entering Data" );
         }
             
    
    }
      @FXML
        private void handleButtonActionClear(ActionEvent event) throws IOException {
           tf1.setText(""); 
           tf2.setText(""); 
           tf21.setText(""); 

        }
        
           @FXML
        private void handleButtonActionBack(ActionEvent event) throws IOException {
     Stage stage = (Stage) back.getScene().getWindow();
     Object root = FXMLLoader.load(getClass().getResource( "view_prod.fxml"));
     Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
        
        }
     @Override
     public void initialize(URL url, ResourceBundle rb) {
          // TODO
     }     
     
}
