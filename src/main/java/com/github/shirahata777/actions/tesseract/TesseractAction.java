package com.github.shirahata777.actions.tesseract;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import com.github.shirahata777.actions.image.opencv.monochrome.Monochrome;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class TesseractAction {

	public String action() throws TesseractException, IOException {
		try {
			Monochrome.monochrome("src/main/resources/static/images/", "test.jpeg");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage img = imageLoad("src/main/resources/static/images/test_output.jpg");
		return tesseractModelSetting("src/main/resources/static/model", "jpn").doOCR(img);
	}

	private ITesseract tesseractModelSetting(String modeldirectlyPath, String modelLang) {
		ITesseract tesseract = new Tesseract();
		tesseract.setDatapath(modeldirectlyPath);
		tesseract.setLanguage(modelLang);

		return tesseract;
	}

	public BufferedImage imageLoad(String imagePath) throws IOException {
		File file = new File(imagePath);

		return ImageIO.read(file);
	}

}
