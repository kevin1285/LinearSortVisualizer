import java.awt.Color;
import java.awt.Graphics;

public class Piece implements Comparable<Piece>{
	private int x, y; //top-left coord
	private int h;
	private static final int w = 36, bottomY=800;
	private final Color bodyColor = new Color(230, 230, 0);
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public static int getBottomY() {
		return bottomY;
	}
	public Color getBodyColor() {
		return bodyColor;
	}
	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public static int getW() {
		return w;
	}

	public Piece(int height) {
		y = bottomY-height;
		h = height;
	}
	
	public void drawPiece(Graphics g) {
		drawEraser(g, true);
		drawTip(g, true);
		drawBody(g, true);
	}

	public void flash(Graphics g) {
		drawEraser(g, false);
		drawTip(g, false);
		drawBody(g, false);
	}
	private void drawTip(Graphics g, boolean fill) {
		if(fill) {
			g.setColor(new Color(145, 102, 102)); //brown
			g.fillPolygon(new int[] {x, x+w/2, x+w}, new int[] {y, y-w, y}, 3);
			g.setColor(Color.BLACK);
			g.fillPolygon(new int[] {x+w/4, x+w/2, x+3*w/4}, new int[] {y-w/2, y-w, y-w/2}, 3);
		}else {
			g.setColor(Color.RED);
			g.drawPolygon(new int[] {x, x+w/2, x+w}, new int[] {y, y-w, y}, 3);
		}
		
	}
	private void drawBody(Graphics g, boolean fill) {
		if(fill) {
			g.setColor(bodyColor);
			g.fillRect(x, y, w, h);
		}else {
			g.setColor(Color.RED);
			g.drawRect(x, y, w, h);
		}
	}
	private void drawEraser(Graphics g, boolean fill) {
		if(fill) {
			g.setColor(new Color(170, 70, 140));
			g.fillRoundRect(x, y+h-6, w, 18, 100, 100);
			g.setColor(new Color(175, 175, 175));
			g.fillRect(x, y+h, w, 4);
		}else {
			g.setColor(Color.RED);
			g.drawRect(x, y+h, w, 4);
			g.drawArc(x, y+h-6, w, 18, 0, -180);
		}
	}
	
	@Override
	public int compareTo(Piece o) {
		return h - o.h;
	}
	
	public String toString() {
		return h + "";
	}
	
}
