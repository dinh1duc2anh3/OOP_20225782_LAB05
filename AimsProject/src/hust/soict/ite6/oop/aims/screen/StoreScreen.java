package hust.soict.ite6.oop.aims.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import hust.soict.ite6.oop.aims.controller.AddBookToStoreController;
import hust.soict.ite6.oop.aims.controller.AddCdToStoreController;
import hust.soict.ite6.oop.aims.controller.AddDvdToStoreController;
import hust.soict.ite6.oop.aims.controller.CartScreenController;
import hust.soict.ite6.oop.aims.exception.CartFullException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInCartException;
import hust.soict.ite6.oop.aims.exception.MediaAlreadyInStoreException;
import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.exception.StoreFullException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Book;
import hust.soict.ite6.oop.aims.model.media.CompactDisc;
import hust.soict.ite6.oop.aims.model.media.DigitalVideoDisc;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.media.Playable;
import hust.soict.ite6.oop.aims.model.media.Track;
import hust.soict.ite6.oop.aims.model.store.Store;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    private GridPane center;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        if (this.store == null) {
            setStore(createStore());
        }
        
        if (this.cart == null) {
        	setCart(new Cart());
        }
        
        
        // Tạo GUI và thêm ListChangeListener vào store
        VBox root = new VBox();
        
        //khoi tao center
        setCenter(new GridPane());
        
        root.getChildren().addAll(createNorth(), createCenter(this.center));
        
        store.getItemsInStore().addListener(new ListChangeListener<Media>() {

			@Override
			public void onChanged(Change<? extends Media> arg0) {
				// TODO Auto-generated method stub
				updateStoreCenter(center);
			}
        	
        });

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Store");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Make sure to call updateStoreCenter() after the scene is displayed
//        updateStoreCenter(center);
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
        
     // Gán sự kiện cho các MenuItem
        addBook.setOnAction(e-> {
			try {
				handleAddBook();
			} catch (MediaNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (StoreFullException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MediaAlreadyInStoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        addCD.setOnAction(e-> {
			try {
				handleAddCd();
			} catch (MediaNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (StoreFullException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MediaAlreadyInStoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        addDVD.setOnAction(e-> {
			try {
				handleAddDvd(store);
			} catch (MediaNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (StoreFullException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MediaAlreadyInStoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
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
        cartButton.setOnAction(e -> handleViewCart());

        header.getChildren().addAll(title, spacer, cartButton);
        return header;
    }

    private GridPane createCenter(GridPane center) {
        center.setHgap(2);
        center.setVgap(2);
        center.setPadding(new Insets(10));
        
        updateStoreCenter(center);

        return center;
    }
    
    public GridPane getCenter() {
    	return this.center;
    }
    
    public void setCenter(GridPane center) {
    	this.center = center;
    }
    
	public void updateStoreCenter(GridPane center) {

		// Xóa các phần tử cũ
		center.getChildren().clear();

		ObservableList<Media> mediaInStore = store.getItemsInStore();
		int column = 0;
		int row = 0;

		// Thêm lại các media vào center
		for (int i = 0; i < 9 && i < mediaInStore.size(); i++) {
			MediaStore mediaStore = new MediaStore(mediaInStore.get(i));
			center.add(mediaStore, column, row);

			column++;
			if (column == 3) {
				column = 0;
				row++;
			}
		}
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
                try {
					cart.addMedia(media);
				} catch (CartFullException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MediaAlreadyInCartException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
        try {
			store.addMedia(dvd1, dvd2, dvd3);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        CompactDisc cd = new CompactDisc("Thriller", "Pop", 15.0f, "Quincy Jones", "Michael Jackson", 0);
        cd.addTrack(new Track("Wanna Be Startin' Somethin'", 6));
        cd.addTrack(new Track("Thriller", 7));
        cd.setLength(cd.getLength());
        try {
			store.addMedia(cd);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Book book = new Book("Effective Java", "Programming", 45.0f, Arrays.asList("Joshua Bloch"));
        try {
			store.addMedia(book);
		} catch (StoreFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MediaAlreadyInStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return store;
    }
    
    public void handleAddBook() throws MediaNotFoundException, StoreFullException, MediaAlreadyInStoreException {
    	try {
    		URL fxmlUrl = getClass().getResource("/hust/soict/ite6/oop/aims/view/AddBookToStoreDialog.fxml");
    		if (fxmlUrl == null) {
    		    System.out.println("FXML file AddBookToStoreDialog.fxml not found!");
    		} else {
    		    System.out.println("FXML file AddBookToStoreDialog.fxml found!");
    		}
    		
    		// Load cửa sổ thêm sách
    		FXMLLoader loader = new FXMLLoader(fxmlUrl);
    		Parent root = loader.load();
    		
    		// Lấy controller của view và gán cho các xử lý tiếp theo
            AddBookToStoreController controller = loader.getController();
            controller.setStore(this.store);
            controller.handleAddBook();  // Gọi phương thức xử lý trong controller
         // Hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void handleAddCd() throws MediaNotFoundException, StoreFullException, MediaAlreadyInStoreException {
    	try {
    		
    		URL fxmlUrl = getClass().getResource("/hust/soict/ite6/oop/aims/view/AddCdToStoreDialog.fxml");
    		if (fxmlUrl == null) {
    		    System.out.println("FXML file AddCdToStoreDialog.fxml not found!");
    		} else {
    		    System.out.println("FXML file AddCdToStoreDialog.fxml found!");
    		}
    		
    		// Load cửa sổ thêm sách
    		FXMLLoader loader = new FXMLLoader(fxmlUrl);
    		Parent root = loader.load();
    		
    		// Lấy controller của view và gán cho các xử lý tiếp theo
            AddCdToStoreController controller = loader.getController();
            controller.setStore(store);
            controller.handleAddCd();  // Gọi phương thức xử lý trong controller
            
            // Hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void handleAddDvd(Store store) throws MediaNotFoundException, StoreFullException, MediaAlreadyInStoreException {
    	try {
    		URL fxmlUrl = getClass().getResource("/hust/soict/ite6/oop/aims/view/AddDvdToStoreDialog.fxml");
    		if (fxmlUrl == null) {
    		    System.out.println("FXML file AddDvdToStoreDialog.fxml not found!");
    		} else {
    		    System.out.println("FXML file AddDvdToStoreDialog.fxml found!");
    		}
    		
    		// Load cửa sổ thêm sách
    		FXMLLoader loader = new FXMLLoader(fxmlUrl);
    		Parent root = loader.load();
    		
    		// Lấy controller của view và gán cho các xử lý tiếp theo
            AddDvdToStoreController controller = loader.getController();
            controller.setStore(store);
            controller.handleAddDvd();  // Gọi phương thức xử lý trong controller
            
         // Hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void handleViewCart() {
    	try {
    		URL fxmlUrl = getClass().getResource("/hust/soict/ite6/oop/aims/view/cart.fxml");
    		if (fxmlUrl == null) {
    		    System.out.println("FXML file cart.fxml  not found!");
    		} else {
    		    System.out.println("FXML file cart.fxml  found!");
    		}
    		
    		// Load cửa sổ thêm sách
    		FXMLLoader loader = new FXMLLoader(fxmlUrl);
    		Parent root = loader.load();
    		
    		// Lấy controller của view và gán cho các xử lý tiếp theo
            CartScreenController controller = loader.getController();
            controller.setCart(cart);
            controller.setStore(store);
            controller.initialize();  // Gọi phương thức xử lý trong controller
            
         // Hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
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

    public static void main(String[] args) {
        launch(args);
    }
}