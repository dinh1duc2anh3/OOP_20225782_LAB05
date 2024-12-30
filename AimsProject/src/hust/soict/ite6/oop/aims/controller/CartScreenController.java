package hust.soict.ite6.oop.aims.controller;

import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.media.Playable;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartScreenController {
	private Cart cart;
	
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn< Media, String> colMediaCategory;
	
	@FXML
	private TableColumn<Media, Float> colMediaCost;
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnRemove;
	
    @FXML
    private MenuItem addBookMenuItem;
	
    @FXML
    private MenuItem addDvdMenuItem;
    
    @FXML
    private MenuItem addCdMenuItem;
    
	@FXML 
	private Label totalCostLabel;
	
	public CartScreenController(Cart cart) {
		super();
		this.cart = cart;
	}
	
	@FXML
	private void initialize() {
	    colMediaTitle.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("title"));
	    colMediaCategory.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("category"));
	    colMediaCost.setCellValueFactory(
	        new PropertyValueFactory<Media, Float>("cost"));
	    tblMedia.setItems(this.cart.getItemsOrdered());

	    btnPlay.setVisible(false);
	    btnRemove.setVisible(false);

	    tblMedia.getSelectionModel().selectedItemProperty().addListener(
	        (observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                updateButtonBar(newValue);
	            }
	        }
	    );
	    
	    //update total cost label
	    updateTotalCost();
	    cart.getItemsOrdered().addListener((ListChangeListener<Media>) c-> {
	    	updateTotalCost();
	    });
	    
	    
	}
	
	private void updateTotalCost() {
		totalCostLabel.setText(cart.totalCost()+"$");
	}
	
	private void updateButtonBar(Media media) {
	    btnRemove.setVisible(true);
	    if (media instanceof Playable) {
	        btnPlay.setVisible(true);
	    } else {
	        btnPlay.setVisible(false);
	    }
	}
	
	@FXML
	private void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
	}
	
	@FXML
	private void btnPlaceOrderPressed(ActionEvent event) {
		updateTotalCost();
    	cart.clear();
	}
	
	@FXML
	private void btnPlayPressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		if  ( media instanceof Playable) {
			showPlayDialog(media);
		}
	}
	
	
	public void showPlayDialog(Media media) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Playing Media");
        alert.setHeaderText(null);
        alert.setContentText("Playing " + media.getTitle());
        
        alert.showAndWait();
	}
	
	public void handleAddBook(ActionEvent event) {
		try {
			// Mở màn hình AddBookToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddBookToCartDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddBookToCartController addBookController = loader.getController();
            addBookController.setCart(cart);
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Book window.");
            alert.showAndWait();

		}
	}
	
	public void handleAddDvd(ActionEvent event) {
		try {
			// Mở màn hình AddDvdToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddDvdToCartDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddDvdToCartController addDvdController = loader.getController();
            addDvdController.setCart(cart);
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Dvd");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Dvd window.");
            alert.showAndWait();

		}
	}
	
	public void handleAddCd(ActionEvent event) {
		try {
			// Mở màn hình AddDvdToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddCdToCartDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddCdToCartController addCdController = loader.getController();
            addCdController.setCart(cart);
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Cd");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Cd window.");
            alert.showAndWait();

		}
	}
}
