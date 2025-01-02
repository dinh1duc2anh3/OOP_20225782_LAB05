package hust.soict.ite6.oop.aims.screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import hust.soict.ite6.oop.aims.store.Store;

import java.util.Arrays;

import hust.soict.ite6.oop.aims.media.Book;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
        VBox layout = createItemForm("Book");

        // Additional fields specific to Book
        Label authorLabel = new Label("Author(s)");
        TextField authorField = new TextField();

        layout.getChildren().addAll(authorLabel, authorField);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> handleAddBook(authorField));

        layout.getChildren().add(addButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Add Book");
        stage.show();
    }

    @Override
    protected void navigateToViewStoreScreen() {
        // Implement navigation to the "View Store" screen
    }

    @Override
    protected void navigateToUpdateStoreScreen() {
        // Implement navigation to the "Update Store" screen
    }

    @Override
    protected void handleAddItem(TextField titleField, TextField categoryField, TextField costField) {
        // Handle adding Book to the store
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());

        // For simplicity, we don't validate yet
        Book book = new Book(title, category, cost, Arrays.asList("Author"));
        store.addMedia(book);
    }

    private void handleAddBook(TextField authorField) {
        // Implement logic to handle adding the Book item
    }
}

