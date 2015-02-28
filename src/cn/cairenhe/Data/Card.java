package cn.cairenhe.Data;

import java.awt.Image;
import cn.cairenhe.tool.Tool;

public class Card {
	private int type;//��������
	private int number;//���Ʊ��
	private int location_x = 0;
	private int location_y = 0;
	private Image image_front = null;
	private Image image_back = null;	
	private int state = CardStateFlag.FRONT;
	private boolean enable = false;
	private int originLocation_x = 0;//�ڿ��Ʊ��϶���ʱ�� ��ԭʼλ��
	private int originLocation_y = 0;
		
	public int getOriginLocation_x() {
		return originLocation_x;
	}
	public int getOriginLocation_y() {
		return originLocation_y;
	}
	public Image getImage_front() {
		return image_front;
	}
	public Image getImage_back() {
		return image_back;
	}
	public int getType() {
		return type;
	}
	public int getNumber() {
		return number;
	}
	public void setOriginLocation(int originlocationx, int originlocationy){
		originLocation_x = originlocationx;
		originLocation_y = originlocationy;
	}
	public int getLocation_x() {
		return location_x;
	}
	public int getLocation_y() {
		return location_y;
	}
	/**
	 * ��õ�ǰ����״̬
	 * @return
	 */
	public int getState() {
		return state;
	}
	/**
	 * ����״̬��Ϊ����
	 */
	public void ChangeToBack(){
		state = CardStateFlag.BACK;
	}
	/**
	 * ����״̬�ı�Ϊ����
	 */
	public void ChangeToFront(){
		state = CardStateFlag.FRONT;
	}
	public void Disable(){
		enable = CardStateFlag.DISABLE;
	}
	public void Enable(){
		enable = CardStateFlag.ENABLE;
	}
	public boolean isEnable(){
		return enable;
	}
	public void setLocation(int x, int y){
		location_x = x;
		location_y = y;
	}
	
	public Card(int type, int number ,int location_x, int location_y, String picturepath_back, String picturepath_front, int state, boolean enable){
		this.type = type;
		this.number = number;
		this.location_x = location_x;
		this.location_y = location_y;		
		originLocation_x = location_x;
		originLocation_y = location_y;
		this.state = state;
		this.enable = enable;
		image_back = Tool.LoadImage(picturepath_back);//����ͼƬ�ļ�
		image_front = Tool.LoadImage(picturepath_front);//����ͼƬ�ļ�
	}
	

}
