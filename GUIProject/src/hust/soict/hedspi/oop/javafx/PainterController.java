package hust.soict.hedspi.oop.javafx;

import javafx.scene.paint.Color;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox; // Đảm bảo rằng VBox được import
import javafx.scene.shape.Circle;

public class PainterController {
	private Color currentColor = Color.BLACK;
	
    @FXML
    private Pane drawingAreaPane;  // Đảm bảo rằng đây là tên chính xác
    
    @FXML
    private RadioButton penRadioButton;
    
    @FXML
    private RadioButton eraserRadioButton;
    
    @FXML
    private ToggleGroup toggleGroup;
    
    
    @FXML
    void clearButtonPressed(ActionEvent event) {
        // Implement clearing of canvas
        drawingAreaPane.getChildren().clear();    
    }
    
    @FXML
    void penRadioButtonPressed(ActionEvent event) {
    	currentColor = Color.BLACK;
    }
    
    @FXML
    void eraserRadioButtonPressed(ActionEvent event) {
    	currentColor = Color.WHITE;
    }
    
	@FXML
	void drawingAreaMouseDragged(MouseEvent event) {
		Circle newCircle = new Circle(event.getX(), event.getY(), 2, currentColor);
		drawingAreaPane.getChildren().add(newCircle);
	}
}
