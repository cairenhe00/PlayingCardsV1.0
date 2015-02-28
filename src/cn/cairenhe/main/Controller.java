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

	//����
	public static BlockingQueue<Message> MessageQueue;
	private LinkedList<Card> cardlist;
	private LinkedList<ImageForDraw> imagefordrawlist;//����ͼƬ���У����߼��������޸����
	private MainWindow mainwindow = null;//������������ڿ���ͼ���ػ�
	//private Vector<ImageForDraw> map_cardlist_and_imagefordrawlist = null;
	private LinkedList<Card> CardPoolQueue = null;//���ƺ�ʣ����Ƴ�
	private LinkedList<Card> CardPoolOpenQueue = null;//չ�����Ƴ�
	private LinkedList<Card> HeartCardQueue = null;//������ɶ���
	private LinkedList<Card> ClubCardQueue = null;//÷����ɶ���
	private LinkedList<Card> DiamondCardQueue = null;//������ɶ���
	private LinkedList<Card> SpadeCardQueue = null;//������ɶ���
	
	private LinkedList<Card> Queue_1 = null;//������Ķ���1
	private LinkedList<Card> Queue_2 = null;//������Ķ���2
	private LinkedList<Card> Queue_3 = null;//������Ķ���3
	private LinkedList<Card> Queue_4 = null;//������Ķ���4
	private LinkedList<Card> Queue_5 = null;//������Ķ���5
	private LinkedList<Card> Queue_6 = null;//������Ķ���6
	private LinkedList<Card> Queue_7 = null;//������Ķ���7
	
	private final int queue1_initsize = 1;
	private final int queue2_initsize = 2;
	private final int queue3_initsize = 3;
	private final int queue4_initsize = 4;
	private final int queue5_initsize = 5;
	private final int queue6_initsize = 6;
	private final int queue7_initsize = 7;
	private final int queuePool_initsize = 24;
	
	//�Ƴ�λ��
	private final int cardpool_x = 10;
	private final int cardpool_y = 10;
	//չ���Ƴ�λ��
	private final int cardopenpool_x = 130;
	private final int cardoepnpool_y = 10;
	//���Ҷ���λ��
	private final int  heartqueue_x = 640;
	private final int  heartqueue_y = 10;
	//÷������λ��
	private final int clubcardqueue_x = 760;
	private final int clubcardqueue_y = 10;
	//�������λ��
	private final int diamondqueue_x = 880;
	private final int diamondqueue_y = 10;
	//���Ҷ��� λ��
	private final int spadequeue_x = 1000;
	private final int spadequeue_y = 10;
	//����1λ��
	private final int queue1_x = 280;
	private final int queue1_y = 250;
	//����2λ��
	private final int queue2_x = 400;
	private final int queue2_y = 250;
	//����3λ��
	private final int queue3_x = 520;
	private final int queue3_y = 250;
	//����4λ��
	private final int queue4_x = 640;
	private final int queue4_y = 250;
	//����5λ��
	private final int queue5_x = 760;
	private final int queue5_y = 250;
	//����6λ��
	private final int queue6_x = 880;
	private final int queue6_y = 250;
	//����7λ��
	private final int queue7_x = 1000;
	private final int queue7_y = 250;
	
	private final int offset_y = 15;//����1~7�ѵ���ʱ���Yƫ��
	
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
		
		//��Ϸ�ŶӶ��г�ʼ��
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
				
		//����
		sendCard();
		//��ʼ���������ƶ�
		init_imagefordrawlist();//��ʼ��imagefordrawlist
	}
	
	public void setMainwindow(MainWindow mainwindow) {
		this.mainwindow = mainwindow;
	}
	
	@Override
	public void run(){
		while( !ifEnd ){
			if( !MessageQueue.isEmpty() ){
				message = MessageQueue.poll();
				DoControl();//�Խ��淢�����û�������Ϣ���д���
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
	 * ���Ĵ�����
	 */
	private void DoControl(){
		switch(message.getMessageType()){
		case MessageType.CLICK:
			/**
			 * ��굥���¼�
			 */
			cardtmp = cardlist.get(1);//����
			if(cardtmp.isEnable()){
				switch(cardtmp.getState()){
				case CardStateFlag.BACK:
					System.out.println("�����¼���back->front");
					cardtmp.ChangeToFront();
					imagefordrawlist.get(cardlist.indexOf(cardtmp)).setImgae(cardtmp.getImage_front());//�߼���cardlist��imagefordraw�� λ��ӳ���Ӧ
					mainwindow.Repaint();
					break;
				case CardStateFlag.FRONT:
					System.out.println("�����¼���front->back");
					cardtmp.ChangeToBack();
					imagefordrawlist.get(cardlist.indexOf(cardtmp)).setImgae(cardtmp.getImage_back());
					mainwindow.Repaint();
					break;
				}
			}
			break;
		case MessageType.DOUBLECLICK:
			/**
			 * ���˫���¼�
			 */
			break;
		case MessageType.DRAG:
			/**
			 * ����϶��¼�
			 */
			break;
		case MessageType.PRESS:
			/**
			 * ��갴���¼�
			 */
			break;
		case MessageType.RELLEASE:
			/**
			 * ����ɿ��¼�
			 */
			break;
		}
	}
	
	/**
	 * ����
	 */
	private void sendCard(){
		int sendedcount = 0;
		
		for(int i = 0; i < queue1_initsize - 1; i++){
			//���Ƹı俨��״̬
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_1.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_1.add(cardlist.get(sendedcount++));//���һ��������
		
		for(int i = 0; i < queue2_initsize -1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_2.add(cardlist.get(sendedcount));			
			sendedcount++;
		}
		Queue_2.add(cardlist.get(sendedcount++));//���һ��������
		
		for(int i = 0; i < queue3_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_3.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_3.add(cardlist.get(sendedcount++));//���һ��������
		
		for(int i = 0; i < queue4_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_4.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_4.add(cardlist.get(sendedcount++));//���һ��������
		
		for(int i = 0; i < queue5_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_5.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		Queue_5.add(cardlist.get(sendedcount++));//���һ��������
		for(int i = 0; i < queue6_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_6.add(cardlist.get(sendedcount));		
			sendedcount++;
		}
		Queue_6.add(cardlist.get(sendedcount++));//���һ��������
		
		for(int i = 0; i < queue7_initsize - 1; i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();//��������ÿ�Ƭ
			Queue_7.add(cardlist.get(sendedcount));			
			sendedcount++;
		}
		Queue_7.add(cardlist.get(sendedcount++));//���һ��������
		
		//��ʣ�µ��Ʒŵ��Ƴ���
		for(int i = sendedcount; i < cardnumberMAX - 1 ;i++){
			cardlist.get(sendedcount).ChangeToBack();
			cardlist.get(sendedcount).Disable();
			CardPoolQueue.add(cardlist.get(sendedcount));
			sendedcount++;
		}
		//��ӵ�һ����
		CardPoolQueue.add(cardlist.get(sendedcount));
		
	}
		
	/**
	 * ��ʼ�����ڻ��ƵĿ���
	 */
	private void init_imagefordrawlist(){
		ImageForDraw imgaefordrawtmp = null;
		//�Ӷ�1��ȡ��
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
		
		//�Ӷ�2��ȡ��
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
		//�Ӷ�3��ȡ��
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
		//�Ӷ�4��ȡ��
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
		//�Ӷ�5��ȡ��
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
		//�Ӷ�6��ȡ��
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
		//�Ӷ�7��ȡ��
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
		//��ʣ���ƶ�����ȡ��
		for( int i = 0;i<CardPoolQueue.size();i++){
			imagefordrawlist.add(new ImageForDraw(CardPoolQueue.get(i).getImage_back(), cardpool_x, cardpool_y));		
		}
	}
	
	/**
	 * ֹͣrun��ѭ��
	 */
	public void End(){
		ifEnd = true;
	}
	
	
}
