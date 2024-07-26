import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

public class ColorPanel extends JPanel{
	private Piece[] pieces;
	private Piece tmp;
	private Arrow redArrow, blueArrow;
	boolean paintArrows = false;
	private final int xShift = 30;
	private double timeMultiplyier;
	private int flashIdx1 = -1, flashIdx2 = -1, tmpIdx=-1;
	private int accessors, modifiers;
	
	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}
	public Piece[] getPieces() {
		return pieces;
	}
	public Piece getTmp() {
		return tmp;
	}
	public void setTmp(Piece tmp) {
		this.tmp = tmp;
	}
	public Arrow getRedArrow() {
		return redArrow;
	}
	public void setRedArrow(Arrow redArrow) {
		this.redArrow = redArrow;
	}
	public Arrow getBlueArrow() {
		return blueArrow;
	}
	public void setBlueArrow(Arrow blueArrow) {
		this.blueArrow = blueArrow;
	}
	public boolean isPaintArrows() {
		return paintArrows;
	}
	public void setPaintArrows(boolean paintArrows) {
		this.paintArrows = paintArrows;
	}

	public int getFlashIdx1() {
		return flashIdx1;
	}
	public void setFlashIdx1(int flashIdx1) {
		this.flashIdx1 = flashIdx1;
	}
	public int getFlashIdx2() {
		return flashIdx2;
	}
	public void setFlashIdx2(int flashIdx2) {
		this.flashIdx2 = flashIdx2;
	}
	public int getTmpIdx() {
		return tmpIdx;
	}
	public void setTmpIdx(int tmpIdx) {
		this.tmpIdx = tmpIdx;
	}
	public int getAccessors() {
		return accessors;
	} 
	public void setAccesors(int accessors) {
		this.accessors = accessors;
	}
	public int getModifiers() {
		return modifiers;
	}
	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}
	public int getxShift() {
		return xShift;
	}
	public double getTimeMultiplyier() {
		return timeMultiplyier;
	}
	public void setTimeMultiplyier(double timeMultiplyier) {
		this.timeMultiplyier = timeMultiplyier;
	}
	
	public ColorPanel(int len) {
		pieces = new Piece[len];
		for(int i=0; i<pieces.length; i++) {
			int randHeight = (int)(500*Math.random()+2);
			pieces[i] = new Piece(randHeight);
		}
		redArrow = new Arrow(Piece.getBottomY()+54, Color.red);
		blueArrow = new Arrow(redArrow.getY()+Arrow.getTh()/2+Arrow.getRh()+4, Color.blue);
		setBackground(Color.white);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(paintArrows) {
			redArrow.drawArrow(g);
			blueArrow.drawArrow(g);
		}
		int x = xShift;
		for (int i = 0; i < pieces.length; i++) {
			pieces[i].setX(x);
			if(i == tmpIdx) {
				tmp.drawPiece(g);
				if (flashIdx1 == i || flashIdx2 == i)
					tmp.flash(g);
				x += Piece.getW() + 1;
				continue;
			}
			pieces[i].drawPiece(g);
			if (flashIdx1 == i || flashIdx2 == i)
				pieces[i].flash(g);
			x += Piece.getW() + 1;
		}
	}
	
	public void selectionSort() {
		zeroCounts();
		paintArrows = true;
		for(int i=0; i<pieces.length-1; i++) {
			wait(500);
			blueArrow.setX(pieces[i].getX());
			
			int minIdx = i;
			for(int j=i+1; j<pieces.length; j++) {
				redArrow.setX(pieces[j].getX());
				repaint();
				
				accessors += 2;
				if(pieces[j].compareTo(pieces[minIdx]) < 0) 
					minIdx = j;
				
				wait(300);
			}
			
			redArrow.setX(pieces[minIdx].getX());
			flash(i, minIdx, 1000);
			swap(i, minIdx); accessors += 2; modifiers += 2;
			repaint();
		}
		paintArrows = false;
		repaint();
	}
	
	public void bubbleSort() {
		zeroCounts();
		paintArrows = true;
		for(int i=0; i<pieces.length-1; i++) {
			wait(500);
			boolean swaps = false;
			for(int j=0; j<pieces.length-1-i; j++) {
				wait(500);
				blueArrow.setX(pieces[j].getX());
				redArrow.setX(pieces[j+1].getX());
				repaint();
				
				accessors += 2;
				if(pieces[j].compareTo(pieces[j+1]) > 0) {
					flash(j, j+1, 400);
					swap(j, j+1); accessors += 2; modifiers += 2;
					repaint();
					swaps = true;
				}
			}
			if(swaps == false)
				break;
		}
		paintArrows = false;
		repaint();
	}
	
	public void insertionSort() {
		zeroCounts();		
		paintArrows = true; 
		for(int i=1; i<pieces.length; i++) {
			
			int j = i-1;
			blueArrow.setX(pieces[i].getX());
			redArrow.setX(pieces[j].getX());
			repaint();
			wait(200);
			
			tmp = pieces[i]; accessors++;
			tmp.setY(32);
			tmpIdx = -1;
			repaint();
			wait(400); 
					
			while(j >= 0) {
				accessors++;
				if(pieces[j].compareTo(tmp) <= 0) //seperate comparison to facilitate accessor counting
					break;
				flash(j, j, 400);
				tmpIdx = j;
				
				pieces[j+1] = pieces[j--]; modifiers++; accessors++;
				tmp.setX(tmp.getX()-Piece.getW()-1);
				repaint();
				
				blueArrow.setX(tmp.getX());
				if(j>=0) redArrow.setX(pieces[j].getX());
				repaint();
				wait(400);	
			}	
			tmp.setY(Piece.getBottomY() - tmp.getH());
				
			pieces[j + 1] = tmp; modifiers++; 
			repaint();
			wait(600);
			
		}
		wait(1000);
		tmpIdx = -1;
		paintArrows = false;
		repaint();
	}
	
	
	/*public void insertionSortWithSwaps() {
		numSwaps = 0;
		paintArrows = true;
		for(int i=1; i<pieces.length; i++) {
			int j = i;
			wait(500);
			pieces[i].setY(100);
			while(j>0 && pieces[j-1].compareTo(pieces[j]) > 0) {
				blueArrow.setX(pieces[j].getX());
				redArrow.setX(pieces[j-1].getX());
				repaint();
				flash(j-1, j, 600);
				swap(j, j-- -1);
				numSwaps++;
				wait(500);
			}
			pieces[j].setY(Piece.getBottomY()-pieces[j].getH());
			repaint();
		}
		paintArrows = false;
		repaint();
	}*/
	
	public void sortReverse() {//use insertion sort since it is on avg the fastest
		for(int i=1; i<pieces.length; i++) {
			Piece tmp = pieces[i];
			int j = i-1;
			while(j>=0 && pieces[j].compareTo(tmp) < 0)
				pieces[j+1] = pieces[j--];
			pieces[j+1] = tmp;
		}
		repaint();
	}
	public void randomize() {
		for(int i=0; i<pieces.length; i++) {
			int randIdx = (int)(Math.random()*pieces.length);
			swap(i, randIdx); 
		}
		repaint();
	}
	private void flash(int i, int j, int waitTime) {
		flashIdx1 = i; flashIdx2 = j;
		repaint();
		wait(waitTime);
		flashIdx1 = -1; flashIdx2 = -1;
	}
	private void swap(int i, int j) {
		Piece tmp = pieces[i];
		pieces[i] = pieces[j];
		pieces[j] = tmp;
	}
	
	private void wait(int milisecs) { 
		try {
			Thread.sleep((long) (milisecs * timeMultiplyier));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void zeroCounts() {
		accessors = 0;
		modifiers = 0;
	}

}
