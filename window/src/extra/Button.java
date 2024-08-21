package extra;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import scoll2D.Panel2D;

public class Button implements MouseListener{
	Panel2D p;
	public Rectangle button;
	public int x;
	public int y;
	public int width;
	public int height;
	public boolean pe = false;
	
	
	public Button(Panel2D p, Rectangle button) {
		this.p=p;
		this.button=button;
		x=button.x;
		y=button.y;
		width=button.width;
		height=button.height;
		
		
	}
	public void modButton(Rectangle button) {
		this.button=button;
		x=button.x;
		y=button.y;
		width=button.width;
		height=button.height;
	}
	public boolean entered() {
		return button.contains(p.m);
	}
	public boolean clicked(){
		if(entered() && p.mthis) {
			return true;
		}
		else {
			return false;
		}
	}
	public void move(int x,int y) {
		button.x = x;
		button.y = y;
		x=this.x;
		y=this.y;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		}
	@Override
	public void mousePressed(MouseEvent e) {
		
		}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		}
	@Override
	public void mouseExited(MouseEvent e) {
		
		}
}
