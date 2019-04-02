package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.Selection;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandMove;


public class ToolSelection implements Tool{

	private double x_press, y_press;
	private double x_drag, y_drag;
	private double x_release, y_release;
	
	public ToolSelection(){
		x_press = 0;
		y_press = 0;
		x_drag = 0;
		y_drag = 0;
		x_release = 0;
		y_release = 0;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		Selection selection = i.getSelection();
		x_press = e.getX();
		y_press = e.getY();
		if (!e.isShiftDown()){
			selection.select(i.getBoard(),x_press,y_press);
		}else{
			selection.toogleSelect(i.getBoard(),x_press,y_press);
		}
		x_drag = x_press;
		y_drag = y_press;
		x_release = x_press;
		y_release = y_press;
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		Selection selection = i.getSelection();
		x_release = e.getX();
		y_release = e.getY();
		Command cMoveSelection = new CommandMove(i,selection.getContents(),x_release - x_drag,y_release - y_drag);
		cMoveSelection.execute();
		x_drag = x_release;
		y_drag = y_release;
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Selection selection = i.getSelection();
		if (!selection.getContents().isEmpty()) {
			Command cMoveSelection = new CommandMove(i,selection.getContents(),x_release - x_press,y_release - y_press);
			i.getUndoStack().addCommand(cMoveSelection);
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Selection";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
