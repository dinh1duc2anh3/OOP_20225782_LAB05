package hust.soict.ite6.oop.aims.controller;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddBookToCartController {
    private Book newBook;
	
	private Cart cart;
	
	@FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField costField;

    @FXML
    private TextField authorField;
    
    
    @FXML
    public void handleAddBook() throws MediaNotFoundException, CartFullException, MediaAlreadyInCartException {
    	
    	String title = titleField.getText();
    	String category = categoryField.getText();
    	String costText = costField.getText(); 
    	String authorText = authorField.getText();
    	
    	
    		
    	//tạo danh sách chứa tên tác giả
    	
    	
    	 // Kiểm tra tính hợp lệ của thông tin nhập vào
    	if (title == null || title.trim().isEmpty() ||
    		    category == null || category.trim().isEmpty() ||
    		    costText == null || costText.trim().isEmpty() ||
    		    authorText == null  ) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Input Error");
	            alert.setHeaderText("Insufficient Information");
	            alert.setContentText("Please fill all fields.");
	            alert.showAndWait();
    		    return;
    		}
        
        // Handle logic to add a book
    	newBook = new Book(titleField.getText());
        System.out.println("Adding Book: " + newBook.getTitle());
        try {
        	float cost = Float.parseFloat(costText);
        	newBook.setCost(cost);
        	newBook.setCategory(category);
        	
        	newBook.setAuthors(newBook.addAuthorsDevidedByComma(authorText)); 
        	
        	if (cart != null) {
        		if (cart.search(title) == null) {
        			cart.addMedia(newBook);
            		// Thông báo thành công
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successful");
                    alert.setHeaderText("...");
                    alert.setContentText("Book added to cart successfully.");
                    
                    alert.showAndWait();
        		}
        		
        		else {
        			// Thông báo đã có rồi , hoặc cart đầy
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Cart full / Book Existed");
                    alert.setContentText("Book "+title+" cant be added to cart.");
                    
                    alert.showAndWait();
        		}
        	}
        	
        }
        catch (NumberFormatException e) {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Cost Value");
            alert.setContentText("Please enter a valid number for the cost.");
            alert.showAndWait();
        }
        
        
    }
    
    // Phương thức để set giỏ hàng (Cart) cho controller
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
