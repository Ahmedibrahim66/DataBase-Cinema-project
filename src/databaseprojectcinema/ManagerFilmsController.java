/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseprojectcinema;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class ManagerFilmsController implements Initializable {

    Stage stage;
    Parent root;
    Scene scene;
    Connection con = null;

    static int test1;
    static int test2;
    static int test3;
    static float test4;
    static String test5;
    static String test6;
    static String test7;
    static String test8;
    
    
    
    
    @FXML
    private Button addfilm;
    
    @FXML
    private TextField search;

    @FXML
    private TableView<Films>  table ;

    @FXML
    private TableColumn<?, ?> Fid;

    @FXML
    private TableColumn<?, ?> fname;

    @FXML
    private TableColumn<?, ?> year;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> audence;

    @FXML
    private TableColumn<?, ?> rating;

    @FXML
    private Button editfilm;

    @FXML
    private Button deletefilm;

    @FXML
    private Button back;

    @FXML
    private Button actors;
    
    @FXML
    private Button shows;
    
    @FXML
    private Button addshow;
    
    @FXML
    private DatePicker datepicker;
    
    
    
   
    
     
    @FXML
    private void addfilm1(ActionEvent event) throws IOException {
       
        stage = (Stage) addfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddFilm.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
     @FXML
    private void editfilm1(ActionEvent event) throws IOException {
      try{ 
        Films person1 = table.getSelectionModel().getSelectedItem();
          
        int test = person1.getFid();
        int test21 = person1.getPrice();
        int test31 = person1.getYear();
        float test41 = person1.getRating();
        
        
        String test51 = person1.getFname();
        String test61 = person1.getAudence();
        String test71 = person1.getType();
        
        
        
        
        
          
        test5= test51;
        test6=test61;
        test7 = test71;
                
        test1= test;
        test2=test21;
        test3 = test31;
        test4 = test41;
        
                
                
        
        
        
        
             

        stage = (Stage) editfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EditFilm.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want to edit...");
         }
    
    }

    
         @FXML
    private void backbutton(ActionEvent event) throws IOException {
       
        stage = (Stage) editfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }   
    
        @FXML
    private void actorsbutton(ActionEvent event) throws IOException {
       
      try{
           Films person1 = table.getSelectionModel().getSelectedItem();
      
        
try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = person1.getFid();
           
          
            
           String query = "SELECT breif FROM films WHERE Fid='"+test+"'";
           

          Statement st=con.createStatement();
ResultSet rs=st.executeQuery(query);
while(rs.next())
{
    
     JOptionPane.showMessageDialog(null , rs.getString(1));
}


           
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
}catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want to see brief...");
         }
    
    }
    
    
    
    @FXML
     private void deletebutton(ActionEvent event) throws IOException {
        int flag = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete?", "Confirm", JOptionPane.YES_NO_OPTION); 
      
              if(flag ==0 ){
       try{
            Films person = table.getSelectionModel().getSelectedItem();
       
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = person.getFid();
           
          
            
           String query = "DELETE FROM films WHERE fid='"+test+"'";
          
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           
           JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<Films> userlist ; 
         
                userlist = userlist() ; 
         
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                year.setCellValueFactory(new PropertyValueFactory<>("year") );
                type.setCellValueFactory(new PropertyValueFactory<>("type") );
                rating.setCellValueFactory(new PropertyValueFactory<>("rating") );
                audence.setCellValueFactory(new PropertyValueFactory<>("audence") );
                
                table.setItems(userlist);
                

           
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }

        }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want to delete...");
         }
              }
    
    }
     
     
         @FXML
     private void addshow(ActionEvent event) throws IOException {
         
        try{ 
          Films person = table.getSelectionModel().getSelectedItem();
          
        
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
            String query1 = "INSERT INTO shows1 (fid,fname,price,quantity,date)\n" +
"VALUES ('"+person.getFid()+"','"+person.getFname()+"','"+person.getPrice()+"','"+person.getQuantity()+"','"+datepicker.getValue()+"')";
           
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query1); 
           
                      JOptionPane.showMessageDialog(null , "Show was added");

           
           
             } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
        
           }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want...");
         }
         
       
     }
     
      @FXML
     private void shows(ActionEvent event) throws IOException {
         
        stage = (Stage) addfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerShows.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
         
     }
     
     
        @FXML
     private void searchbutton(KeyEvent event) throws IOException {
         
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
            
            
            
         ObservableList<Films> userlist ; 
         
         userlist = userlist2() ; 
         
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                year.setCellValueFactory(new PropertyValueFactory<>("year") );
                type.setCellValueFactory(new PropertyValueFactory<>("type") );
                rating.setCellValueFactory(new PropertyValueFactory<>("rating") );
                audence.setCellValueFactory(new PropertyValueFactory<>("audence") );
                table.setItems(userlist);
                
                
       
       
           
           
             } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
         
     }
     
     
    public ObservableList<Films> userlist(){
        
        
        ObservableList<Films> userlist =  FXCollections.observableArrayList() ; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM films";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            Films user;
            while (rs.next())
            {
                user = new Films(rs.getInt("Fid") , rs.getString("fname") , rs.getInt("price") ,rs.getString("type"), rs.getInt("year") , rs.getString("audence") , rs.getFloat("rating") , rs.getInt("Quantity"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }
    
     public ObservableList<Films> userlist2(){
        
        
        ObservableList<Films> userlist =  FXCollections.observableArrayList() ; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
              
            String query = "SELECT * FROM films\n" +
"WHERE fname LIKE '"+search.getText()+"%';";
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            Films user;
            while (rs.next())
            {
                user = new Films(rs.getInt("Fid") , rs.getString("fname") , rs.getInt("price") ,rs.getString("type"), rs.getInt("year") , rs.getString("audence") , rs.getFloat("rating") , rs.getInt("Quantity"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }


    
    
    
     
     
     
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
         ObservableList<Films> userlist ; 
         
         userlist = userlist() ; 
         
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                year.setCellValueFactory(new PropertyValueFactory<>("year") );
                type.setCellValueFactory(new PropertyValueFactory<>("type") );
                rating.setCellValueFactory(new PropertyValueFactory<>("rating") );
                audence.setCellValueFactory(new PropertyValueFactory<>("audence") );
                table.setItems(userlist);
                
               
    }
   
   
    
}

