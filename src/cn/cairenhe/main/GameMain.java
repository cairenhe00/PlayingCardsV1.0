package cn.cairenhe.main;

import java.awt.Image;
import java.util.LinkedList;

import cn.cairenhe.Data.Card;
import cn.cairenhe.Data.CardNumber;
import cn.cairenhe.Data.CardStateFlag;
import cn.cairenhe.Data.CardType;
import cn.cairenhe.Data.ImageForDraw;
import cn.cairenhe.tool.Tool;
import cn.cairenhe.window.MainWindow;
import cn.cairenhe.window.MouseMonitor;

public class GameMain {
	private MainWindow mainwindow = null;
	private LinkedList<ImageForDraw> imagefordrawlist;
	private LinkedList<Card> cardlist;//
	private Image backgroundimage;//����ͼƬ
	private Controller controller;//��Ϸ�߼�������
	public GameMain(){
		imagefordrawlist = new LinkedList<ImageForDraw>();
		cardlist = new LinkedList<Card>();
		//������Դ
		loadResource();
		
		//ʵ������Ϸ�߼�������
		controller = new Controller(cardlist, imagefordrawlist);//controller�ڴ�����ʱ�����imagefordrawlist
		
		
		//ʵ����������
		mainwindow = new MainWindow(imagefordrawlist, backgroundimage);
		mainwindow.setMouseListener(MouseMonitor.crh_mouselistener);
		mainwindow.setMouseMotionListener(MouseMonitor.crh_mousemotionlistener);
		
		controller.setMainwindow(mainwindow);
		
		controller.start();
	}
	
	/**
	 * ������Դ
	 * @return
	 */
	private boolean loadResource(){
		/**
		 * ���ؿ���
		 */
		if( !loadCard() ){
			return false;
		}
		/**
		 * ���ر���
		 */
		if( !loadBackGroundImage() ){
			return false;
		}
		return true;
	}
	
	private boolean loadCard(){
		try{
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_A, 0, 0, Resource.CardBack, Resource.Club_A, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_8, 0, 0, Resource.CardBack, Resource.Club_Eight, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_5, 0, 0, Resource.CardBack, Resource.Club_Five, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_4, 0, 0, Resource.CardBack, Resource.Club_Four, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_J, 0, 0, Resource.CardBack, Resource.Club_J, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_K, 0, 0, Resource.CardBack, Resource.Club_K, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_9, 0, 0, Resource.CardBack, Resource.Club_Nine, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_Q, 0, 0, Resource.CardBack, Resource.Club_Q, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_7, 0, 0, Resource.CardBack, Resource.Club_Seven, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_6, 0, 0, Resource.CardBack, Resource.Club_Six, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_10, 0, 0, Resource.CardBack, Resource.Club_Ten, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_3, 0, 0, Resource.CardBack, Resource.Club_Three, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.CLUB, CardNumber.NUMBER_2, 0, 0, Resource.CardBack, Resource.Club_Two, CardStateFlag.FRONT, true));
			
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_A,0, 0, Resource.CardBack, Resource.Diamond_A, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_8,0, 0, Resource.CardBack, Resource.Diamond_Eight, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_5,0, 0, Resource.CardBack, Resource.Diamond_Five, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_4,0, 0, Resource.CardBack, Resource.Diamond_Four, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_J,0, 0, Resource.CardBack, Resource.Diamond_J, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_K,0, 0, Resource.CardBack, Resource.Diamond_K, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_9,0, 0, Resource.CardBack, Resource.Diamond_Nine, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_Q,0, 0, Resource.CardBack, Resource.Diamond_Q, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_7,0, 0, Resource.CardBack, Resource.Diamond_Seven, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_6,0, 0, Resource.CardBack, Resource.Diamond_Six, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_10,0, 0, Resource.CardBack, Resource.Diamond_Ten, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_3,0, 0, Resource.CardBack, Resource.Diamond_Three, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.DIAMOND, CardNumber.NUMBER_2,0, 0, Resource.CardBack, Resource.Diamond_Two, CardStateFlag.FRONT, true));
			
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_A,0, 0, Resource.CardBack, Resource.Heart_A, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_8,0, 0, Resource.CardBack, Resource.Heart_Eight, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_5,0, 0, Resource.CardBack, Resource.Heart_Five, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_4,0, 0, Resource.CardBack, Resource.Heart_Four, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_J,0, 0, Resource.CardBack, Resource.Heart_J, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_K,0, 0, Resource.CardBack, Resource.Heart_K, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_9,0, 0, Resource.CardBack, Resource.Heart_Nine, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_Q,0, 0, Resource.CardBack, Resource.Heart_Q, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_7,0, 0, Resource.CardBack, Resource.Heart_Seven, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_6,0, 0, Resource.CardBack, Resource.Heart_Six, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_10,0, 0, Resource.CardBack, Resource.Heart_Ten, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_3,0, 0, Resource.CardBack, Resource.Heart_Three, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.HEART, CardNumber.NUMBER_2,0, 0, Resource.CardBack, Resource.Heart_Two, CardStateFlag.FRONT, true));
			
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_A,0, 0, Resource.CardBack, Resource.Spade_A, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_8,0, 0, Resource.CardBack, Resource.Spade_Eight, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_5,0, 0, Resource.CardBack, Resource.Spade_Five, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_4,0, 0, Resource.CardBack, Resource.Spade_Four, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_J,0, 0, Resource.CardBack, Resource.Spade_J, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_K,0, 0, Resource.CardBack, Resource.Spade_K, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_9,0, 0, Resource.CardBack, Resource.Spade_Nine, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_Q,0, 0, Resource.CardBack, Resource.Spade_Q, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_7,0, 0, Resource.CardBack, Resource.Spade_Seven, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_6,0, 0, Resource.CardBack, Resource.Spade_Six, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_10,0, 0, Resource.CardBack, Resource.Spade_Ten, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_3,0, 0, Resource.CardBack, Resource.Spade_Three, CardStateFlag.FRONT, true));
			cardlist.add(new Card(CardType.SPADE, CardNumber.NUMBER_2,0, 0, Resource.CardBack, Resource.Spade_Two, CardStateFlag.FRONT, true));
		}catch(Exception e){
			System.out.println("���ؿ��ƹ��̳���");
			return false;
		}
		return true;
	}
	
	private boolean loadBackGroundImage(){
		try{
			backgroundimage = Tool.LoadImage(Resource.BackGroundImage);
		}catch(Exception e){
			System.out.println("���ر���ͼƬ����");
			return false;
		}
		return true;
	}
	/**
	 * �������
	 * @param args
	 */
	public static void main(String args[]){
		new GameMain();
	}
	
}
