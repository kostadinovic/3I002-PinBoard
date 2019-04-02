package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;

public class Selection {
	private List<Clip> laSelection;
	private ClipRect cr;
	
	
	public Selection(){
		laSelection = new ArrayList<>();
		//cr = new ClipRect(0, 0, 0, 0, null);
	}
	
	public void select(Board board, double x, double y) {
		clear();
		List<Clip> elemsPlanche = board.getContents(); 
		for(Clip c : elemsPlanche) {
			if(c.isSelected(x, y)) laSelection.add(c);
		}
		
	}

	public void toogleSelect(Board board, double x, double y) {
		List<Clip> elemsPlanche = board.getContents();
		for (Clip c: elemsPlanche){
			if(c.isSelected(x, y)){
				if (!(laSelection.contains(c))){
					laSelection.add(c);
					break;
				}else{
					laSelection.remove(c);
					break;
				}
			}
		}
	}
	
	public List<Clip> getContents(){
		return laSelection;
	}
	public void clear() {
		laSelection.clear();
	}
	
	 public void drawFeedback(GraphicsContext gc) {
		 double left = 100000000;
			double right = 0;
			double top = 100000000;
			double bottom = 0;
			for (Clip c : laSelection){
				if (c.getLeft()<left){
					left = c.getLeft();
				}
				if (c.getRight()>right){
					right = c.getRight();
				}
				if (c.getBottom()>bottom){
					bottom = c.getBottom();
				}
				if (c.getTop()<top){
					top = c.getTop();
				}
			}
			cr = new ClipRect(left, top, right, bottom, null);
			gc.setStroke(Color.BLUE);
			gc.strokeRect(cr.getLeft(), cr.getTop(), cr.getRight() - cr.getLeft(), cr.getBottom() - cr.getTop());
	 }
}
