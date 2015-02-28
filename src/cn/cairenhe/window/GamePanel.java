package cn.cairenhe.window;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import javax.swing.JPanel;
import cn.cairenhe.Data.ImageForDraw;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<ImageForDraw> imagefordrawlist = null;
	private Image backGroundImage = null;
	
	public GamePanel(LinkedList<ImageForDraw> imagefordrawlist,Image backGroundImage){
		this.imagefordrawlist = imagefordrawlist;
		this.backGroundImage = backGroundImage;
		//this.setSize(500,400);
		 
	}
	
	@Override
	public void paintComponent(Graphics g){
		//super.paintComponent(g);		
		super.paintComponent(g);
		//»­±³¾°
		g.drawImage(backGroundImage, 0, 0,this);
		//»­ÅÆ
		for(ImageForDraw imagefordraw : imagefordrawlist){
			g.drawImage(imagefordraw.getImgae(), imagefordraw.getLocation_x(),imagefordraw.getLocation_y(),this);
		}
	}
}
