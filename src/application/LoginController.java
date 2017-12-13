package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	Database library;

	@FXML
	private MenuItem exit;
	@FXML
	private Button GoBack;
	@FXML
	private Button AdminLoginButton;
	@FXML
	private TextField adminUserName;
	@FXML
	private PasswordField adminPassword;


	@FXML
	public void exitProgram(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
	void AdminLoginButton(ActionEvent event) throws SQLException, IOException {
		String userName = adminUserName.getText();
		String password = adminPassword.getText();
		boolean loginChecker = false;
		//boolean loginChecker = library.verifyLogin(userName, password);

		if(library.verifyLogin(adminUserName.getText(), adminPassword.getText())) {
			Parent Admin_parent = FXMLLoader.load(getClass().getResource("AdminStartPage.fxml"));
			Scene Admin_scene = new Scene(Admin_parent);
			Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(Admin_scene);
			app_stage.show();
		}
		else  {
			System.out.println("wrong password");
		}
	}
	
	@FXML
	void GoBack(ActionEvent event) throws IOException {
		Parent  My_View_parent = FXMLLoader.load(getClass().getResource("MyView.fxml"));
		Scene My_View_scene = new Scene(My_View_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(My_View_scene);
		app_stage.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		try {
			library = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
