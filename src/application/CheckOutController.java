package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

public class CheckOutController implements Initializable {
	@FXML
	private MenuItem exit;
	private static Database library;
	private final ObservableList<Book> checkoutData = FXCollections.observableArrayList();
	// Event Listener on MenuItem[#exit].onAction
    private Button AdvSearch, Toplist, CheckOut, GoBack, MyBooks, removeSelected, borrowBooks, IDScan;
    private int cardID;
 	@FXML TextField IDScanText, showNameField;

	@FXML private TableView<Book> checkoutTable;
	@FXML private TableColumn<Book, String> TitleCol;
	@FXML private TableColumn<Book, String> AuthorCol;
	@FXML private TableColumn<Book, String> GenreCol;
	@FXML private TableColumn<Book, Integer> PagesCol;
	@FXML private TableColumn<Book, String> PublisherCol;
	@FXML private TableColumn<Book, Long> ISBNCol;
	@FXML private TableColumn<Book, Integer> QuantityCol;
	@FXML private TableColumn<Book, Integer> Book_idCol;
	
	@FXML
	public void exitProgram(ActionEvent event) {
		// TODO Autogenerated
	}
	    
	@FXML
	void borrowBooksButton(ActionEvent event) throws Exception {
		
		
		try(Database db = new Database()) {
			String borrowSuccess = db.addBorrowedList(Integer.valueOf(IDScanText.getText()), 4);
			Alert addBook = new Alert(AlertType.INFORMATION);
			addBook.setTitle("Borrow Books");
			addBook.setHeaderText(null);
			addBook.setContentText(borrowSuccess);
			addBook.showAndWait();
		}
		
	}
	    
	@FXML 
	void  IDScanButton(ActionEvent event) throws Exception{
		cardID =  Integer.valueOf(IDScanText.getText());
		try (Database db = new Database()) {
		Customer current = db.getCustomer(cardID);
		showNameField.setText(current.getName());
		}
	}
	    
	@FXML
	void removeSelectedBook(ActionEvent event) throws SQLException, Exception {
		Book removal = checkoutTable.getSelectionModel().getSelectedItem();
		checkoutTable.getItems().remove(removal);
		Main.checkoutData.removeFromCheckout(removal);

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
	
	/*static void setList(ArrayList<Book> list) {
		checkoutList = list;
	}
	
	public ObservableList<Book> getCheckout() throws SQLException{
		
		ObservableList<Book> book = FXCollections.observableArrayList();
		for(int i = 0; i < checkoutList.size(); i++) {
			book.add(checkoutList.get(i));
		}
		//book.addAll(checkoutList);
		return book;
	}*/
	public ObservableList<Book> getCheckoutData() {
		return checkoutData;
	}
	public void setCheckoutData() {
		
		ArrayList<Book> checkoutList = Main.checkoutData.getCheckoutList();
		int size = checkoutList.size();
		for(int i = 0; i < checkoutList.size(); i++) {
			checkoutData.add(checkoutList.get(i));
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		//set up the columns in the table
		TitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		AuthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		GenreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
		PublisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
		PagesCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("pages"));
		ISBNCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("isbn"));
		QuantityCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));
		
		/*try {
			library = new Database();*/
			setCheckoutData();
			checkoutTable.setItems(getCheckoutData());
			
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	void initData(ArrayList<Book> list) throws SQLException {
		
		for(int i = 0; i < list.size(); i++) {
			checkoutData.add(list.get(i));
		}
	}
}
