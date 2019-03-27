package pobj.pinboard.editor.tools;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool{
	private double left, top, right, bottom;
	private Color color= Color.BLUE;

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
		list.add(new ClipEllipse(left,top,right,bottom,color));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillOval(((left+right)/2) - ((right-left)/2), ((top+bottom)/2) - ((bottom-top)/2),
				(right-left), (bottom-top));
		gc.setStroke(color);
		gc.strokeOval( ((left+right)/2) - ((right-left)/2), ((top+bottom)/2) - ((bottom-top)/2),
				(right-left), (bottom-top));
	}

	@Override
	public String getName() {
		return "Ellipse tool";
	}

}
