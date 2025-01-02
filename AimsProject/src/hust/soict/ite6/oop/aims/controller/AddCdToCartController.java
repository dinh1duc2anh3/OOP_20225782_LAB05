package hust.soict.ite6.oop.aims.controller;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCdToCartController {
    private CompactDisc newCd;
	
	private Cart cart;
	
	@FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField costField;
    
    @FXML
    private TextField artistField;
    
    @FXML
    private TextField directorField;

    @FXML
    private TextField lengthField;
    
    @FXML
    private Button addTrackButton;
    
    
    @FXML
    public void handleAddCd() throws MediaNotFoundException, CartFullException, MediaAlreadyInCartException {
    	
    	String title = titleField.getText();
    	String category = categoryField.getText();
    	String costText = costField.getText(); 
    	String artist = artistField.getText();
    	String director = directorField.getText();
    	String lengthText = lengthField.getText();
    	
    	
    	// Kiểm tra tính hợp lệ của thông tin nhập vào
    	if (title == null || title.trim().isEmpty() ||
    		    category == null || category.trim().isEmpty() ||
    		    costText == null || costText.trim().isEmpty() ||
    		    director == null || director.trim().isEmpty() || 
    		    artist == null || artist.trim().isEmpty() ||
    		    lengthText == null || lengthText.trim().isEmpty() 
    		    ) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Input Error");
	            alert.setHeaderText("Insufficient Information");
	            alert.setContentText("Please fill all fields.");
	            alert.showAndWait();
    		    return;
    		}
        
        // Handle logic to add a book
    	newCd = new CompactDisc(title);
        System.out.println("Adding Cd: " + title);
        try {
        	float cost = Float.parseFloat(costText);
        	int length = Integer.parseInt(lengthText);
        	newCd.setLength(length);
        	newCd.setCost(cost);
        	newCd.setCategory(category);
        	newCd.setDirector(director);
        	newCd.setArtist(artist);
        	
        	if (cart != null) {
        		if (cart.search(title) == null) {
        			cart.addMedia(newCd);
            		// Thông báo thành công
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successful");
                    alert.setHeaderText("...");
                    alert.setContentText("CD "+title + " added to cart successfully.");
                    
                    alert.showAndWait();
        		}
        		
        		else {
        			// Thông báo đã có rồi , hoặc cart đầy
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Cart full / CD Existed");
                    alert.setContentText("CD "+title+" cant be added to cart.");
                    
                    alert.showAndWait();
        		}
        	}
        	
        }
        catch (NumberFormatException e) {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Cost / Length Value");
            alert.setContentText("Please enter a valid number for the cost / length.");
            alert.showAndWait();
        }
        
        
    }
    
    
    // Phương thức để set giỏ hàng (Cart) cho controller
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
