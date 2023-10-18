package net.kdigital.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.hackathon.service.PortService;

@Controller
@RequestMapping("/port")
@Slf4j
public class PortController {
	@Autowired
	PortService portService;
	
	
	@GetMapping("/port")
	public String port() {
		return "/port/port";
	}
}
