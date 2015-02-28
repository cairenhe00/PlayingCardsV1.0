package cn.cairenhe.window;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JFrame;
import cn.cairenhe.Data.ImageForDraw;
import cn.cairenhe.main.Resource;
import cn.cairenhe.main.Setting;
import cn.cairenhe.tool.Tool;

public class MainWindow{
	
	private JFrame gameWindow = null;
	private Toolkit toolkit = null;
	private GamePanel  gamepanel= null;
	
	public MainWindow(LinkedList<ImageForDraw> imagefordrawlist, Image backgroundimage){
		toolkit = Toolkit.getDefaultToolkit();
						
		gameWindow = new JFrame();
		gamepanel = new GamePanel(imagefordrawlist, backgroundimage);
		gameWindow.add(gamepanel);
		gameWindow.setTitle(Setting.Title);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//������Ļ��ȣ�ʹ��Ļ����
		Toolkit.getDefaultToolkit().getScreenSize();
		Setting.X_Coordinate = toolkit.getScreenSize().width/2 - Setting.WindowWidth/2;
		Setting.Y_Coordinate = toolkit.getScreenSize().height/2 - Setting.WindowHight/2;
		
		//������Ϸ����߽磬ʹ��Ϸ�������
		gameWindow.setBounds(Setting.X_Coordinate, Setting.Y_Coordinate, Setting.WindowWidth, Setting.WindowHight);
		gameWindow.setVisible(true);
		
		//����ͼƬ
		
		gameWindow.repaint();
	}
	
	/**
	 * �ػ�
	 */
	public void Repaint(){
		gameWindow.repaint();
	}
	
//	public int LoadImages(LinkedList<Image> lli){		
//		if( null == lli){
//			return -1;
//		}
//		if( lli.size() == 0){
//			return 0;
//		}
//		int count = 0;//���������ɹ����ص�ͼƬ����
//		for(Image img : lli){
//			gamepanel.addImage(img);
//			count++;
//		}
//		return lli.size();
//	}
	
	public boolean setMouseListener(MouseListener ml){
		if( null == gameWindow){//���gameWindow��δʵ����
			return false;
		}
		if( null == ml){//����������Ϊ��
			return false;
		}
		gameWindow.addMouseListener(ml);
		return true;
	}
	
	public boolean setMouseMotionListener(MouseMotionListener mml){
		if( null == gameWindow){//���gameWindow��δʵ����
			return false;
		}
		if( null == mml){//����������Ϊ��
			return false;
		}
		gameWindow.addMouseMotionListener(mml);
		return true;
	}
}
