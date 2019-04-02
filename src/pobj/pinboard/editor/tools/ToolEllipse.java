package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool{
	private Color color;
	private double xPress, yPress, xRelease, yRelease;
	
	
	public ToolEllipse() {
		color = color.RED;
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
		planche.addClip(new ClipEllipse(left(),top(),right(),bottom(),color));	
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(color);
		gc.strokeOval(left(), top(), right()-left(), bottom()-top());
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

	public void setCouleur(Color couleur) {
		this.color = couleur;
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Ellipse Tools";
	}

}
