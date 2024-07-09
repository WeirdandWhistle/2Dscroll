package scoll2D;

import javax.swing.*;

public class Frame extends JFrame{
	
	Panel2D panel;
	
	Frame(){
		panel = new Panel2D();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(500,100);
		this.setTitle("2D Scroll game");
		this.addKeyListener(panel);
		
		this.add(panel);
		this.pack();
	}

}
