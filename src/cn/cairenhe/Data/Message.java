package cn.cairenhe.Data;

public class Message {
	private int MessageType;
	private int location_x;
	private int location_y;
	public int getMessageType() {
		return MessageType;
	}

	public int getLocation_x() {
		return location_x;
	}

	public int getLocation_y() {
		return location_y;
	}	
	public Message(int mt, int lx, int ly){
		MessageType = mt;
		location_x = lx;
		location_y = ly;
	}
}
