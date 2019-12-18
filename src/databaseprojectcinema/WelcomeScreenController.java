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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class WelcomeScreenController implements Initializable {

    Stage stage;
    Parent root;
    Scene scene;
    Connection con = null;

    
    
    @FXML
    private Button  enter;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       
        stage = (Stage) enter.getScene().getWindow();
        
       // root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
        root = FXMLLoader.load(getClass().getResource("login page.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                  
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
                    String query = "SELECT * \n" +
"FROM films";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            
         
                int fid;
                
                
                 
            DBTablePrinter.printTable(con, "soldtickets");

            
                } catch (SQLException ex) {
            Logger.getLogger(WelcomeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }    
    
}
