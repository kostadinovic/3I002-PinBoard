package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	
	private List<Clip> selected;
	private List<Double> L = new ArrayList<Double>();
	private List<Double> T = new ArrayList<Double>();
	private List<Double> R = new ArrayList<Double>();
	private List<Double> B = new ArrayList<Double>();
	
	public Selection () {
		selected = new ArrayList<Clip>();
	}
	
	public void select (Board board, double x, double y ){
			selected.clear();
			L.clear();
			T.clear();
			R.clear();
			B.clear();
		
		for (Clip elem : board.getContents()) {
			if (elem.isSelected(x, y)) {
				selected.add(elem);
				
				L.add(elem.getLeft());
				T.add(elem.getTop());
				R.add(elem.getRight());
				B.add(elem.getBottom());
				break;
			}
		}
	}
	
	public void toogleSelect (Board board, double x, double y){
		
		for (Clip elem : board.getContents()) {
			if (elem.isSelected(x, y)) {
				if(selected.contains(elem)) {
					selected.remove(elem);
					
					L.remove(elem.getLeft());
					T.remove(elem.getTop());
					R.remove(elem.getRight());
					B.remove(elem.getBottom());
				} else {					
					selected.add(elem);

					L.add(elem.getLeft());
					T.add(elem.getTop());
					R.add(elem.getRight());
					B.add(elem.getBottom());
				}
				break;
			}
		}
	}
	
	public void clear() { selected.clear(); }
	
	public List<Clip> getContents() { return selected ; }
	
	public void drawFeedback(GraphicsContext gc) {
		
		double minLeft = Collections.min(L);
		double minTop = Collections.min(T);
		double width = Collections.max(R) - minLeft;
		double heigth = Collections.max(B) - minTop; 
		
		gc.strokeRect(minLeft, minTop, width, heigth);
	}
	
	public double getRight() {
		return Collections.max(R);
	}
	
	public double getLeft() {
		return Collections.min(L);
	}
	
	public double getTop() {
		return Collections.min(T);
	}
	
	public double getBottom() {
		return Collections.max(B);
	}
	
	public double getWidth() {
		return getRight() - getLeft();
	}
	
	public double getHeight() {
		return getBottom() - getTop();
	}

}
