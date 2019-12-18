/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import RKinfotech.MysqlMd5;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class EditEmployeesController implements Initializable {

    
    
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
          private void handleButtonActionReSetPassword(ActionEvent event) throws IOException, NoSuchAlgorithmException {
       
         String h= JOptionPane.showInputDialog(null, "New Password" , "Error - Refund Amount",JOptionPane.YES_NO_OPTION);
         if(!(h.equals(""))){
              
         
         String pass = MysqlMd5.getRKmd5(h);
               Connection con = null ;
             try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           String query = "Update password set pass = '"+pass+"' Where eid = '"+ManagerEmployeesScreenController.eid1+"'";
            Statement st;
            
              st = con.createStatement();
              st.execute(query);
              JOptionPane.showMessageDialog(null, "Password Changed");
             }catch(Exception ex){
                  System.out.println(ex);
             }
         } else JOptionPane.showMessageDialog(null, "Password does not Changed");
    }

    @FXML
    void addbutton(ActionEvent event) throws IOException {
        
         try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           String name = tf1.getText();
           
           String phone = tf2.getText();
           
           String salary = tf5.getText();
           
           
          
             String query = "update employees\n" +
"set\n" +
"Ename = '"+name+"',\n" +
"Phone = '"+phone+"',\n" +
"DOB = '"+dob.getValue()+"',\n" +
"start_date = '"+sd.getValue()+"',\n" +
"salary = '"+salary+"'\n" +
"where  Eid = '"+ManagerEmployeesScreenController.eid1+"'";
             
            

           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           JOptionPane.showMessageDialog(null , "Done");
           
backbutton( event);
           
        } catch (SQLException ex) {
            Logger.getLogger(AddFilmController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
        

    }

    @FXML
    void backbutton(ActionEvent event) throws IOException {
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerEmployeesScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    void clearbutton(ActionEvent event) {
   tf1.setText(""); 
          tf2.setText("");
          tf5.setText("");
          combobox.getSelectionModel().select("Ticket Seller");
          sd.setValue(null);
          dob.setValue(null);
        

          

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
           tf1.setText(""+ManagerEmployeesScreenController.name1+"");
           tf2.setText(""+ManagerEmployeesScreenController.phone1+"");
           sd.setValue(ManagerEmployeesScreenController.sd1.toLocalDate());
           dob.setValue(ManagerEmployeesScreenController.dob1.toLocalDate());
           
       
           

           int test = ManagerEmployeesScreenController.eid1;
           
          
            
           String query = "SELECT salary FROM employees WHERE Eid='"+test+"'";
           

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
            



           
           
           
           combobox.getSelectionModel().select(ManagerEmployeesScreenController.job1);
           combobox.getItems().addAll("Security","Ticket Seller","Resturant Emp." , "cleaner" , "Computer Man");
           
        // TODO
    }    
    
}
