package com.github.shirahata777.controller;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sourceforge.tess4j.TesseractException;
import com.github.shirahata777.actions.tesseract.TesseractAction;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@EnableAutoConfiguration
public class MainController extends WebSecurityConfigurerAdapter {

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/index").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		String password = passwordEncoder().encode("password");

		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user").password(password)
				.roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}