package pobj.pinboard.editor;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;

public class EditorWindow implements EditorInterface{
	
	private Board board;
	private MenuBar menuBar;
	private ToolBar toolBar;
	private Canvas canvas;
	private Label statut;
	private Tool tool;
	
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
		menuBar.getMenus().add(menuTools);
		
		//Button of ToolBar
		Button bBox = new Button("Box");
		Button bElip = new Button("Ellipse");
		Button bImg = new Button("Img...");
		
		//ToolBar
		toolBar= new ToolBar(bBox, bElip, bImg);
		
		//Separator = séparation entre canvas et statut
		Separator separator = new Separator();
		
		//Barre de statut = un label apres le separator
		Label statut = new Label("Filled rectangle tool");
		
		//VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(menuBar, toolBar,canvas, separator, statut);
	
		
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
		
		canvas.setOnMousePressed(EventHandler<MouseEvent>){
			tool.press(eI, e);
		}
		
		
		
		
		
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
