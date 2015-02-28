package cn.cairenhe.Data;

import java.awt.Image;

public class ImageForDraw {
	private Image imgae;
	private int location_x;
	private int location_y;
	
	public ImageForDraw(Image image, int location_x, int location_y){
		this.imgae  = image;
		this.location_x = location_x;
		this.location_y = location_y;
	}
	
	public Image getImgae() {
		return imgae;
	}
	public void setImgae(Image imgae) {
		this.imgae = imgae;
	}
	public int getLocation_x() {
		return location_x;
	}
	public void setLocation_x(int locationX) {
		location_x = locationX;
	}
	public int getLocation_y() {
		return location_y;
	}
	public void setLocation_y(int locationY) {
		location_y = locationY;
	}
}
