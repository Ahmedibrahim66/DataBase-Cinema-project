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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELIFE
 */
public class SellerManController implements Initializable {

      @FXML
    private Button sp;
    
    @FXML
    private Label w;
   

        
        @FXML
    private ImageView cp1;

    @FXML
    private ImageView logout1;
      
            @FXML
          private void handleButtonActionback(Event event) throws IOException {
       
          Stage stage = (Stage) sp.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource("login page.fxml"));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }
         
         
             @FXML
          private void handleButtonActionsp(ActionEvent event) throws IOException {
      String go= "" ; 
               if((LoginPageController.user >=100 )&& (LoginPageController.user<100000))
            go ="TicketSellerFimsPage.fxml" ;
               else go = "cafeteria.fxml";
          Stage stage = (Stage) sp.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource(go));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }     
      
                   @FXML
          private void handleButtonActioncp(Event event) throws IOException {
       
          Stage stage = (Stage) sp.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }   
      
     @Override
     public void initialize(URL url, ResourceBundle rb) {
      String s = "" ;  
       
    if((LoginPageController.user >=100 )&& (LoginPageController.user<100000))
            s ="Welcome Ticket Seller " ;
               else s = "Welcome cafeteria Seller ";
        Connection   con;
            try {
                 con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
            String query = "SELECT Ename FROM employees where Eid = '"+LoginPageController.user+"'";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            products user;
           if (rs.next())
            {
              s += rs.getString(1); 
                
            }
          
          } catch (SQLException ex) {
                 Logger.getLogger(SellerManController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          w.setText(s);
     }     
     
}
