package pobj.pinboard.editor;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;

public class EditorWindow {
	
	private Board board;
	private MenuBar menuBar;
	private ToolBar toolBar;
	private Canvas canvas;
	private Label statut = new Label();
	
	public EditorWindow(final Stage stage) {
		board = new Board();
		menuBar = new MenuBar();
		toolBar= new ToolBar();
		canvas = new Canvas(400,400);
		Separator separator = new Separator();
		stage.setTitle("PinBoard");
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(menuBar, toolBar,canvas, separator, statut);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
		
	}
	
	
	

}
