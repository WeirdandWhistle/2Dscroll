package scoll2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseHandler implements MouseListener,MouseWheelListener,MouseMotionListener {
	
	public Panel2D p;
	public int notches = 0;
	
	public MouseHandler(Panel2D p) {
		this.p = p;
		p.addMouseListener(this);
		p.addMouseWheelListener(this);
		p.addMouseMotionListener(this);
//		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		p.sx = e.getX();
//		p.sy = e.getY();
//		
//		p.mathRevitavePosScreen();
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton()==1) {
			p.mthis = true;
//			System.out.println("pressed");
//			ctf = tf;
//			System.out.println("bug");
//			m.setLocation(mx, my);
			
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==1) {
//			System.out.println("released");

			p.mthis = false;
		}

		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
//		p.ex=e.getX();
//		p.ey=e.getY();
//		p.m.setLocation(e.getLocationOnScreen());
////		System.out.println(m);
		
//		p.mathRevitavePosEntered();
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		notches = e.getWheelRotation();
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		p.sx = e.getX();
		p.sy = e.getY();
		p.m = e.getPoint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		p.sx = e.getX();
		p.sy = e.getY();
		p.m = e.getPoint();
		
	}

}
