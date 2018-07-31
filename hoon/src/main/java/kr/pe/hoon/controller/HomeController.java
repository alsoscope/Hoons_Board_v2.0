package kr.pe.hoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="about", method=RequestMethod.GET)
	public String about() {
		return "about";
	}
}
