package cn.cairenhe.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class crh_MouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����϶�\nx:" + e.getX() + " y:" + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����ƶ�\nx:" + e.getX() + " y:" + e.getY());
		
	}
}
