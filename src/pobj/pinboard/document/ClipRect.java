package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip{
	private double left,top,right,bottom;
	private Color color;
	
	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(getColor());
		ctx.fillRect(getLeft(),getTop(),getWidth(), getHeigth());
		
	}

	@Override
	public Clip copy() {
		return new ClipRect(getLeft(), getTop(), getRight(),getBottom(),getColor());
	}

}
