package cn.chenyuxian.discuz.core.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	public void gaussianBlur(String srcImg, String savePath, String saveName, int blurFactor) throws IOException {
		BufferedImage gdImageResource = ImageIO.read(new File(srcImg));
		
	}
	
	private void blur(BufferedImage gdImageResource, int blurFactor) {
		int originalWidth = gdImageResource.getWidth();
		int originalHeight = gdImageResource.getHeight();
		
		int smallestWidth = (int) Math.ceil(originalWidth * Math.pow(0.5, blurFactor));
		int smallestHeight = (int) Math.ceil(originalHeight * Math.pow(0.5, blurFactor));
		
		BufferedImage prevImage = gdImageResource;
		int prevWidth = originalWidth;
		int prevHeight = originalHeight;
		
		for(int i = 0; i < blurFactor; i++) {
			int nextWidth = (int) (smallestWidth * Math.pow(2, i));
			int nextHeight = (int) (smallestHeight * Math.pow(2, i));
			
		}
		
	}
}
