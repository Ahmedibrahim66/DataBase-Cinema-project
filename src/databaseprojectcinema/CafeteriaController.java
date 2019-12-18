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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;






public class CafeteriaController implements Initializable {
     
                   LocalDate ld = LocalDate.now();
		Date today = java.sql.Date.valueOf(ld);
                
           
                
    public double sum=0; 
     public int max_id ;
    
     public double exp; 
    @FXML
    private ComboBox<String> combo1;
     @FXML
    private ComboBox<Integer> combo2;
      @FXML
    private TableView<order>  table ;
       @FXML
    private TableColumn<?, ?> pname;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private TableColumn<?, ?> total; 
          @FXML
    private Button reset;
     
   
     @FXML
    private Button add; 
      @FXML
    private Button sell;
       @FXML
    private Button test;
         @FXML
    private Button del;
          @FXML
    private Button refund;
           @FXML
    private Label sumL;
       @FXML
          private void handleButtonActionback(ActionEvent event) throws IOException {
        String go = "SellerMan.fxml"; 
               if (LoginPageController.user <100) 
         go = "ManagerScreen.fxml" ; 
          Stage stage = (Stage) test.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource(go));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }
       @FXML
          private void comboAction(ActionEvent event) throws IOException {
       
          combo2.setValue(1);
    
    }      
                 @FXML
          private void handleButtonActionRefund(ActionEvent event) throws IOException {
       
         String h= JOptionPane.showInputDialog(null, "refund amount" , "Error - Refund Amount",JOptionPane.YES_NO_OPTION);
         double amount = Double.parseDouble(h); 
          Connection con = null ;
             try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           String query = "INSERT INTO tbill2(bill,total,exp,date) VALUES('"+(getMax_bill()+1) +"','"+(-amount)+"','"+0+"' , '"+today+"')";
            Statement st;
            
              st = con.createStatement();
              st.execute(query);
             }catch(Exception ex){
                  System.out.println(ex);
             }
    
    }
 
    @FXML 
     public void fill_combo(){    
Connection con = null ; 
 try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT p_name FROM rest_prod";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            while (rs.next())
            {
               combo1.getItems().add(rs.getString(1)); 
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
     }
 
     
    
    
     
       @FXML 
     public void setOnActionSell(ActionEvent event) throws IOException{
          
      if (sum != 0 ){
          int flag = JOptionPane.showConfirmDialog(null, "Confirm?", "Confirm", JOptionPane.YES_NO_OPTION); 
      
              if(flag ==0 ){
           Connection con = null ;
             try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           String query = "INSERT INTO tbill2(bill,total,exp,date) VALUES('"+(getMax_bill()+1) +"','"+sum+"','"+exp+"', '"+ today +"')";
            Statement st;
            
              st = con.createStatement();
              st.execute(query);
             
              setOnActionReset( event);
             
              exp = 0 ; 
              sum = 0 ; 
            

         } catch (SQLException ex) {
              Logger.getLogger(CafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
              
         }
          }
      }
     else if(sum == 0 ) JOptionPane.showMessageDialog(null , "No items to be recorded"); 
          
          
          
     }
       @FXML 
     public void setOnActionReset(ActionEvent event) throws IOException{
          
         
             
         Connection con = null ;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           
           int testt = (getMax_bill()+1);;
           
          
            
           String query = "DELETE FROM tbill WHERE (bill = '"+testt+"') ";
           
           Statement st;
           st = con.createStatement();
           st.execute(query); 
           
           JOptionPane.showMessageDialog(null , "Done");
           sum = 0;
           exp = 0 ; 
           sumL.setText(""+sum+"");
            ObservableList<order> userlist ; 
         
                userlist = userlist() ; 
         
        
        
              total.setCellValueFactory(new PropertyValueFactory<>("Total") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Price") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
               
           table.setItems(userlist);
          
                

           
        } catch (SQLException ex) {
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }


          
          
          
          
     }
     
     
     
     
     
     
      @FXML
     private void deletebutton(ActionEvent event) throws IOException { 
          
           Connection con = null ; 
           try {
        order p = table.getSelectionModel().getSelectedItem();
        
      
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = p.getPid();
           int testt = (getMax_bill()+1);;
           sum = sum - p.getTotal(); 
           exp = exp - p.getCost();
           sumL.setText(""+sum+"");
          
            
           String query = "DELETE FROM tbill WHERE ((bill = '"+testt+"') AND (prod ='"+test+"'))";
           
           Statement st;
           st = con.createStatement();
           st.execute(query); 
           
         //  JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<order> userlist ; 
         
                userlist = userlist() ; 
         
        
        
              total.setCellValueFactory(new PropertyValueFactory<>("Total") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Price") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
               
           table.setItems(userlist);
          
                

           
        } catch (Exception ex) {
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "Please Select the Item to Be Deleted");
        }

        
          
    
    }
        @FXML 
     public void setOnActionAdd(ActionEvent event) throws IOException, SQLException{
          String name; 
   
          int quantity1; 
          try{
          quantity1 = combo2.getValue();
         
     name = combo1.getValue() ;
      
          try{
     
     
      
          order p = new order(name  , quantity1); 
          max_id = (getMax_bill()+1);
          
          exp = exp + p.getCost(); 
          sum = sum + p.getTotal() ; 
          sumL.setText(""+sum+"");
      Connection con = null ;
             
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            try{
                 String query = "INSERT INTO tbill VALUES('"+max_id +"','"+p.getPid()+"','"+p.getQuantity()+"')";
              
            Statement st;
              st = con.createStatement();
              st.execute(query);
            }catch(Exception ex){
             String query = "UPDATE tbill SET quantity = (quantity +'"+quantity1+"') Where ((bill = '"+max_id+"') AND (prod = '"+p.getPid()+"'))";
           
            Statement st;
              st = con.createStatement();
              st.execute(query);
            }

              
           
              
                ObservableList<order> userlist ; 
         
                userlist = userlist() ; 
           total.setCellValueFactory(new PropertyValueFactory<>("Total") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Price") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
        
        
           
          table.setItems(userlist);
        
                
              
              
         } catch (SQLException ex) {
             
              
              
         }  
          } catch(Exception ex ){   
               
               JOptionPane.showMessageDialog(null , "Error in Selecting Product");
              
         }
                       

          
                      
          
          
          
     }
       
      public ObservableList<order> userlist(){
        
        Connection con = null ;
        ObservableList<order> userlist =  FXCollections.observableArrayList() ; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM tbill WHERE bill = '"+( getMax_bill()+1)+"'";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            order user;
            while (rs.next())
            {
                 user = new order(rs.getInt("prod") , rs.getInt("quantity") );
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(View_prodController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }
     
     
    
     
     
     @Override
     public void initialize(URL url, ResourceBundle rb) {
          
          for(int i = 1 ; i <=30 ; i++)
          combo2.getItems().add(i);
          
          fill_combo();
          
          ObservableList<order> userlist ; 
 
         
         userlist = userlist() ; 
         
        
        
try{
             total.setCellValueFactory(new PropertyValueFactory<>("Total") );
		pname.setCellValueFactory(new PropertyValueFactory<>("Pname") );
		price.setCellValueFactory(new PropertyValueFactory<>("Price") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
               
           table.setItems(userlist);
          
          
          
          
     }catch(Exception ex){
          System.out.println(ex);
     }   
     
     
     }  
     
public int getMax_bill(){
   Connection con = null ; 
    int max =0; 
         try { con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT Max(bill) FROM tbill2";
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
     
     
}
