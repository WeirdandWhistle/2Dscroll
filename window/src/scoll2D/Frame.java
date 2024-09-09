package scoll2D;

import javax.swing.JFrame;



public class Frame extends JFrame{
	
	Panel2D panel;
	public int x =500;
	public int y = 100;
	
	
	Frame(){
		panel = new Panel2D();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(500,100);
		this.setTitle("2D Scroll game");
		this.addKeyListener(panel);
		this.addMouseListener(panel.mh);
		
		
		this.add(panel);
		this.pack();
	}

}
