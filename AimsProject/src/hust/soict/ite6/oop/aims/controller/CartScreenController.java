package hust.soict.ite6.oop.aims.controller;

import java.io.IOException;
import java.net.URL;

import hust.soict.ite6.oop.aims.exception.MediaNotFoundException;
import hust.soict.ite6.oop.aims.model.cart.Cart;
import hust.soict.ite6.oop.aims.model.media.Media;
import hust.soict.ite6.oop.aims.model.media.Playable;
import hust.soict.ite6.oop.aims.model.store.Store;
import hust.soict.ite6.oop.aims.screen.StoreScreen;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CartScreenController {
	private Store store;
	private Cart cart;
	
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn< Media, String> colMediaCategory;
	
	@FXML
	private TableColumn<Media, Float> colMediaCost;
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnRemove;
	
    @FXML
    private MenuItem addBookMenuItem;
	
    @FXML
    private MenuItem addDvdMenuItem;
    
    @FXML
    private MenuItem addCdMenuItem;

    @FXML
    private MenuItem viewStoreMenuItem;
    
    @FXML
    private MenuItem viewCartMenuItem;
    
	@FXML 
	private Label totalCostLabel;
	
	// Constructor không tham số
    public CartScreenController() {
        this.cart = new Cart(); // Hoặc bạn có thể khởi tạo cart theo cách khác
    }
	
	public CartScreenController(Cart cart) {
		super();
		this.cart = cart;
	}
	
	public CartScreenController(Cart cart,Store store) {
		super();
		this.cart = cart;
		this.store = store;
	}

	@FXML
	public void initialize() {
		System.out.println("CartScreenController initialized");
	    colMediaTitle.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("title"));
	    colMediaCategory.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("category"));
	    colMediaCost.setCellValueFactory(
	        new PropertyValueFactory<Media, Float>("cost"));
	    tblMedia.setItems(this.cart.getItemsOrdered());

	    btnPlay.setVisible(false);
	    btnRemove.setVisible(false);

	    tblMedia.getSelectionModel().selectedItemProperty().addListener(
	        (observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                updateButtonBar(newValue);
	            }
	        }
	    );
	    
	    //update total cost label
	    updateTotalCost();
	    cart.getItemsOrdered().addListener((ListChangeListener<Media>) c-> {
	    	updateTotalCost();
	    });
	    
	    
	}
	
	private void updateTotalCost() {
		totalCostLabel.setText(cart.totalCost()+"$");
	}
	
	private void updateButtonBar(Media media) {
	    btnRemove.setVisible(true);
	    if (media instanceof Playable) {
	        btnPlay.setVisible(true);
	    } else {
	        btnPlay.setVisible(false);
	    }
	}
	
	@FXML
	private void btnRemovePressed(ActionEvent event) throws MediaNotFoundException {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
	}
	
	@FXML
	private void btnPlaceOrderPressed(ActionEvent event) {
		updateTotalCost();
    	cart.clear();
	}
	
	@FXML
	private void btnPlayPressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		if  ( media instanceof Playable) {
			showPlayDialog(media);
		}
	}
	
	
	public void showPlayDialog(Media media) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Playing Media");
        alert.setHeaderText(null);
        alert.setContentText("Playing " + media.getTitle());
        
        alert.showAndWait();
	}
	
	public void handleAddBook(ActionEvent event) {
		try {
			// Mở màn hình AddBookToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddBookToStoreDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddBookToStoreController addBookController = loader.getController();
            addBookController.setStore(store);
            addBookController.handleAddBook();
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Book window.");
            alert.showAndWait();

		}
	}
	
	public void handleAddDvd(ActionEvent event) {
		try {
			// Mở màn hình AddDvdToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddDvdToStoreDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddDvdToStoreController addDvdController = loader.getController();
            addDvdController.setStore(store);
            addDvdController.handleAddDvd();
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Dvd");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Dvd window.");
            alert.showAndWait();

		}
	}
	
	public void handleAddCd(ActionEvent event) {
		try {
			// Mở màn hình AddDvdToCartDialog
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/AddCdToStoreDialog.fxml"));
			GridPane root = loader.load();
			
			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
            AddCdToStoreController addCdController = loader.getController();
            addCdController.setStore(store);
            addCdController.handleAddCd();
            
            // Tạo cửa sổ mới và hiển thị
            Stage stage = new Stage();
            stage.setTitle("Add Cd");
            stage.setScene(new Scene(root));
            stage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Add Cd window.");
            alert.showAndWait();

		}
	}
	
	public void handleViewStore(ActionEvent event) {
//		try {
//			// Mở màn hình AddDvdToCartDialog
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/ite6/oop/aims/view/store.fxml"));
//			GridPane root = loader.load();
//			
//			// Lấy controller của AddBookToCartDialog và thiết lập giỏ hàng
//            StoreScreen viewScreenController = loader.getController();
//            viewScreenController.setStore(store);
//            viewScreenController.setCart(cart);
//            viewScreenController.handleAddCd();
//            
//            // Tạo cửa sổ mới và hiển thị
//            Stage stage = new Stage();
//            stage.setTitle("Add Cd");
//            stage.setScene(new Scene(root));
//            stage.show();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Failed to open Add Cd window.");
//            alert.showAndWait();
//
//		}
		
		try {
    		URL fxmlUrl = getClass().getResource("/hust/soict/ite6/oop/aims/view/store.fxml");
    		if (fxmlUrl == null) {
    		    System.out.println("FXML file store.fxml  not found!");
    		} else {
    		    System.out.println("FXML file store.fxml  found!");
    		}
    		
    		// Load cửa sổ thêm sách
    		FXMLLoader loader = new FXMLLoader(fxmlUrl);
    		Parent root = loader.load();
    		
    		// Lấy controller của view và gán cho các xử lý tiếp theo
            StoreScreen controller = loader.getController();
            controller.setCart(cart);
            controller.setStore(store);
            
            // Hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            controller.start(stage);  // Gọi phương thức xử lý trong controller
            
            
 
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    		
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open view store window.");
            alert.showAndWait();
    	}
	}
	
	/*Với cách tiếp cận này, bạn sẽ đảm bảo rằng khi bạn bấm "View Store" lần đầu tiên từ cửa sổ CartScreen, nó sẽ tạo một cửa sổ mới cho StoreScreen. Nếu cửa sổ đó đã được tạo từ trước, chương trình sẽ chuyển sang cửa sổ Store đã tồn tại mà không tạo mới nó. Tương tự, khi bạn chọn "View Cart" từ cửa sổ StoreScreen, cửa sổ Cart sẽ được hiển thị hoặc chuyển lên nếu đã tồn tại.

Hãy nhớ rằng trong JavaFX, bạn có thể sử dụng các phương thức như toFront() hoặc show() để quản lý sự chuyển giao giữa các cửa sổ, đồng thời sử dụng các biến tĩnh để lưu trữ trạng thái của các cửa sổ này.*/
	
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
