/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class SecondScreenController implements Initializable {

    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private Button  manager;
    @FXML
    private Button employee;
    
        @FXML
    private void TicketSellerPage(ActionEvent event) throws IOException {
       
        stage = (Stage) employee.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("TicketSellerFimsPage.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    private void CafeteriaSellerPage(ActionEvent event) throws IOException {
       
        stage = (Stage) manager.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("cafeteria.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    @FXML
    private void ManagerPage(ActionEvent event) throws IOException {
       
        stage = (Stage) manager.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
    @FXML
    private void handleButtonAction1(ActionEvent event) throws IOException {
       
        stage = (Stage) employee.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EmployeeScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    



}
