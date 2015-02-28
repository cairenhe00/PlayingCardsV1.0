package cn.cairenhe.tool;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tool {
	public static Image LoadImage(String imagepath){
		Image image;
		try {
			image = ImageIO.read(new File(imagepath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("¼ÓÔØÍ¼Æ¬³ö´í");
			return null;
		}
		return image;
	}
}
