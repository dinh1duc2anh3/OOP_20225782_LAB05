package hust.soict.ite6.oop.aims.screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import hust.soict.ite6.oop.aims.store.Store;
import hust.soict.ite6.oop.aims.media.DigitalVideoDisc;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        VBox layout = createItemForm("DVD");

        // Additional fields specific to Digital Video Disc
        Label directorLabel = new Label("Director");
        TextField directorField = new TextField();

        Label lengthLabel = new Label("Length");
        TextField lengthField = new TextField();

        layout.getChildren().addAll(directorLabel, directorField, lengthLabel, lengthField);

        Button addButton = new Button("Add DVD");
        addButton.setOnAction(e -> handleAddDVD(directorField, lengthField));

        layout.getChildren().add(addButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Add Digital Video Disc");
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
        // Handle adding DVD to the store
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());
        
        // For simplicity, we don't validate yet
        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost, "Director", 120);
        store.addMedia(dvd);
    }

    private void handleAddDVD(TextField directorField, TextField lengthField) {
        // Implement logic to handle adding the DVD item
    }
}
