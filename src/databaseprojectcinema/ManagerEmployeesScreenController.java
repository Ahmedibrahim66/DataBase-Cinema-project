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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 * @author fcbar
 */
public class ManagerEmployeesScreenController implements Initializable {


    Stage stage;
    Parent root;
    Scene scene;
    
    
    static int eid1;
    static String name1;
    static String phone1;
    static Date dob1;
    static Date sd1;
    static String job1;
    

  @FXML
    private Button addfilm;

    @FXML
    private TableView<employees> table1;

    @FXML
    private TableColumn<?, ?> Eid;

    @FXML
    private TableColumn<?, ?> ename;

    @FXML
    private TableColumn<?, ?> sd;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private TableColumn<?, ?> dob;

    @FXML
    private TableColumn<?, ?> job;

    @FXML
    private Button editfilm;

    @FXML
    private Button deletefilm;

    @FXML
    private Button back;

        Connection con = null;

        
    @FXML
    void addemployee(ActionEvent event) throws IOException {

        stage = (Stage) addfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddEmployees.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
        
    }

    @FXML
    void backbutton(ActionEvent event) throws IOException {
        
        
        stage = (Stage) editfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ManagerScreen.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deletebutton(ActionEvent event) {
        try{
        employees person = table1.getSelectionModel().getSelectedItem();
        int flag = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete?", "Confirm", JOptionPane.YES_NO_OPTION); 
        if(flag == 0 ){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = person.getEid();
           
          
            
           String query = "DELETE FROM employees WHERE Eid='"+test+"'";
            String query1 = "DELETE FROM password WHERE Eid='"+test+"'";
           Statement st;
           st = con.createStatement();
           st.executeUpdate(query); 
             st.executeUpdate(query1); 
           JOptionPane.showMessageDialog(null , "delete done");
           
            ObservableList<employees> userlist ; 
         
                userlist = userlist() ; 
         
        
                 Eid.setCellValueFactory(new PropertyValueFactory<>("Eid") );
		ename.setCellValueFactory(new PropertyValueFactory<>("Ename") );
		dob.setCellValueFactory(new PropertyValueFactory<>("dob") );
                phone.setCellValueFactory(new PropertyValueFactory<>("Phone") );
                sd.setCellValueFactory(new PropertyValueFactory<>("sd") );
                
                
                table1.setItems(userlist);
                

           
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
        }
        }catch (Exception ex){
             JOptionPane.showMessageDialog(null , "Please Select The Employee you want to delete... \n By Clicking on the Employee... ");
        }

        
    
    }
     
   

    @FXML
    void editemployee(ActionEvent event) throws IOException {
        
        try{
         employees person1 = table1.getSelectionModel().getSelectedItem();
          
        int eid2 = person1.getEid();
        String name2 = person1.getEname();
        String phone2 = person1.getPhone();
        Date dob2 = person1.getDob();
        Date sd2 = person1.getSd();
        String job2 = person1.getJob();
        
        
        eid1 = eid2;
        name1 = name2;
         phone1 = phone2;
        dob1 = dob2 ;
       sd1 =  sd2  ;
        job1 = job2;
        
        stage = (Stage) editfilm.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EditEmployees.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         }catch (Exception ex){
             JOptionPane.showMessageDialog(null , "Please Select The Employee you want to edit... \n By Clicking on the Employee... ");
        }

        
        
    }
    
    public ObservableList<employees> userlist(){
        
        
        ObservableList<employees> userlist =  FXCollections.observableArrayList() ; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
            String query = "SELECT * FROM employees";
            Statement st;
            st = con.createStatement();
            ResultSet rs= st.executeQuery(query);
            employees user;
            while (rs.next())
            {
                user = new employees(rs.getInt("Eid") , rs.getString("phone")  , rs.getString("ename") ,rs.getDate("dob"), rs.getDate("start_date") , rs.getString("job") , rs.getInt("salary") );
                userlist.add(user);
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(ManagerEmployeesScreenController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        return userlist;            

    }
    
    
    @FXML
    void SalaryButton(ActionEvent event) throws IOException {
        
        try{
    employees person1 = table1.getSelectionModel().getSelectedItem();
        
try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
           
     
           int test = person1.getEid();
           
          
            
           String query = "SELECT salary FROM employees WHERE Eid='"+test+"'";
           

          Statement st=con.createStatement();
ResultSet rs=st.executeQuery(query);
while(rs.next())
{
    
     JOptionPane.showMessageDialog(null , rs.getString(1));
}

    String query1 = "SELECT salary FROM employees ";
        Statement st1=con.createStatement();
        ResultSet rs1=st1.executeQuery(query1);
        
        int salaries= 0 ;
        
while(rs1.next())
{
    salaries = salaries + Integer.parseInt(rs1.getString(1));
    
    
}
              
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFilmsController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "not done");
        }
        }catch (Exception ex){
             JOptionPane.showMessageDialog(null , "Please Select The Employee you want to see the salary... \n By Clicking on the Employee... ");
        }




    
    }
    
    
  @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         ObservableList<employees> userlist ; 
         
         userlist = userlist() ; 
         
        
                Eid.setCellValueFactory(new PropertyValueFactory<>("Eid") );
		ename.setCellValueFactory(new PropertyValueFactory<>("Ename") );
		dob.setCellValueFactory(new PropertyValueFactory<>("dob") );
                phone.setCellValueFactory(new PropertyValueFactory<>("Phone") );
                sd.setCellValueFactory(new PropertyValueFactory<>("sd") );
                job.setCellValueFactory(new PropertyValueFactory<>("job") );

                
                
                table1.setItems(userlist);

        
    }    
    
}
