package hust.soict.ite6.oop.aims.screen;

import java.util.ArrayList;
import java.util.Arrays;

import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.media.Playable;
import hust.soict.ite6.oop.aims.model.media.Track;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StoreScreen extends Application {
    private Store store;
    private Cart cart;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.store = createStore();
        this.cart = new Cart();

        VBox root = new VBox();
        root.getChildren().addAll(createNorth(), createCenter());

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNorth() {
        VBox north = new VBox();
        north.getChildren().addAll(createMenuBar(), createHeader());
        return north;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Options");
        
        Menu updateStore = new Menu("Update Store");
        MenuItem addBook = new MenuItem("Add Book");
        MenuItem addCD = new MenuItem("Add CD");
        MenuItem addDVD = new MenuItem("Add DVD");
        updateStore.getItems().addAll(addBook, addCD, addDVD);

        MenuItem viewStore = new MenuItem("View Store");
        MenuItem viewCart = new MenuItem("View Cart");

        optionsMenu.getItems().addAll(updateStore, viewStore, viewCart);
        menuBar.getMenus().add(optionsMenu);
        
        return menuBar;
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));
        header.setSpacing(10);

        Label title = new Label("AIMS");
        title.setFont(Font.font(50));
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button cartButton = new Button("View Cart");
        cartButton.setPrefSize(100, 50);

        header.getChildren().addAll(title, spacer, cartButton);
        return header;
    }

    private GridPane createCenter() {
        GridPane center = new GridPane();
        center.setHgap(2);
        center.setVgap(2);
        center.setPadding(new Insets(10));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        int column = 0;
        int row = 0;

        for (int i = 0; i < 9 && i < mediaInStore.size(); i++) {
            MediaStore mediaStore = new MediaStore(mediaInStore.get(i));
            center.add(mediaStore, column, row);
            
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        return center;
    }

    private class MediaStore extends VBox {
        private Media media;

        public MediaStore(Media media) {
            this.media = media;
            
            setPadding(new Insets(10));
            setSpacing(10);
            setAlignment(Pos.CENTER);
            setStyle("-fx-border-color: black; -fx-border-width: 1;");

            Label title = new Label(media.getTitle());
            title.setFont(Font.font(20));

            Label cost = new Label(String.format("%.2f$", media.getCost()));

            HBox buttons = new HBox(10);
            buttons.setAlignment(Pos.CENTER);

            Button addToCartButton = new Button("Add to cart");
            addToCartButton.setOnAction(e -> {
                cart.addMedia(media);
                System.out.println("Added to cart: " + media.getTitle());
            });
            buttons.getChildren().add(addToCartButton);

            if (media instanceof Playable) {
                Button playButton = new Button("Play");
                playButton.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Playing Media");
                    alert.setHeaderText("Now playing: " + media.getTitle());
                    alert.showAndWait();
                });
                buttons.getChildren().add(playButton);
            }

            getChildren().addAll(title, cost, buttons);
        }
    }

    private Store createStore() {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", 20.0f, "Christopher Nolan", 148);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Avatar", "Fantasy", 25.0f, "James Cameron", 162);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Titanic", "Romance", 22.0f, "James Cameron", 195);
        store.addMedia(dvd1, dvd2, dvd3);

        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
        cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd.addTrack(new Track("Thriller", 7));
        cd.setLength(cd.getLength());
        store.addMedia(cd);

        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        store.addMedia(book);

        return store;
    }

    public static void main(String[] args) {
        launch(args);
    }
}