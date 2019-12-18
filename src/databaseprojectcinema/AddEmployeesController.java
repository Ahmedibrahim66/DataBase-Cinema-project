/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import RKinfotech.MysqlMd5;
        import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class AddEmployeesController implements Initializable {

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
    private TextField tf5;
    
       @FXML
    private PasswordField pass1;
        @FXML
    private PasswordField pass2;

    @FXML
    private Button add;

    @FXML
    private Button clear;

    @FXML
    private Button back;

    @FXML
    private DatePicker sd;

    @FXML
    private DatePicker dob;
             Connection con = null;
             
             
                     
    @FXML
    private void addbutton(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        
        
        if(pass1.getText().equals(pass2.getText())){
                
             try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           String name = tf1.getText();
           
           String phone = tf2.getText();
           
           String salary = tf5.getText();
          
          
          
          
            
           String query = "INSERT INTO employees (Eid,phone,Ename,DOB,start_date,job,salary)\n" +
"VALUES ('"+(getMax_id(combobox.getValue())+1)+"','"+phone+"','"+name+"','"+dob.getValue()+"','"+sd.getValue()+"','"+combobox.getValue()+"','"+salary+"')";
           String query2 = "INSERT INTO password VALUES('"+(getMax_id(combobox.getValue())+1)+"' , '"+MysqlMd5.getRKmd5(pass1.getText())+"' )";

           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           st.executeUpdate(query2);
      
           JOptionPane.showMessageDialog(null , "Done");
           clearbutton(event);
           

           
        } catch (SQLException ex) {
            Logger.getLogger(AddFilmController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
             
        } else     JOptionPane.showMessageDialog(null , "Password does not match");
   
        
        
    
    }
    public int getMax_id(String s){
   Connection con = null ; 
    int max =0; 
         try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT Max(Eid) FROM employees Where job= '"+s+"'";
            Statement st;
              st = con.createStatement();
              ResultSet rs= st.executeQuery(query);
            
              if(rs.next()){
                   max =rs.getInt(1); 
              }
                      return max;

         } catch (SQLException ex) {
              Logger.getLogger(CafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
        return max;
     
     
}   
    
     @FXML
    private void backbutton(ActionEvent event) throws IOException {
       
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerEmployeesScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
     private void clearbutton(ActionEvent event) throws IOException {
       
          tf1.setText(""); 
          tf2.setText("");
          tf5.setText("");
          combobox.getSelectionModel().select("Ticket Seller");
          sd.setValue(null);
          dob.setValue(null);
          pass1.setText("");
           pass2.setText("");

          

          
          
           
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         combobox.getItems().addAll("Ticket Seller","Resturant Emp." );
         combobox.getSelectionModel().select("Ticket Seller");
       
    }    
    
}

