package hust.soict.ite6.oop.aims.screen;

import java.io.IOException;
import java.util.Arrays;

import hust.soict.ite6.oop.aims.controller.CartScreenController;
import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Track;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CartScreen extends Application {
    private static Cart cart;
    private static Store store;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/cart.fxml"));
            
            // Set controller with cart
            CartScreenController controller = new CartScreenController(cart,store);
//            loader.setController(controller);
            
            // Load the root layout from the FXML
            Parent root = loader.load();
            
            // Set up the scene
            Scene scene = new Scene(root);
            primaryStage.setTitle("Cart");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
    	try {
    		// Create a new cart
    		cart = new Cart();

            // Create sample media items
            DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
            DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
            DigitalVideoDisc dvd3 = new DigitalVideoDisc("Titanic", "Romance", 22.0f, "James Cameron", 195);

            // Add DVDs to cart
            cart.addMedia(dvd1, dvd2, dvd3);

            // Create and add CD to cart
            CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
            cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
            cd.addTrack(new Track("Thriller", 7));
            cd.setLength(cd.getLength());
            cart.addMedia(cd);

            // Create and add Book to cart
            Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
            cart.addMedia(book);

            // Launch the JavaFX application
            launch(args);
    	}
    	catch (CartFullException | MediaAlreadyInCartException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}