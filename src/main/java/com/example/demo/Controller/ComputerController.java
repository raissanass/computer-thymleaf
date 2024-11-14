package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Computer;
import com.example.demo.service.ComputerService;

@Controller
public class ComputerController {
	@Autowired 
	ComputerService service;
	
	@GetMapping("/test")
    public String test() {
		Computer cp = new Computer();
		cp.setSerialNumber("234");
		cp.setModel("HP");
		cp.setReleaseDate("12 janvier 2004");
		cp.setStatus(true);
		service.addComputer(cp);
		return "index";
	}
	
	
	@GetMapping("/")
	public String findAll(Model model)
	{
		model.addAttribute("computers", service.findALLcomputer());
		return "All-Computer";
	}

}
