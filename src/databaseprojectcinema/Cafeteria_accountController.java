
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Cafeteria_accountController implements Initializable {
       @FXML
    private Button  back;
         @FXML
    private Button  set;
         @FXML
    private Label  total;
         @FXML
    private Label  exp;
         @FXML
    private Label  tsales;
        @FXML
    private Label  sold_p;
             @FXML
    private TableView<report_product>  table ;

    @FXML
    private TableColumn<?, ?> pname;

    @FXML
    private TableColumn<?, ?> quantity;
        @FXML
    private DatePicker fpicker;
            @FXML
    private DatePicker tpicker;
       
       
        @FXML
    private void handleButtonActionback(ActionEvent event) throws IOException {
       
            Stage stage = (Stage) back.getScene().getWindow();
            Object root = FXMLLoader.load(getClass().getResource("mangReport.fxml"));
            Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
        }
             @FXML
    private void handleButtonActionSet1(ActionEvent event) throws IOException, SQLException {
         fpicker.setValue(null);
         tpicker.setValue(null);
         handleButtonActionSet( event);
    }
           @FXML
    private void handleButtonActionSet(ActionEvent event) throws IOException, SQLException {
       
            table.setVisible(true);
            sold_p.setVisible(true);
            double tot = 0 , exp1 = 0  ; 
            Connection con = null ; 
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            
            String query = "SELECT SUM(total) , SUM(exp) FROM (tbill2 t) ";
				if (fpicker.getValue() != null && tpicker.getValue() == null) {
					java.sql.Date picker = java.sql.Date.valueOf(fpicker.getValue());
					query += "WHERE (t.date >= '" + picker + "')";
				} else if (fpicker.getValue() == null && tpicker.getValue() != null) {
					java.sql.Date picker = java.sql.Date.valueOf(tpicker.getValue());
					query += "WHERE (t.date  <= '" + picker + "')";
				} else if (fpicker.getValue() != null && tpicker.getValue() != null) {
					java.sql.Date picker1 = java.sql.Date.valueOf(fpicker.getValue());
					java.sql.Date picker2 = java.sql.Date.valueOf(tpicker.getValue());
					query += "WHERE (( t.date >= '" + picker1
							+ "') AND (t.date <= '" + picker2 + "'))";
				}
		  Statement st;
           st = con.createStatement();
           
          ResultSet rs= st.executeQuery(query); 
          
                        
				if (rs.next()) {
                             tot = rs.getDouble(1);
					tsales.setText(""+tot+"");
                              exp1 = rs.getDouble(2);
                                   exp.setText("(" + exp1 + ")");
                              }
                             
                         total.setText(""+ (tot - exp1) + " (nis)") ; 
                 
                        
                
                  ObservableList<report_product> userlist1 ;
                         userlist1 = userlist();
                          pname.setCellValueFactory(new PropertyValueFactory<>("Name") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                 
                table.setItems(userlist1);
                
                        
                        
	}
            
             public ObservableList<report_product> userlist(){
       
        
        ObservableList<report_product> userlist =  FXCollections.observableArrayList() ; 
       try { 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
             String query = " Select p_name , sum(t1.quantity) as quantity "
                   + "from tbill t1 , tbill2 t2 , rest_prod p "
                   + "where (t1.bill = t2.bill) and (p.product_id =t1.prod) ";
				if (fpicker.getValue() != null && tpicker.getValue() == null) {
					java.sql.Date picker = java.sql.Date.valueOf(fpicker.getValue());
					query += "And (t2.date >= '" + picker + "')";
				} else if (fpicker.getValue() == null && tpicker.getValue() != null) {
					java.sql.Date picker = java.sql.Date.valueOf(tpicker.getValue());
					query += "and (t2.date  <= '" + picker + "')";
				} else if (fpicker.getValue() != null && tpicker.getValue() != null) {
					java.sql.Date picker1 = java.sql.Date.valueOf(fpicker.getValue());
					java.sql.Date picker2 = java.sql.Date.valueOf(tpicker.getValue());
					query += "and ( t2.date >= '" + picker1
							+ "') AND (t2.date <= '" + picker2 + "')";
				}
                        query += "Group By p_name";
            
            
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
          
            report_product user;
            while (rs.next())
            { 
                user = new report_product(rs.getString("p_name") , rs.getInt("quantity") );
              
                userlist.add(user);
                
            }
             
        } catch (SQLException ex) {
            
            Logger.getLogger(Cafeteria_accountController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }       
            
          
     @Override
     public void initialize(URL url, ResourceBundle rb) {
        
            
          table.setVisible(true);
            sold_p.setVisible(true);
            double tot = 0 , exp1 = 0  ; 
            Connection con = null ; 
           try {
               con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
                 String query = "SELECT SUM(total) , SUM(exp) FROM (tbill2 t) ";
				if (fpicker.getValue() != null && tpicker.getValue() == null) {
					java.sql.Date picker = java.sql.Date.valueOf(fpicker.getValue());
					query += "WHERE (t.date >= '" + picker + "')";
				} else if (fpicker.getValue() == null && tpicker.getValue() != null) {
					java.sql.Date picker = java.sql.Date.valueOf(tpicker.getValue());
					query += "WHERE (t.date  <= '" + picker + "')";
				} else if (fpicker.getValue() != null && tpicker.getValue() != null) {
					java.sql.Date picker1 = java.sql.Date.valueOf(fpicker.getValue());
					java.sql.Date picker2 = java.sql.Date.valueOf(tpicker.getValue());
					query += "WHERE (( t.date >= '" + picker1
							+ "') AND (t.date <= '" + picker2 + "'))";
				}
                        		  Statement st;
        
                                st = con.createStatement();
                ResultSet rs= st.executeQuery(query); 
          
                        
				if (rs.next()) {
                             tot = rs.getDouble(1);
					tsales.setText(""+tot+"");
                              exp1 = rs.getDouble(2);
                                   exp.setText("(" + exp1 + ")");
                              }
                             
                         total.setText(""+ (tot - exp1) + " (nis)") ; 
                 
                        
                
                  ObservableList<report_product> userlist1 ;
                         userlist1 = userlist();
                          pname.setCellValueFactory(new PropertyValueFactory<>("Name") );
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity") );
                 
                table.setItems(userlist1);
                
                                
           } catch (SQLException ex) {
               Logger.getLogger(Cafeteria_accountController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
          
           
           
         
     }     
     
}
