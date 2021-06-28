package com.github.shirahata777.controller;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sourceforge.tess4j.TesseractException;

import com.github.shirahata777.actions.login.LoginManagement;
import com.github.shirahata777.actions.tesseract.TesseractAction;

@Controller
@EnableAutoConfiguration
public class MainController extends LoginManagement {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) throws IOException, TesseractException {
		TesseractAction tesseract = new TesseractAction();

		String ocrString = tesseract.action();
		model.addAttribute("message", ocrString);

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}