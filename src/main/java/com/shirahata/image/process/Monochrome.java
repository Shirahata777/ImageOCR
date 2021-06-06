package com.shirahata.image.process;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Monochrome {

	private static final File TEMP_DIR = new File("src/main/resources/static/images/test.jpeg");

	public static void monochrome(String filepath) throws URISyntaxException, IOException {
		// 画像を読み込んで、IplIm
//		System.out.println(opencv_imgcodecs.imread(TEMP_DIR.toString()));
		
//		Mat source = opencv_imgcodecs.imread(TEMP_DIR.toString());
//		opencv_imgproc.cvtColor(source, source, opencv_imgproc.COLOR_BGR2HSV);
//		opencv_imgcodecs.imwrite(filepath, source);
		Mat image = Imgcodecs.imread("src/main/resources/static/images/test.jpeg");
	    Core.transpose(image, image);
	    Core.flip(image, image, 1);
	    Imgcodecs.imwrite("src/main/resources/static/images/test_output.jpg", image);
	}

}
