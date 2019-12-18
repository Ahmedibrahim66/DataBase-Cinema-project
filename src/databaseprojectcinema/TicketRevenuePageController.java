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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class TicketRevenuePageController implements Initializable {

        Connection con = null;

        
   @FXML
    private TableView<TicketRevenue> table;

    @FXML
    private TableColumn<?, ?> bid;

    @FXML
    private TableColumn<?, ?> fname;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Button MonthlyRevenue;

    @FXML
    private Button TodayRevenue;

    @FXML
    private Button YearlyRevenue;

    @FXML
    private DatePicker day;

    @FXML
    private Button back;

    @FXML
    private ComboBox<String> Month;

    @FXML
    private TextField year;

    
    @FXML
    private Button ShowAll;

    @FXML
    private TextField filmname;

    @FXML
    private Button calculate;
    
    
    
    
        
    Stage stage;
    Parent root;
    Scene scene;
    
    
    @FXML
    void MonthRevenue(ActionEvent event) throws SQLException {
        
          ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlistmonth() ; 
         
        
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
                           
        con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"WHERE MONTH(date) = "+Month.getValue()+" ";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                            JOptionPane.showMessageDialog(null , "Total Revenue for " +Month.getValue()+ "  is :- "+ rs.getInt(1));

                
            }
    
        

    }

    @FXML
    void YearRevenue(ActionEvent event) throws SQLException {
        
         ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlistyear(); 
         
        
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
                           
        con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"WHERE YEAR(date) = "+year.getText()+" ";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                            JOptionPane.showMessageDialog(null , "Total Revenue for " +year.getText()+ "  is :- "+ rs.getInt(1));

                
            }
    
        

    }

    @FXML
    void backbutton(ActionEvent event) throws IOException {
        
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("mangReport.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void dayRevenue(ActionEvent event) throws SQLException {

        ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlistday() ; 
         
        
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
                
                
        con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"where date='"+day.getValue()+"'";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
         
                
                
                  while (rs.next())
            {
                            JOptionPane.showMessageDialog(null , "Total Revenue for " +day.getValue()+ "  is :- "+ rs.getInt(1));

                
            }
   
    }
    
    
     @FXML
    void showall(ActionEvent event) {

           ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlist() ; 
         
        
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
                
        
    }
    
    
    
    
        @FXML
    void calculate(ActionEvent event) throws SQLException {

        con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
        
            String query = "SELECT sum(price)\n" +
"FROM soldtickets\n" +
"WHERE fname LIKE '"+filmname.getText()+"%'; ";
        
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            
            int sum = 0;
         
                
                
                  while (rs.next())
            {
                sum = rs.getInt(1);
           
            }
                  
                  
            String query1 = "SELECT fname \n" +
"FROM soldtickets\n" +
"WHERE fname LIKE '"+filmname.getText()+"%'; ";
        
            Statement st1;
            st1 = con.createStatement();
            ResultSet rs1= st1.executeQuery(query1);
            
            String FilmName;
            List<String> al = new ArrayList<>();
            
            
            
            
              
                  while (rs1.next())
            {

                al.add(rs1.getString(1));

            }
                  
                  
                  

            Set<String> hs = new HashSet<>();
            hs.addAll(al);
            al.clear();
            al.addAll(hs);
            
            JOptionPane.showMessageDialog(null , "Total Revenue for " +al+ "  is :- "+ sum);

            
            
                       
            
            
            
             
                  
                  

            
                  
                  
        
    }
    
    
    @FXML
     private void searchbutton(KeyEvent event) throws IOException {
         
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
            
            
            
         ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlist2() ; 
         
        
        
             
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
            
           
           
             } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
         
     }
     
    
     public ObservableList<TicketRevenue> userlist2(){
        
        
        ObservableList<TicketRevenue> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT * FROM soldtickets \n" +
"   WHERE fname LIKE '"+filmname.getText()+"%'; ";
        
            
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TicketRevenue user;
            
            while (rs.next())
            {
                user = new TicketRevenue(rs.getInt("billno") , rs.getString("fname") , rs.getInt("price") ,  rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            
    }
    
    
    public ObservableList<TicketRevenue> userlistyear(){
        
        
        ObservableList<TicketRevenue> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT * FROM soldtickets \n" +
"   WHERE YEAR(date) = "+year.getText()+" ";
        
            
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TicketRevenue user;
            
            while (rs.next())
            {
                user = new TicketRevenue(rs.getInt("billno") , rs.getString("fname") , rs.getInt("price") ,  rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            
    }
    
    
    
     public ObservableList<TicketRevenue> userlistmonth(){
        
        
        ObservableList<TicketRevenue> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = "SELECT * FROM soldtickets \n" +
"   WHERE MONTH(date) = "+Month.getValue()+" ";
        
            
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TicketRevenue user;
            
            while (rs.next())
            {
                user = new TicketRevenue(rs.getInt("billno") , rs.getString("fname") , rs.getInt("price") ,  rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            
    }
    
    public ObservableList<TicketRevenue> userlistday(){
        
        
        ObservableList<TicketRevenue> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM soldtickets \n" +
"where date= '"+day.getValue()+"'";
            
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TicketRevenue user;
            
            while (rs.next())
            {
                user = new TicketRevenue(rs.getInt("billno") , rs.getString("fname") , rs.getInt("price") ,  rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            
    }
    
    
         
    public ObservableList<TicketRevenue> userlist(){
        
        
        ObservableList<TicketRevenue> userlist =  FXCollections.observableArrayList() ; 
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM soldtickets";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            TicketRevenue user;
            
            while (rs.next())
            {
                user = new TicketRevenue(rs.getInt("billno") , rs.getString("fname") , rs.getInt("price") ,  rs.getDate("Date"));
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ObservableList<TicketRevenue> userlist ; 
         
         userlist = userlist() ; 
         
        
                bid.setCellValueFactory(new PropertyValueFactory<>("billno") );        
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Revenue") );
                date.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                
                
                
         Month.getItems().addAll("1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10", "11" , "12");
                        


                
        
    }    
    
}
