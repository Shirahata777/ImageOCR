package com.github.shirahata777.actions.image.formatter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import com.github.shirahata777.actions.image.manager.ImageManager;

public class ImageFormatter {

	public static void monochrome(String fileDirPath, String imageName) {
		Loader.load(opencv_java.class);
		
		Mat mat = ImageManager.loadMatImage(fileDirPath, imageName);
		
		Mat gray = new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY); // 画像のグレースケール変換
		Mat s16 = new Mat();
		gray.convertTo(s16, CvType.CV_16S);
		
		ImageManager.saveImage(s16);
	}
	
}
