package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool{
	private double xPress, yPress, xRelease, yRelease;
	private Color color;
	
	
	public ToolRect() {
		color = color.BLUE;
		xPress = 0;
		yPress = 0;
		xRelease = 0;
		yRelease = 0;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		xPress = e.getX();
		yPress = e.getY();	
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		xRelease = e.getX();
		yRelease = e.getY();	
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		Board planche = i.getBoard();
		planche.addClip(new ClipRect(left(),top(),right(),bottom(),color));	
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(color);
		gc.strokeRect(left(), top(), right()-left(), bottom()-top());
	}

	private double left() {
		return Math.min(xPress, xRelease);
	}
	
	private double top() {
		return Math.min(yPress,yRelease);
	}

	private double right() {
		return Math.max(xRelease, xPress);
	}

	private double bottom() {
		return Math.max(yRelease, yPress);
	}

	public void setCouleur(Color color) {
		this.color = color;
	}
	
	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse Tools";
	}
}
