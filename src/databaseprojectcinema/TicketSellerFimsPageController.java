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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fcbar
 */
public class TicketSellerFimsPageController implements Initializable {


 @FXML
    private Button back;

    @FXML
    private Button SellTicket;

    @FXML
    private TextField selltf;

    @FXML
    private Button RefundTicket;

    @FXML
    private TextField refundtf;

    
    
    @FXML
    private TableView<Shows> table;

    @FXML
    private TableColumn<?, ?> Fid;

    
    
    @FXML
    private TableColumn<?, ?> fname;

    @FXML
    private TableColumn<?, ?> price;
    
    @FXML
    private TableColumn<?, ?> sd;
    
     @FXML
    private TableColumn<?, ?> sid;

    @FXML
    private TableColumn<?, ?> Quantity;
    
    
    
    Stage stage;
    Parent root;
    Scene scene;
    Connection con = null;
    

    
      @FXML
    private void backbutton(ActionEvent event) throws IOException {
       
    String go = "SellerMan.fxml" ; 
    if (LoginPageController.user <100) 
         go = "ManagerScreen.fxml" ; 
        stage = (Stage) back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(go));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }   
    
    
    
    
     @FXML
    void SellTicketButton(ActionEvent event) {
        try{
         Shows person1 = table.getSelectionModel().getSelectedItem();
         int test = person1.getSid();
         
         
         
         int QuantitySold = Integer.parseInt(selltf.getText());
         
         int TicketLeft = person1.getQuantity();
         int newTicket = TicketLeft - QuantitySold;

         
         int TotalPrice = person1.getPrice()*QuantitySold;
         
         
         
         if(newTicket < 0 )
         {
           JOptionPane.showMessageDialog(null , "Not Enough Tickets");
         }else 
         {
             
             
              try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
             
             String query = "update Shows1\n" +
"set\n" +

"Quantity = '"+newTicket+"'\n" +
"where  Sid = '"+test+"'";
          
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           JOptionPane.showMessageDialog(null , " "+QuantitySold+" Have Been Sold \n price is "+TotalPrice+" ");
           
           
            ObservableList<Shows> userlist ; 
         
                userlist = userlist() ; 
         
                        sid.setCellValueFactory(new PropertyValueFactory<>("sid") );

                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                sd.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                        
                
                ZoneId zonedId = ZoneId.of( "America/Montreal" );
                LocalDate today = LocalDate.now( zonedId );
                

           
             String query1 =  "INSERT INTO soldtickets (fname,price,date)\n" +
"VALUES ('"+person1.getFname()+"','"+TotalPrice+"','"+today+"')";          
           
           Statement st1;
           st1 = con.createStatement();
           st1.executeUpdate(query1); 
           
            String query2 =  "INSERT INTO totalSales (TickRev,Date)\n" +
"VALUES ('"+person1.getFname()+"','"+TotalPrice+"','"+today+"')";          
           
           
          

           
        } catch (SQLException ex) {
            Logger.getLogger(AddFilmController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
       
         }
         
          }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you want...");
         } 
         

    }
    
    
     @FXML
    void RefundTicketButton(ActionEvent event) {
        try{
        
        Shows person1 = table.getSelectionModel().getSelectedItem();
        int test = person1.getSid();
        int QuantityRefund = Integer.parseInt(refundtf.getText());
        int TicketLeft = person1.getQuantity();
        int newTicket = TicketLeft + QuantityRefund;
        int TotalPrice = person1.getPrice()*QuantityRefund;
        
        
         if(newTicket > 50 )
         {
           JOptionPane.showMessageDialog(null , "Wrong Number of Tickets");
         }else 
         {
             
             
              try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
             
             String query = "update Shows1\n" +
"set\n" +

"Quantity = '"+newTicket+"'\n" +
"where  Sid = '"+test+"'";
          
           
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
           JOptionPane.showMessageDialog(null , " "+QuantityRefund+" Have Been Sold \n price is "+TotalPrice+" ");
           
           
                ZoneId zonedId = ZoneId.of( "America/Montreal" );
                LocalDate today = LocalDate.now( zonedId );
                

                   int TotalPrice1 = person1.getPrice()*QuantityRefund*-1;
                   

                   
             String query1 =  "INSERT INTO soldtickets (fname,price,date)\n" +
"VALUES ('"+person1.getFname()+"','"+TotalPrice1+"','"+today+"')";          
           
           Statement st1;
           st1 = con.createStatement();
           st1.executeUpdate(query1); 
           
           
           
           
                       ObservableList<Shows> userlist ; 
         
                userlist = userlist() ; 
         
        
        
                Fid.setCellValueFactory(new PropertyValueFactory<>("Fid") );
		fname.setCellValueFactory(new PropertyValueFactory<>("fname") );
		price.setCellValueFactory(new PropertyValueFactory<>("price") );
                Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                sd.setCellValueFactory(new PropertyValueFactory<>("Date") );
                table.setItems(userlist);
                        

       
           

           
        } catch (SQLException ex) {
            Logger.getLogger(AddFilmController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
       
         }
        
        

          }catch(Exception ex){
              JOptionPane.showMessageDialog(null , "No item Selected.... \nuse the mouse to select the line you...");
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
                user = new Shows(rs.getInt("sid") , rs.getInt("Fid") , rs.getString("fname") , rs.getInt("price") , rs.getInt("Quantity"), rs.getDate("Date"));
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
