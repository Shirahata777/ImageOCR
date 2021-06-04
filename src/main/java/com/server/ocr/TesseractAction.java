package com.server.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractAction {

	public String action() throws TesseractException, IOException {
		BufferedImage img = imageLoad("src/main/resources/static/images/test.jpeg");
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
