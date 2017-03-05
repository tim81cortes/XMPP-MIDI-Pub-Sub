package jsonExample;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Draw extends JPanel{
private int width;
private int height;
private int ex;
private int why;

	public Draw(int x, int y, int w, int h){
		width = w;
		height = h;
		ex = x;
		why = y;
	}
	public void drawing(){
		repaint();
	}
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.fillRect(ex, why, width, height);
	}
	
public void update(Graphics g){
		
		super.update(g);
		
		
	}
}