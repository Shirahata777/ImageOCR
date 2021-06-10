package shirahata777.image.process;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Monochrome {

	private static final File TEMP_DIR = new File("src/main/resources/static/images/test.jpeg");

	public static void monochrome(String filepath) throws URISyntaxException, IOException {
		Loader.load(opencv_java.class);
		Mat image = Imgcodecs.imread("src/main/resources/static/images/test.jpeg");
		Core.transpose(image, image);
		Core.flip(image, image, 1);
		Imgcodecs.imwrite("src/main/resources/static/images/test_output.jpg", image);
	}

}
