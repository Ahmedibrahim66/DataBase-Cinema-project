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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */

   





public class AddFilmController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Stage stage;
    Parent root;
    Scene scene;
    @FXML
    private ComboBox<String> combobox;


    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf5;

    @FXML
    private RadioButton rd1;

    @FXML
    private RadioButton rd2;

    @FXML
    private Button add;

    @FXML
    private Button clear;

    @FXML
    private Button back;
    
         Connection con = null;

    
    ToggleGroup tg = new ToggleGroup(); 

                    
    @FXML
    private void addbutton(ActionEvent event) throws IOException {
        
        
        
        RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        System.out.println(toogleGroupValue);
              
                
             try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           String test = tf1.getText();
           
           int test1 = Integer.parseInt( tf2.getText());
           Float test2 = Float.parseFloat(tf3.getText());
           
           int test3 = Integer.parseInt(tf4.getText());
           String test4 = tf5.getText();
           
          
            
           String query = "INSERT INTO films (fname,year,price,breif,type,rating,audence)\n" +
"VALUES ('"+test+"','"+test1+"','"+test2+"','"+test4+"','"+combobox.getValue()+"','"+test3+"','"+toogleGroupValue+"');";
         
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           
           JOptionPane.showMessageDialog(null , "Insert done");
           
           
           
           
          tf1.setText(""); 
          tf2.setText(""); 
          tf3.setText(""); 
          tf4.setText(""); 
          tf5.setText("");
          combobox.getSelectionModel().select("Horror");
           

           
        } catch (SQLException ex) {
            Logger.getLogger(AddFilmController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
             
             
    }
    
     @FXML
    private void backbutton(ActionEvent event) throws IOException {
       
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerFilms.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
     private void clearbutton(ActionEvent event) throws IOException {
       
          tf1.setText(""); 
          tf2.setText(""); 
          tf3.setText(""); 
          tf4.setText(""); 
          tf5.setText("");
          combobox.getSelectionModel().select("Horror");

          

          
          
           
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

                rd1.setToggleGroup(tg);
                rd2.setToggleGroup(tg);
                
                
                 rd1.setSelected(true);

         
         combobox.getSelectionModel().select("Horror");
         combobox.getItems().addAll("Comdey","Action","Adventure" , "Historical" , "Documentary" , "Animated" , "ForKids" ,"Horror");
         

        
    }    
    
}
