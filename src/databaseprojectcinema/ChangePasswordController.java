
package databaseprojectcinema;

import RKinfotech.MysqlMd5;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class ChangePasswordController implements Initializable {
   @FXML
    private Button change; 
     @FXML
    private Button back;
       @FXML
    private PasswordField cp;
         @FXML
    private PasswordField np;
           @FXML
    private PasswordField cnp;
           
                 @FXML
          private void handleButtonActionChange(ActionEvent event) throws IOException {
     int flag = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Change Password?", "Confirm", JOptionPane.YES_NO_OPTION); 
      
              if(flag ==0 ){
               try{ 
          if(np.getText().equals(cnp.getText())){
               try{Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cinema","root", "root");
      Statement st;
         st = con.createStatement();
        String passs = "" ; 
        String query1 = "select pass from password where eid = '"+LoginPageController.user+"' ";
         ResultSet rs = st.executeQuery(query1);
         if(rs.next())
              passs = rs.getString(1) ; 
          if(MysqlMd5.getRKmd5(cp.getText()).equals(passs) ){
           
           String query = "UPDATE password SET pass = '"+MysqlMd5.getRKmd5(np.getText())+"' WHERE eid = '"+LoginPageController.user+"'";
           
              st.execute(query);
              JOptionPane.showMessageDialog(null , "Changed!");
              handleButtonActionback( event);
          }
          else JOptionPane.showMessageDialog(null , "Current Password Entered Does not match");
               }catch(Exception ex){
                  System.out.println(ex);
             }}
          else  JOptionPane.showMessageDialog(null , "Password Does Not Match");
}catch(Exception ex){
                  JOptionPane.showMessageDialog(null , "error in inserting in fields.");
             }
     
          }}
              @FXML
          private void handleButtonActionback(ActionEvent event) throws IOException {
       String s = "SellerMan.fxml" ; 
       if (LoginPageController.user <100)
          s = "ManagerScreen.fxml"; 
               
               Stage stage = (Stage) back.getScene().getWindow();
          Object root = FXMLLoader.load(getClass().getResource(s));
          Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    
    }
     @Override
     public void initialize(URL url, ResourceBundle rb) {
          // TODO
     }     
     
}
