/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import RKinfotech.MysqlMd5;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 *
 * @author fcbar
 */
public class DataBaseProjectCinema extends Application {
    
     
    @Override
    public void start(Stage stage) throws Exception {
        
        getConnection();
        
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(false);
        stage.setResizable(false);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)   {
          
          
        launch(args);
    }
    
      public Connection getConnection()
    {
        Connection con = null;
      
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
           return con;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseProjectCinema.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not connected");
            return null;                      
        }
    }
    
    
  
        
      
    
}
