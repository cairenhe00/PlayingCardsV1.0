package cn.cairenhe.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.cairenhe.Data.Message;
import cn.cairenhe.Data.MessageType;

public class crh_MouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("��굥��");
		cn.cairenhe.main.Controller.MessageQueue.add(new Message(MessageType.CLICK, e.getY(), e.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("�����봰��");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����뿪����");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("��갴��");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����ͷ�");
	}

}
