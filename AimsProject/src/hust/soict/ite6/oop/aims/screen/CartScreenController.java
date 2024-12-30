package hust.soict.ite6.oop.aims.screen;

import java.util.Observable;

import hust.soict.ite6.oop.aims.cart.Cart;
import hust.soict.ite6.oop.aims.media.Media;
import hust.soict.ite6.oop.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
}
