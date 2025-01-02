package hust.soict.ite6.oop.aims.controller;

import java.awt.image.TileObserver;

import hust.soict.ite6.oop.aims.exception.MediaAlreadyInStoreException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.exception.StoreFullException;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookToStoreController {
    private Book newBook;
	
	private Store store;
	
	@FXML
    private TextField titleField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField costField;

    @FXML
    private TextField authorField;
    
    @FXML
    private Button addButton;
    
    @FXML
    public void handleAddBook() throws MediaNotFoundException, StoreFullException, MediaAlreadyInStoreException {
    	
    	String title = titleField.getText();
    	String category = categoryField.getText();
    	String costText = costField.getText(); 
    	String authorText = authorField.getText();
    	
    	
    		
        // Handle logic to add a book
    	newBook = new Book(title);
        System.out.println("Adding Book: " + title);
        try {
        	float cost = Float.parseFloat(costText);
        	newBook.setCost(cost);
        	newBook.setCategory(category);
        	
        	newBook.setAuthors(newBook.addAuthorsDevidedByComma(authorText)); 
        	
        	// Kiểm tra tính hợp lệ của thông tin nhập vào
        	if (title == null || title.trim().isEmpty() ||
        		    category == null || category.trim().isEmpty() ||
        		    costText == null || costText.trim().isEmpty() ||
        		    authorText == null  ) {
    	    		//alert
        			Alert alert = new Alert(AlertType.ERROR);
    	            alert.setTitle("Input Error");
    	            alert.setHeaderText("Insufficient Information");
    	            alert.setContentText("Please fill all fields.");
    	            alert.showAndWait();
        		    return;
        		}
            
        	
        	if (store != null) {
        		if (store.search(title) != null) {
        			
        			// Thông báo đã có rồi , hoặc store đầy
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("store full / Book Existed");
                    alert.setContentText("Book "+title+" cant be added to store.");
                    
                    alert.showAndWait();
                    return;
        		}
        	}
        	store.addMedia(newBook);
        	
    		// Thông báo thành công
    		Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText(null);
            alert.setContentText("Book "+title+" added to store successfully.");
            
            alert.showAndWait();
            return;
        	
        }
        catch (NumberFormatException e) {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Cost Value");
            alert.setContentText("Please enter a valid number for the cost.");
            alert.showAndWait();
        }
        
        
    }
    
    // Phương thức để set giỏ hàng (store) cho controller
    public void setStore(Store store) {
        this.store = store;
    }
    
    public Store getStore(Store store) {
    	return this.store;
    }
}
