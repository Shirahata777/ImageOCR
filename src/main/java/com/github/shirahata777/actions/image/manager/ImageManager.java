package com.github.shirahata777.actions.image.manager;

import java.util.ResourceBundle;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageManager {
	
	private static final String RESOURCE_NAME = "application";
	private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_NAME);
	
	public static Mat loadMatImage(String fileDirPath, String imageName) {
		return Imgcodecs.imread(fileDirPath + imageName);
	}

	public static void saveImage(Mat s16) {
		String outputImagePath = rb.getString("output_image_path");
		String outputImageName = rb.getString("output_image_name");
		String outputImageExtension = rb.getString("output_image_extension");

		Imgcodecs.imwrite(outputImagePath + outputImageName + outputImageExtension, s16);
	}

}
