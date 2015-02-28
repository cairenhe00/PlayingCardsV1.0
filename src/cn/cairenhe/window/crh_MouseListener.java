package cn.cairenhe.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.cairenhe.Data.Message;
import cn.cairenhe.Data.MessageType;

public class crh_MouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标单击");
		cn.cairenhe.main.Controller.MessageQueue.add(new Message(MessageType.CLICK, e.getY(), e.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标进入窗体");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标离开窗体");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标按下");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("鼠标释放");
	}

}
