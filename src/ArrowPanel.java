import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ArrowPanel extends JPanel{
	private Arrow redArrow, blueArrow;
	public ArrowPanel() {
		redArrow = new Arrow(800, Color.RED);
		blueArrow = new Arrow(800, Color.BLUE);
	}
	public void PaintComponent(Graphics g) {
		super.paintComponent(g);
		blueArrow.drawArrow(g);
		redArrow.drawArrow(g); 
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
}
