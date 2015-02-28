package cn.cairenhe.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import cn.cairenhe.Data.Card;
import cn.cairenhe.Data.CardStateFlag;
import cn.cairenhe.Data.ImageForDraw;
import cn.cairenhe.Data.Message;
import cn.cairenhe.Data.MessageType;
import cn.cairenhe.window.MainWindow;

public class Controller extends Thread{

	//队列
	public static BlockingQueue<Message> MessageQueue;
	private LinkedList<Card> cardlist;
	private LinkedList<ImageForDraw> imagefordrawlist;//绘制图片队列，由逻辑控制器修改添加
	private MainWindow mainwindow = null;//主界面对象，用于控制图像重绘
	//private Vector<ImageForDraw> map_cardlist_and_imagefordrawlist = null;
	private LinkedList<Card> CardPoolQueue = null;//发牌后剩余的牌池
	private LinkedList<Card> CardPoolOpenQueue = null;//展开的牌池
	private LinkedList<Card> HeartCardQueue = null;//红桃完成队列
	private LinkedList<Card> ClubCardQueue = null;//梅花完成队列
	private LinkedList<Card> DiamondCardQueue = null;//方块完成队列
	private LinkedList<Card> SpadeCardQueue = null;//黑桃完成队列
	
	private LinkedList<Card> Queue_1 = null;//被分配的队列1
	private LinkedList<Card> Queue_2 = null;//被分配的队列2
	private LinkedList<Card> Queue_3 = null;//被分配的队列3
	private LinkedList<Card> Queue_4 = null;//被分配的队列4
	private LinkedList<Card> Queue_5 = null;//被分配的队列5
	private LinkedList<Card> Queue_6 = null;//被分配的队列6
	private LinkedList<Card> Queue_7 = null;//被分配的队列7
	
	private final int queue1_initsize = 1;
	private final int queue2_initsize = 2;
	private final int queue3_initsize = 3;
	private final int queue4_initsize = 4;
	private final int queue5_initsize = 5;
	private final int queue6_initsize = 6;
	private final int queue7_initsize = 7;
	private final int queuePool_initsize = 24;
	
	//牌池位置
	private final int cardpool_x = 10;
	private final int cardpool_y = 10;
	//展开牌池位置
	private final int cardopenpool_x = 130;
	private final int cardoepnpool_y = 10;
	//红桃队列位置
	private final int  heartqueue_x = 640;
	private final int  heartqueue_y = 10;
	//梅花队列位置
	private final int clubcardqueue_x = 760;
	private final int clubcardqueue_y = 10;
	//方块队列位置
	private final int diamondqueue_x = 880;
	private final int diamondqueue_y = 10;
	//黑桃队列 位置
	private final int spadequeue_x = 1000;
	private final int spadequeue_y = 10;
	//队列1位置
	private final int queue1_x = 280;
	private final int queue1_y = 250;
	//队列2位置
	private final int queue2_x = 400;
	private final int queue2_y = 250;
	//队列3位置
	private final int queue3_x = 520;
	private final int queue3_y = 250;
	//队列4位置
	private final int queue4_x = 640;
	private final int queue4_y = 250;
	//队列5位置
	private final int queue5_x = 760;
	private final int queue5_y = 250;
	//队列6位置
	private final int queue6_x = 880;
	private final int queue6_y = 250;
	//队列7位置
	private final int queue7_x = 1000;
	private final int queue7_y = 250;
	
	private final int offset_y = 15;//队列1~7堆叠的时候的Y偏移
	
	private int completequeuesize = 13;
	private int cardpoolsize = 24;
	private int cardqueuemaxsize = 19;

	private int cardnumberMAX = 52;
	
	private boolean ifEnd = false;
	private Message message = null;
	private Card cardtmp = null;
	public Controller(LinkedList<Card> cardlist, LinkedList<ImageForDraw> imagefordrawlist) {
		MessageQueue = new ArrayBlockingQueue<Message>(Setting.MessageQueueSize);
		this.cardlist = cardlist;
		this.imagefordrawlist = imagefordrawlist;
		
		//游戏排队队列初始化
		CardPoolQueue = new LinkedList<Card>();
		CardPoolOpenQueue = new LinkedList<Card>();
		HeartCardQueue = new LinkedList<Card>();
		ClubCardQueue = new LinkedList<Card>();
		DiamondCardQueue = new LinkedList<Card>();
		SpadeCardQueue = new LinkedList<Card>();
		Queue_1 = new LinkedList<Card>();
		Queue_2 = new LinkedList<Card>();
		Queue_3 = new LinkedList<Card>();
		Queue_4 = new LinkedList<Card>();
		Queue_5 = new LinkedList<Card>();
		Queue_6 = new LinkedList<Card>();
		Queue_7 = new LinkedList<Card>();
				
		//分牌
		sendCard();
		//初始化待绘制牌堆
		init_imagefordrawlist();//初始化imagefordrawlist
	}
	
	public void setMainwindow(MainWindow mainwindow) {
		this.mainwindow = mainwindow;
	}
	
