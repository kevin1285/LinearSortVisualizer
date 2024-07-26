import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Arrow {
	private int x=-40, y;
	private static final int tw=Piece.getW(), th=2*tw, rw=tw/2-4, rh=50;
	private Color c;
	
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public static int getTw() {
		return tw;
	}
	public static int getTh() {
		return th;
	}

	public static int getRw() {
		return rw;
	}
	public static int getRh() {
		return rh;
	}
	public int getY() {
		return y;
	} 
	public Arrow(int yc, Color color) {
		y = yc;
		c = color;
	}
	
	public void drawArrow(Graphics g) {
		g.setColor(c);
		g.fillPolygon(new int[] {x, x+tw/2, x+tw}, new int[] {y, y-th/2, y}, 3);
		g.fillRect(x+(tw-rw)/2, y, rw, rh);
	}
	
	
}
