package application;




import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.glass.ui.Accessible.EventHandler;
import com.sun.glass.ui.MenuBar;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;

public class MyViewController implements Initializable {

	Book book;
	AdvancedSearchController advsearch;
	static Database library;
	String searchResultText;	
	private static String textSearch;
	private static String searchCategory = "title";

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		MyViewController.textSearch = textSearch;
	}

	public  String getSearchCategory() {
		return searchCategory;
	}

	public  void setSearchCategory(String searchCategory) {
		MyViewController.searchCategory = searchCategory;
	}

	@FXML
	private ToggleGroup RadioOption;
	@FXML
	private RadioButton RadioAuthor, RadioTitle;
	@FXML
	private Button Toplist, AdvSearch, CheckOut, SearchButton, MyBooks, GoToAdminLogin;
	@FXML
	private AnchorPane rootView;
	@FXML
	private MenuItem exit, EnterAdminLogin;
	@FXML TextField Search;

	@FXML
	void Search(ActionEvent event) {
		advsearch.setTextSearch(Search.getText()) ;
	}

	@FXML
	void GoToAdminLogin(ActionEvent event) throws IOException {
		Parent Admin_Login_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene Admin_Login_scene = new Scene(Admin_Login_parent);

		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();

		app_stage.setScene(Admin_Login_scene);
		app_stage.show();
	}





  @FXML 
  public void onEnter(ActionEvent ae) throws IOException {
	  SearchButton(ae);
  }
	@FXML
	void SearchButton(ActionEvent event) throws IOException{
		setTextSearch(Search.getText());

		if(RadioTitle.isSelected()) {
			setSearchCategory("title");
		}
		else if(RadioAuthor.isSelected()) {
			setSearchCategory("author");
		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdvancedSearch.fxml"));
		Parent root = (Parent) loader.load();

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(new Scene(root));
		stage.show();
		/*
    	Parent Advanced_Search_parent = FXMLLoader.load(getClass().getResource("AdvancedSearch.fxml"));
    	Scene Advanced_Search_scene = new Scene(Advanced_Search_parent);

    	Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();

    	app_stage.setScene(Advanced_Search_scene);
    	app_stage.show();
		 */
	}

	@FXML
	void exitProgram(ActionEvent event) {
		Platform.exit();

	}

	@FXML
	void AdvSearch(ActionEvent event) throws IOException {
		Parent Advanced_Search_parent = FXMLLoader.load(getClass().getResource("AdvancedSearch.fxml"));
		Scene Advanced_Search_scene = new Scene(Advanced_Search_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(Advanced_Search_scene);
		app_stage.show();

	}
	
	@FXML
	void EnterMyBorrowedBooks(ActionEvent event) throws IOException {
		Parent My_Books_parent = FXMLLoader.load(getClass().getResource("MyBooks.fxml"));
		Scene My_Books_scene = new Scene(My_Books_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(My_Books_scene);
		app_stage.show();
		
	}

	@FXML
	void GoToToplist(ActionEvent event) throws IOException {
		Parent Toplist_parent = FXMLLoader.load(getClass().getResource("Toplist.fxml"));
		Scene Toplist_scene = new Scene(Toplist_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(Toplist_scene);
		app_stage.show();

	}

	@FXML
	void GoToCheckOut(ActionEvent event)  throws IOException {
		Parent CheckOut_parent = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
		Scene CheckOut_scene = new Scene(CheckOut_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(CheckOut_scene);
		app_stage.show();

	}
	
	@FXML
	void EnterAdminLogin(ActionEvent event) throws IOException {
		Parent CheckOut_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene CheckOut_scene = new Scene(CheckOut_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();

		app_stage.setScene(CheckOut_scene);
		app_stage.show();
	}

	public void RadioButtons() {
		ToggleGroup toggleGroup = new ToggleGroup();

		RadioAuthor.setToggleGroup(toggleGroup);
		RadioTitle.setToggleGroup(toggleGroup);

	}
	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			library = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
