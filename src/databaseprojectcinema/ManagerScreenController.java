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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class ManagerScreenController implements Initializable {

    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private Button  films;
    @FXML
    private Button  employees;


    @FXML
    private Button reports;
    
    
    @FXML
    private Button prodducts;
    
     @FXML
    private Button ts;
   
     
     
    @FXML
    private ImageView cp;

    @FXML
    private ImageView logout;
    
    
    
    @FXML
    private void filmsbutton(ActionEvent event) throws IOException {
       
        stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerFilms.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
        @FXML
    private void changepassword(Event event) throws IOException {
       
        stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
         @FXML
    private void asticket(ActionEvent event) throws IOException {
       
        stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SecondScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

     @FXML
    private void employeesbutton(ActionEvent event) throws IOException {
       
        stage = (Stage) employees.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerEmployeesScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
     @FXML
    private void logoutbutton(MouseEvent event) throws IOException {
       
        stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("login page.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
      @FXML
    void reportsbutton(ActionEvent event) throws IOException {
        
        stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("mangReport.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        

    }
    
    @FXML
    void productsbutton(ActionEvent event) throws IOException {
        
         stage = (Stage) films.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("view_prod.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        

    }    
            @FXML
    private PasswordField np;
           @FXML
    private PasswordField cnp;
           
           
      @FXML
      private void handleButtonActionReSetPassword(Event event) throws IOException, NoSuchAlgorithmException {
       
         String h= JOptionPane.showInputDialog(null, "New Password" , "Change Your Password",JOptionPane.YES_NO_OPTION);
         if(!(h.equals(""))){
              
         
         String pass = MysqlMd5.getRKmd5(h);
               Connection con = null ;
             try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           String query = "Update password set pass = '"+pass+"' Where eid = '"+LoginPageController.user+"'";
            Statement st;
            
              st = con.createStatement();
              st.execute(query);
              JOptionPane.showMessageDialog(null, "Password Changed");
             }catch(Exception ex){
                  System.out.println(ex);
             }
         } else JOptionPane.showMessageDialog(null, "Password does not Changed");
    }

    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
