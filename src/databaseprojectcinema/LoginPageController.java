package databaseprojectcinema;

import RKinfotech.MysqlMd5;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELIFE
 */
public class LoginPageController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private Button login;

    @FXML
    private TextField id;

    @FXML
    private PasswordField pass;

    
    @FXML
    private ImageView exit;

    
    public static int user;

    @FXML
    public void handle(KeyEvent ke) throws IOException, NoSuchAlgorithmException, SQLException {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            actionlog(ke);
        }
    }

    @FXML
    private void actionback(Event event) throws IOException {
       System.exit(0);
    }

    @FXML
    private void actionlog(Event event) throws IOException, NoSuchAlgorithmException, SQLException {
        try {
            String password = MysqlMd5.getRKmd5(pass.getText());
            user = Integer.parseInt(id.getText());
            String password1 = "";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cinema", "root", "root");
            String query = "select pass from password where eid = '" + user + "'";

            Statement st;
            st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                password1 = rs.getString(1);

            }
            if (password1.equals(password)) {

                Stage stage = (Stage) login.getScene().getWindow();
                Object root = FXMLLoader.load(getClass().getResource(check(user)));
                Scene scene = new Scene((Parent) root);
                stage.setScene(scene);
                stage.show();

            } else {
                error.setText("Invalid Username or Password. ");
            }
        } catch (Exception ex) {

            error.setText("Error in Id Or Password Field. ");
        }

    }

    private String check(int id1) {

        if (id1 < 100) {
            return "ManagerScreen.fxml";
        } else {
            return "SellerMan.fxml";
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
