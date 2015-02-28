package cn.cairenhe.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class crh_MouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标拖动\nx:" + e.getX() + " y:" + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标移动\nx:" + e.getX() + " y:" + e.getY());
		
	}
}
