package hust.soict.ite6.oop.aims.screen;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import hust.soict.ite6.oop.aims.store.Store;
import hust.soict.ite6.oop.aims.media.CompactDisc;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        VBox layout = createItemForm("CD");

        // Additional fields specific to Compact Disc
        Label artistLabel = new Label("Artist");
        TextField artistField = new TextField();

        Label lengthLabel = new Label("Length");
        TextField lengthField = new TextField();

        layout.getChildren().addAll(artistLabel, artistField, lengthLabel, lengthField);

        Button addButton = new Button("Add CD");
        addButton.setOnAction(e -> handleAddCD(artistField, lengthField));

        layout.getChildren().add(addButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Add Compact Disc");
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
        // Handle adding CD to the store
        String title = titleField.getText();
        String category = categoryField.getText();
        float cost = Float.parseFloat(costField.getText());

        // For simplicity, we don't validate yet
        CompactDisc cd = new CompactDisc(title, category, cost, "Artist", "Album", 0);
        store.addMedia(cd);
    }

    private void handleAddCD(TextField artistField, TextField lengthField) {
        // Implement logic to handle adding the CD item
    }
}
