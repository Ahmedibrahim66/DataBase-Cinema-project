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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class EditFilmController implements Initializable {
    
    
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
    private void updatebutton(ActionEvent event) throws IOException {
        
        
        
        RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
              
             
            try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
            String test = tf1.getText();
           
           int test1 = Integer.parseInt( tf3.getText());
           int test2 = Integer.parseInt(tf2.getText());
           
           float test3 = Float.parseFloat(tf4.getText());
           String test4 = tf5.getText();
           
             

             
             String query = "update films\n" +
"set\n" +
"fname = '"+test+"',\n" +
"year = '"+test1+"',\n" +
"price = '"+test2+"',\n" +
"breif = '"+test4+"',\n" +
"type = '"+combobox.getValue()+"',\n" +
"rating = '"+test3+"',\n" +
"audence = '"+toogleGroupValue+"'\n" +
"where  Fid = '"+ManagerFilmsController.test1+"'";
          
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           JOptionPane.showMessageDialog(null , "Update done");
           backbutton( event);

           
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
        // TODO
        

                rd1.setToggleGroup(tg);
                rd2.setToggleGroup(tg);
                
                System.out.println(ManagerFilmsController.test6);
              
              
            if(ManagerFilmsController.test6.equals("+18"))
            {
                  rd1.setSelected(true);
                
            } else rd2.setSelected(true);
          
           
           tf1.setText(ManagerFilmsController.test5);
           tf2.setText(""+ManagerFilmsController.test2+"");
           tf3.setText(""+ManagerFilmsController.test3+"");
           tf4.setText(""+ManagerFilmsController.test4+"");
           
           
                      int test = ManagerFilmsController.test1;

           
             String query = "SELECT breif FROM films WHERE Fid='"+test+"'";
           

        try {
            
                      Statement st;
                 con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");

            st = con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
                  {
    
         tf5.setText( rs.getString(1));
                }
        } catch (SQLException ex) {
            Logger.getLogger(EditEmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            

           
           
           combobox.getSelectionModel().select(ManagerFilmsController.test7);
           combobox.getItems().addAll("Comdey","Action","Adventure" , "Historical" , "Documentary" , "Animated" , "ForKids" ,"Horror");
           

           

                   
    } 
    
}
