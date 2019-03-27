package pobj.pinboard.editor.tools;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool {
	private double left,top,right,bottom;
	private Color color=Color.RED;
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		left=e.getX();
		top=e.getY();
		right=e.getX();
		bottom=e.getY();
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		right=e.getX();
		bottom=e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		List<Clip> list= new ArrayList<Clip>();
		list.add(new ClipRect(left,top,right,bottom,color));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(left, top, Math.abs(right-left), Math.abs(top-bottom));
		gc.setStroke(color);
		gc.strokeRect(left, top, Math.abs(right-left), Math.abs(top-bottom));
	}

	@Override
	public String getName() {
		return "Rect tool";
	}
}
