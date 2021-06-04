package com.shirahata;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shirahata.ocr.model.tesseract.TesseractAction;

import net.sourceforge.tess4j.TesseractException;

@Controller
@EnableAutoConfiguration
public class MainOCR {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) throws IOException, TesseractException {
		TesseractAction tesseract = new TesseractAction();

		String ocrString = tesseract.action();
		model.addAttribute("message", ocrString);

		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(MainOCR.class, args);
	}
}