package net.kdigital.hackathon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tech")
@Slf4j
public class TechController {
//	@Autowired
//	TechService techService;
	
	
	@GetMapping("/tech")
	public String tech() {
		return "/tech/tech";
	}
}
