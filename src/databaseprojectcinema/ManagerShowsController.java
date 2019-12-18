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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class ManagerShowsController implements Initializable {
    
    
    
    Stage stage;
    Parent root;
    Scene scene;
    Connection con = null;
    

    @FXML
    private TableView<Shows> table;

    @FXML
    private TableColumn<?, ?> Fid;

    

    @FXML
    private DatePicker date;

    
    @FXML
    private TableColumn<?, ?> fname;
    
      @FXML
    private TableColumn<?, ?> sid;


    @FXML
    private TableColumn<?, ?> price;
    
    @FXML
    private TableColumn<?, ?> sd;

    @FXML
    private TableColumn<?, ?> Quantity;

    @FXML
    private Button editfilm;

    @FXML
    private Button deletefilm;

    @FXML
    private Button back;


    @FXML
    void backbutton(ActionEvent event) throws IOException {
        
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerFilms.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteShow(ActionEvent event) {

         Shows person = table.getSelectionModel().getSelectedItem();
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = person.getSid();
           
          
            
           String query = "DELETE FROM shows1 WHERE sid='"+test+"'";
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<Shows> userlist ; 
         
                userlist = userlist() ; 
         
        
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                sd.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                        
                
                

           
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }

        
        
        
    }

    @FXML
    void editshow(ActionEvent event) {
        
        
        Shows person = table.getSelectionModel().getSelectedItem();
        
        
                   int test = person.getSid();
                   

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           
          String query = "update shows1 \n" +
"set\n" +
"Date = '"+date.getValue()+"'\n" +
"where  sid = '"+test+"'";
          
            
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<Shows> userlist ; 
         
                userlist = userlist() ; 
         
        
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                sd.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                        
                
                

           
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }

        

    }
    
    
      
    public ObservableList<Shows> userlist(){
        
        
        ObservableList<Shows> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM shows1";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            Shows user;
            while (rs.next())
            {
                user = new Shows(rs.getInt("sid") ,rs.getInt("Fid") , rs.getString("fname") , rs.getInt("price") , rs.getInt("Quantity"), rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         ObservableList<Shows> userlist ; 
         
         userlist = userlist() ; 
         
        
                sid.setCellValueFactory(new PropertyValueFactory<>("sid") );
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                sd.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                        

                
                
       
        
    }    
    
}