	@Override
	public void run(){
		while( !ifEnd ){
			if( !MessageQueue.isEmpty() ){
				message = MessageQueue.poll();
				DoControl();//对界面发来的用户操作消息进行处理
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 核心处理功能
	 */
	private void DoControl(){
		switch(message.getMessageType()){
		case MessageType.CLICK:
			/**
			 * 鼠标单击事件
			 */
			cardtmp = cardlist.get(1);//测试
			if(cardtmp.isEnable()){
				switch(cardtmp.getState()){
				case CardStateFlag.BACK:
					System.out.println("单击事件：back->front");
					cardtmp.ChangeToFront();
					imagefordrawlist.get(cardlist.indexOf(cardtmp)).setImgae(cardtmp.getImage_front());//逻辑上cardlist和imagefordraw是 位置映射对应
					mainwindow.Repaint();
					break;
				case CardStateFlag.FRONT:
					System.out.println("单击事件：front->back");
					cardtmp.ChangeToBack();
					imagefordrawlist.get(cardlist.indexOf(cardtmp)).setImgae(cardtmp.getImage_back());
					mainwindow.Repaint();
					break;
				}
			}
			break;
		case MessageType.DOUBLECLICK:
			/**
			 * 左键双击事件
			 */
			break;
		case MessageType.DRAG:
			/**
			 * 鼠标拖动事件
			 */
			break;
		case MessageType.PRESS:
			/**
			 * 鼠标按下事件
			 */
			break;
		case MessageType.RELLEASE:
			/**
			 * 鼠标松开事件
			 */
			break;
		}
	}
	
	/**
	 * 发牌
	 */
	private void sendCard(){
		int sendedcount = 0;
		
		for(int i = 0; i < queue1_initsize - 1; i++){
			//分牌改变卡牌状态
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_1.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_1.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		for(int i = 0; i < queue2_initsize -1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_2.add(cardlist.get(sendedcount));			
			sendedcount++;
		}
		Queue_2.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		for(int i = 0; i < queue3_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_3.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_3.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		for(int i = 0; i < queue4_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_4.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_4.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		for(int i = 0; i < queue5_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_5.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_5.add(cardlist.get(sendedcount++));//添加一张正面牌
		for(int i = 0; i < queue6_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_6.add(cardlist.get(sendedcount));		
			sendedcount++;
		}
		Queue_6.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		for(int i = 0; i < queue7_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//背面则禁用卡片
			Queue_7.add(cardlist.get(sendedcount));			
			sendedcount++;
		}
		Queue_7.add(cardlist.get(sendedcount++));//添加一张正面牌
		
		//把剩下的牌放到牌池里
		for(int i = sendedcount; i < cardnumberMAX - 1 ;i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();
			CardPoolQueue.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		//添加第一张牌
		CardPoolQueue.add(cardlist.get(sendedcount));
		
	}
		
	/**
	 * 初始化用于绘制的卡牌
	 */
	private void init_imagefordrawlist(){
		ImageForDraw imgaefordrawtmp = null;
		//从队1中取牌
		for( int i = 0;i<Queue_1.size();i++){
			switch(Queue_1.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_1.get(i).getImage_front(), queue1_x, queue1_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_1.get(i).getImage_back(), queue1_x, queue1_y + i * offset_y));
				break;
			}			
		}
		
		//从队2中取牌
		for( int i = 0;i<Queue_2.size();i++){
			switch(Queue_2.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_2.get(i).getImage_front(), queue2_x, queue2_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_2.get(i).getImage_back(), queue2_x, queue2_y + i * offset_y));
				break;
			}			
		}
		//从队3中取牌
		for( int i = 0;i<Queue_3.size();i++){
			switch(Queue_3.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_3.get(i).getImage_front(), queue3_x, queue3_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_3.get(i).getImage_back(), queue3_x, queue3_y + i * offset_y));
				break;
			}			
		}
		//从队4中取牌
		for( int i = 0;i<Queue_4.size();i++){
			switch(Queue_4.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_4.get(i).getImage_front(), queue4_x, queue4_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_4.get(i).getImage_back(), queue4_x, queue4_y + i * offset_y));
				break;
			}			
		}
		//从队5中取牌
		for( int i = 0;i<Queue_5.size();i++){
			switch(Queue_5.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_5.get(i).getImage_front(), queue5_x, queue5_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_5.get(i).getImage_back(), queue5_x, queue5_y + i * offset_y));
				break;
			}			
		}
		//从队6中取牌
		for( int i = 0;i<Queue_6.size();i++){
			switch(Queue_6.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_6.get(i).getImage_front(), queue6_x, queue6_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_6.get(i).getImage_back(), queue6_x, queue6_y + i * offset_y));
				break;
			}			
		}
		//从队7中取牌
		for( int i = 0;i<Queue_7.size();i++){
			switch(Queue_7.get(i).getState()){
			case CardStateFlag.FRONT:
				imagefordrawlist.add(new ImageForDraw(Queue_7.get(i).getImage_front(), queue7_x, queue7_y + i * offset_y));
				break;
			case CardStateFlag.BACK:
				imagefordrawlist.add(new ImageForDraw(Queue_7.get(i).getImage_back(), queue7_x, queue7_y + i * offset_y));
				break;
			}			
		}
		//从剩余牌队列中取牌
		for( int i = 0;i<CardPoolQueue.size();i++){
			imagefordrawlist.add(new ImageForDraw(CardPoolQueue.get(i).getImage_back(), cardpool_x, cardpool_y));		
		}
	}
	
	/**
	 * 停止run中循环
	 */
	public void End(){
		ifEnd = true;
	}
	
	
}
