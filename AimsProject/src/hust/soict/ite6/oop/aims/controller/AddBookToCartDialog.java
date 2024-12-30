package hust.soict.ite6.oop.aims.controller;

import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddBookToCartDialog {
	@FXML
    private Book book;
	
	@FXML
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
    public void handleAddBook() {
        // Handle logic to add a book
        System.out.println("Adding Book: " + titleField.getText());
        book = new Book(titleField.getText(), categoryField.getText() , costField.getText() , authorField.getText());
    }
    
}
