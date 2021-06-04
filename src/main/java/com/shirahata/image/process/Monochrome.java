package com.shirahata.image.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import static org.bytedeco.opencv.global.opencv_core.NORM_MINMAX;
import static org.bytedeco.opencv.global.opencv_core.normalize;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.blur;

import static org.bytedeco.opencv.global.opencv_core.CV_8UC1;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.THRESH_BINARY;
import static org.bytedeco.opencv.global.opencv_imgproc.threshold;


public class Monochrome {

	@SuppressWarnings("resource")
	public static void monochrome(String url) throws IOException {
		BufferedImage bi = ImageIO.read(new File(url));
		OpenCVFrameConverter.ToIplImage cv = new OpenCVFrameConverter.ToIplImage();
		Java2DFrameConverter jcv = new Java2DFrameConverter();

		Mat mat = cv.convertToMat(jcv.convert(bi));
		Mat gray = copyRegion(toGray(mat), 1000, 800, 600, 400);
		Mat blurred = new Mat(gray.size(), gray.type());
		blur(gray, blurred, new Size(3, 3));
		Mat normalized = new Mat(blurred.size(), blurred.type());
		Mat result = new Mat(normalized.size(), normalized.type());
        threshold(normalized, result, 50, 255, THRESH_BINARY);
        imwrite(new File(url).getAbsolutePath(), result);
	}

	public static Mat copyRegion(Mat input, int x, int y, int width, int height) {
		Mat cropped = new Mat(new Size(width, height), input.type());
		int dtop = y;
		int dbottom = input.rows() - dtop - cropped.rows();
		int dleft = x;
		int dright = input.cols() - dleft - cropped.cols();
		input.adjustROI(-dtop, -dbottom, -dleft, -dright).copyTo(cropped);
		return cropped;
	}

	public static Mat toGray(Mat color) {
		Mat grey = new Mat(color.size(), CV_8UC1);
		cvtColor(color, grey, COLOR_BGR2GRAY);
		return grey;
	}

}
