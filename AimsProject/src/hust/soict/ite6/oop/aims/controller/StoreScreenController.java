package hust.soict.ite6.oop.aims.controller;

import java.util.Arrays;

import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInStoreException;
import hust.soict.ite6.oop.aims.exception.StoreFullException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StoreScreenController {

    private Store store;
    private Cart cart;

    @FXML
    private GridPane mediaGrid;

    @FXML
    private Button viewCartButton;

    public void initialize() throws StoreFullException, MediaAlreadyInStoreException {
        store = createStore();
        cart = new Cart();
        populateMediaGrid();
    }

    private void populateMediaGrid() {
        int column = 0;
        int row = 0;
        for (Media media : store.getItemsInStore()) {
            MediaStore mediaStore = new MediaStore(media);
            mediaGrid.add(mediaStore, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    @FXML
    private void handleViewCart() {
        // Code to display the cart screen
    }

    private Store createStore() throws StoreFullException, MediaAlreadyInStoreException {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Titanic", "Romance", 22.0f, "James Cameron", 195);
        store.addMedia(dvd1, dvd2, dvd3);

        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
        store.addMedia(cd);

        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        store.addMedia(book);

        return store;
    }

    // MediaStore class to create the display of each media item
    private class MediaStore extends VBox {
        private Media media;

        public MediaStore(Media media) {
            this.media = media;

            setPadding(new Insets(10));
            setSpacing(10);
            setStyle("-fx-border-color: black; -fx-border-width: 1;");

            Label title = new Label(media.getTitle());
            title.setFont(new Font(20));

            Label cost = new Label(String.format("%.2f$", media.getCost()));

            HBox buttons = new HBox(10);
            buttons.setAlignment(Pos.CENTER);

            Button addToCartButton = new Button("Add to cart");
            addToCartButton.setOnAction(e -> {
                try {
					cart.addMedia(media);
				} catch (CartFullException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MediaAlreadyInCartException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.out.println("Added to cart: " + media.getTitle());
            });
            buttons.getChildren().add(addToCartButton);

            getChildren().addAll(title, cost, buttons);
        }
    }
    
    // Phương thức để set giỏ hàng (store) cho controller
    public void setStore(Store store) {
        this.store = store;
    }
    public Store getStore(Store store) {
        return this.store;
    }
    
    // Phương thức để set giỏ hàng (cart) cho controller
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public Cart getCart(Cart cart) {
        return this.cart;
    }
}
