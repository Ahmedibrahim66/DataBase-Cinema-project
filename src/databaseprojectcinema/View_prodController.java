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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ELIFE
 */
public class View_prodController implements Initializable {
     
     
     
     @FXML
    private TableView<products>  table ;

    @FXML
    private TableColumn<?, ?> pid;

    @FXML
    private TableColumn<?, ?> pname;

    @FXML
    private TableColumn<?, ?> pcost;

    @FXML
    private TableColumn<?, ?> pprice; 
         @FXML
    private Button edit;
     
     @FXML
    private Button back;
     @FXML
    private Button add; 
      @FXML
    private Button delete; 
     
         @FXML
    private void handleButtonActionback(ActionEvent event) throws IOException {
       
          Stage stage = (Stage) back.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }
    
             @FXML
    private void handleButtonActionAdd(ActionEvent event) throws IOException {
       
          Stage stage = (Stage) add.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource("add_product.fxml"));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }
    public static int pastid; 
    public static double pastcost; 
    public static double pastprice; 
    public static String pastname; 
     
    
    @FXML
    private void editprod(ActionEvent event) throws IOException {
         try{
       
        products p = table.getSelectionModel().getSelectedItem();
      
        int id = p.getId() ; 
       
        double cost = p.getCost(); 
        double price = p.getPrice() ; 
        String pname =p.getPname() ; 
        
        pastid = id; 
        pastcost = cost ; 
        pastprice = price; 
        pastname = pname; 
     
         


          Stage stage = (Stage) edit.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource("edit_product.fxml"));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
         }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want to edit...");
         }
       
    }
 @FXML
     private void deletebutton(ActionEvent event) throws IOException {
       int flag = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete?", "Confirm", JOptionPane.YES_NO_OPTION); 
      
              if(flag ==0 ){
          try { 
          Connection con = null ;
        products p = table.getSelectionModel().getSelectedItem();
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = p.getId();
           
          
            
           String query = "DELETE FROM rest_prod WHERE product_id='"+test+"'";
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<products> userlist ; 
         
                userlist = userlist() ; 
         
        
        
                pid.setCellValueFactory(new PropertyValueFactory<>("Id") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		pprice.setCellValueFactory(new PropertyValueFactory<>("Price") );
                pcost.setCellValueFactory(new PropertyValueFactory<>("Cost") );
               
                
                table.setItems(userlist);
                

           
        } catch (SQLException ex) {
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }

        }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want to Delete...");
         }}
    
    }
         public ObservableList<products> userlist(){
        
        Connection con = null ;
        ObservableList<products> userlist =  FXCollections.observableArrayList() ; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM rest_prod";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            products user;
            while (rs.next())
            {
                user = new products(rs.getInt("product_id") , rs.getString("p_name") , rs.getDouble("p_cost") ,rs.getDouble("p_price"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }
     
     /**
      * Initializes the controller class.
      */
     @Override
     public void initialize(URL url, ResourceBundle rb) {
            
         ObservableList<products> userlist ; 
         
         userlist = userlist() ; 
         
        
        
              pid.setCellValueFactory(new PropertyValueFactory<>("Id") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		pprice.setCellValueFactory(new PropertyValueFactory<>("Price") );
                pcost.setCellValueFactory(new PropertyValueFactory<>("Cost") );
               
                
       table.setItems(userlist);
                
     }     
     
}

