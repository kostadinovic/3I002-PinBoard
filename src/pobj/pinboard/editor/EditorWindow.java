package pobj.pinboard.editor;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface{
	
	private Board board;
	private MenuBar menuBar;
	private ToolBar toolBar;
	private Canvas canvas;
	private Label statut;
	private Tool tool;
	private Color color = Color.BLACK;
	private Selection selected;
	
	public EditorWindow(final Stage stage) {
		board = new Board();
	
		stage.setTitle("PinBoard");
		
		//Canvas = zone de dessin
		canvas = new Canvas(1000,1000);
		
		
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
		
		//Tool
		tool = new ToolRect();
		
		final EventHandler<ActionEvent> boxHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tool = new ToolRect();
				statut.setText("Filled "+ tool.getName() +" tool");
				
			}
		};
		
		final EventHandler<ActionEvent> EllipseHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tool = new ToolEllipse();
				statut.setText("Filled "+ tool.getName() + " tool");
				
			}
		};
		
		
		
		selected = new Selection();
		
		
		
		
		
		
	}
	
	public void press(final EditorInterface ei) {
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.press(ei, e);
				
			}
			
		});
	}
	
	public void drag(final EditorInterface ei) {
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.drag(ei, e);
				draw();
			}
			
		});
	}
	
	public void release(final EditorInterface ei) {
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.release(ei, e);
				board.draw(canvas.getGraphicsContext2D());
				if (tool instanceof ToolSelection) {
					tool.drawFeedback(ei, canvas.getGraphicsContext2D());
				}
			}
			
		});
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selected;
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
	
	public void draw() {
		board.draw(canvas.getGraphicsContext2D());
		tool.drawFeedback(this, canvas.getGraphicsContext2D());
	}
	
	
	

}
