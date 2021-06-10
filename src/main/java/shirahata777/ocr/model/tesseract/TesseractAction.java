package shirahata777.ocr.model.tesseract;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import shirahata777.image.process.Monochrome;


public class TesseractAction {

	public String action() throws TesseractException, IOException {
		try {
			Monochrome.monochrome("src/main/resources/static/images/test_output.jpg");
		} catch (URISyntaxException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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
