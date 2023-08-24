/*
 * KONSTANTINOS KIKIDIS (4387) 
 * CHRISTOS KROKIDAS (4399) 
 * KONSTANTINOS TSAMPIRAS (4508)
 */

import java.awt.*;
import java.awt.event.*;


public class Plot extends Frame {
	
	private Data[] x;
	private Data[] c;
	
	public Plot(Data[] c, Data[] x, String title){
		super(title);
		this.x = x;
		this.c = c;
		prepareGUI();
	}

	private void prepareGUI(){
		setSize(1300,1000);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
			System.exit(0);
			}        
		}); 
	}    

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 26);
		
		g2.setFont(font);
		g2.translate(0, getHeight());
		g2.scale(0.9, -0.9);
		
		for(int i = 0; i < x.length; i++) {
			float t1 = (float)((x[i].getX1() + 1.1)/2.2);
			float t2 = (float)((x[i].getX2() + 1.1)/2.2);
			float x = (1 - t1)*10 + t1*780;
			float y = (1 - t2)*20 + t2*770;
			g2.drawString("+", x, y);
		}
		for(int i = 0; i < c.length; i++) {
			float t1 = (float)((c[i].getX1() + 1.1)/2.2);
			float t2 = (float)((c[i].getX2() + 1.1)/2.2);
			float x = (1 - t1)*10 + t1*780;
			float y = (1 - t2)*20 + t2*770;
			g2.setPaint(Color.RED);
			g2.drawString("*", x, y);
		}
	}
		

}
