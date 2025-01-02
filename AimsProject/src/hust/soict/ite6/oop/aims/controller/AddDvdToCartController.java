package hust.soict.ite6.oop.aims.controller;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class AddDvdToCartController {
    private DigitalVideoDisc newDvd;
	
	private Cart cart;
	
	@FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField costField;
    
    @FXML
    private TextField directorField;

    @FXML
    private TextField lengthField;
    
    
    
    
    @FXML
    public void handleAddDvd() throws MediaNotFoundException, CartFullException, MediaAlreadyInCartException {
    	
    	String title = titleField.getText();
    	String category = categoryField.getText();
    	String costText = costField.getText(); 
    	String director = directorField.getText();
    	String lengthText = lengthField.getText();
    	
    	
    	// Kiểm tra tính hợp lệ của thông tin nhập vào
    	if (title == null || title.trim().isEmpty() ||
    		    category == null || category.trim().isEmpty() ||
    		    costText == null || costText.trim().isEmpty() ||
    		    director == null || director.trim().isEmpty() || 
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
    	newDvd = new DigitalVideoDisc(title);
        System.out.println("Adding DVD: " + title);
        try {
        	float cost = Float.parseFloat(costText);
        	int length = Integer.parseInt(lengthText);
        	newDvd.setLength(length);
        	newDvd.setCost(cost);
        	newDvd.setCategory(category);
        	newDvd.setDirector(director);
        	
        	if (cart != null) {
        		if (cart.search(title) == null) {
        			cart.addMedia(newDvd);
            		// Thông báo thành công
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successful");
                    alert.setHeaderText("...");
                    alert.setContentText("DVD "+title + " added to cart successfully.");
                    
                    alert.showAndWait();
        		}
        		
        		else {
        			// Thông báo đã có rồi , hoặc cart đầy
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Cart full / DVD Existed");
                    alert.setContentText("DVD "+title+" cant be added to cart.");
                    
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
