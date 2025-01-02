package hust.soict.ite6.oop.aims.screen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import hust.soict.ite6.oop.aims.store.Store;

public abstract class AddItemToStoreScreen {

    protected Store store;
    protected Stage stage;
    protected MenuBar menuBar;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        this.stage = new Stage();
        this.menuBar = createMenuBar();
    }

    // Method to create a common menu bar for all screens
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");

        MenuItem viewStoreMenuItem = new MenuItem("View Store");
        viewStoreMenuItem.setOnAction(e -> navigateToViewStoreScreen());

        Menu updateStoreMenu = new Menu("Update Store");
        MenuItem addItemMenuItem = new MenuItem("Add Item");
        addItemMenuItem.setOnAction(e -> navigateToUpdateStoreScreen());

        updateStoreMenu.getItems().add(addItemMenuItem);
        optionsMenu.getItems().addAll(viewStoreMenuItem, updateStoreMenu);
        menuBar.getMenus().add(optionsMenu);

        return menuBar;
    }

    // Methods to handle navigation to the Store Screen and Update Store Screen
    protected abstract void navigateToViewStoreScreen();
    protected abstract void navigateToUpdateStoreScreen();

    // Common layout method (with fields for item entry)
    protected VBox createItemForm(String itemType) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        // Add MenuBar to the top of the form
        layout.getChildren().add(menuBar);

        // Based on itemType (DVD, CD, or Book), create form for item details
        Label titleLabel = new Label(itemType + " Title");
        TextField titleField = new TextField();

        Label categoryLabel = new Label("Category");
        TextField categoryField = new TextField();

        Label costLabel = new Label("Cost");
        TextField costField = new TextField();

        Button addButton = new Button("Add " + itemType);
        addButton.setOnAction(e -> handleAddItem(titleField, categoryField, costField));

        layout.getChildren().addAll(titleLabel, titleField, categoryLabel, categoryField, costLabel, costField, addButton);

        return layout;
    }

    // Abstract method for adding item to the store (to be implemented in child classes)
    protected abstract void handleAddItem(TextField titleField, TextField categoryField, TextField costField);
}
