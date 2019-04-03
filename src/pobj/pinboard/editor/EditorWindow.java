package pobj.pinboard.editor;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface{
	
	private Board board;
	private MenuBar menuBar;
	private ToolBar toolBar;
	private Canvas canvas;
	private Label statut;
	private Color color = Color.BLACK;
	private Tool tool = new ToolRect();
	
	public EditorWindow(final Stage stage) {
		board = new Board();
	
		stage.setTitle("PinBoard");
		
		//Canvas = zone de dessin
		canvas = new Canvas(400,400);
		
		
		//Menu Bar
		menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		Menu menuTools = new Menu("Tools");
		menuBar.getMenus().add(menuFile);
		menuBar.getMenus().add(menuEdit);
		
		//Button of ToolBar
		Button bBox = new Button("Box");
		Button bElip = new Button("Ellipse");
		Button bImg = new Button("Img...");
		Button bSelect = new Button("Selection");
		
		//ToolBar
		toolBar= new ToolBar(bBox, bElip, bImg, bSelect);
		
		//Separator = séparation entre canvas et statut
		Separator separator = new Separator();
		
		//Barre de statut = un label apres le separator
		Label label = new Label("Filled rectangle tool");
		
		//VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(menuBar, toolBar,canvas, separator, label);
	
		
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
		
		//Comportement des menu dans MenuBar
		MenuItem newFile = new MenuItem("New");
		MenuItem closeFile = new MenuItem("Close");
		menuFile.getItems().addAll(newFile,closeFile);
		newFile.setOnAction(e -> { new EditorWindow(new Stage()); } );
		closeFile.setOnAction(e -> { stage.close(); } );
		
		//Tools dans MenuBar
		Menu tools = new Menu("Tools");
		MenuItem rectangle = new MenuItem("Rectangle");
		MenuItem ellipse = new MenuItem("Ellipse");
		tools.getItems().addAll(rectangle, ellipse);
		menuBar.getMenus().add(tools); 
		
		//Comportement des TOOLS

		
		
		
		
		
		
		
	}
	

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return null;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	public void press(EditorInterface i, MouseEvent e) {
		tool.press(i, e);
	}
	
	public void drag(EditorInterface i, MouseEvent e) {
		tool.drag(i, e);
	}
	
	public void release(EditorInterface i, MouseEvent e) {
		tool.release(this, e);
	}
	
	public void draw(GraphicsContext gc) {
		board.draw(gc);
		tool.drawFeedback(this, gc);
	}
	
	
	

}
