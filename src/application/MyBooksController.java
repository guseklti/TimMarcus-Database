package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyBooksController {
	@FXML
	private MenuItem exit;
	Database library;

	// Event Listener on MenuItem[#exit].onAction
	@FXML
	public void exitProgram(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
	private Button AdvSearch, Toplist, CheckOut, GoBack, MyBooks, enterCardIDButton;

	@FXML
	private TextField nameInfo, IDScan;


	@FXML private TableView<Book> result;
	@FXML private TableColumn<Book, String> TitleCol;
	@FXML private TableColumn<Book, String> AuthorCol;
	@FXML private TableColumn<Book, String> GenreCol;
	@FXML private TableColumn<Book, Integer> PagesCol;
	@FXML private TableColumn<Book, String> PublisherCol;
	@FXML private TableColumn<Book, Long> ISBNCol;
	@FXML private TableColumn<Book, Integer> QuantityCol;
	@FXML private TableColumn<Book, Integer> Book_idCol;


	int IDScanNumber;


	@FXML
	void enterCardIDButton(ActionEvent event) throws SQLException {
		String IDScanString= IDScan.getText();
		IDScanNumber = Integer.valueOf(IDScanString);

		result.setItems(getBorrowedBook());
		Customer current = Main.library.getCustomer(IDScanNumber);
		nameInfo.setText(current.getName());
		
		initialize(null, null);

	}

	@FXML
	void GoBack(ActionEvent event) throws IOException {
		Parent  My_View_parent = FXMLLoader.load(getClass().getResource("MyView.fxml"));
		Scene My_View_scene = new Scene(My_View_parent);
		Stage app_stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(My_View_scene);
		app_stage.show();
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

	public void initialize(URL location, ResourceBundle resources) throws SQLException {


		//set up the columns in the table
		TitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		AuthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		GenreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		PublisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		PagesCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pages"));
		ISBNCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("isbn"));
		QuantityCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));

		/*try {

			//result.setItems(getBorrowedBook());
			library = new Database();

			//Customer current = library.getCustomer(IDScanNumber);
			//nameInfo.setText(current.getName());


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	public ObservableList<Book> getBorrowedBook() throws SQLException{
		ObservableList<Book> book = FXCollections.observableArrayList();
		Database data = new Database();
		//Book [] searchArray=data.getBorrowedBooks(IDScanNumber);

		Book [] searchArray = Main.library.getBorrowedBooks(IDScanNumber);

		for(int i =0; i<searchArray.length; i++) {
			book.add(searchArray[i]);
		}
		return book; 
	}

}