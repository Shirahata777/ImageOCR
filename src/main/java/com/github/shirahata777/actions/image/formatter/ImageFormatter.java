package com.github.shirahata777.actions.image.opencv.monochrome;

import java.io.IOException;
import java.net.URISyntaxException;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Monochrome {
	public static void monochrome(String fileDirPath, String imageName) throws URISyntaxException, IOException {
		Loader.load(opencv_java.class);
		Mat mat = Imgcodecs.imread(fileDirPath + imageName);
		Mat gray = new Mat();
		Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY); // 画像のグレースケール変換
		Mat s16 = new Mat();
		gray.convertTo(s16, CvType.CV_16S);
		Imgcodecs.imwrite("src/main/resources/static/images/test_output.jpg", s16);
	}
}
