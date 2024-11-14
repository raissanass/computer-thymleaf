package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Computer;
import com.example.demo.service.ComputerService;

import jakarta.validation.Valid;

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
	  @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	  

	    @GetMapping("/computer")
	    public String viewcomputer(Authentication authentication, Model model) {
	        model.addAttribute("computer", service.findALLcomputer());
	        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
	            return "all-computer-admin"; 
	        } else {
	            return "all-computer-user"; 
	        }
	    }
	    
	    
	    @GetMapping("/add")
	    public String showcomputerForm(Model model) {
	        model.addAttribute("computer", new Computer());
	        return "add-computer";
	    }
	    
	    @PostMapping("/addcomputer")
	    public String addcomputer(@Valid @ModelAttribute("computer") Computer computer , BindingResult result, Model model) {
	    	
	    		service.addComputer(computer);
	    		return "redirect:/computer";
	    	}

	    @GetMapping("/edit/{id}") 
	    public String showEditComputerForm(@PathVariable("id") Integer id, Model model)
	    {
	    	Computer computer = service.findComputerById(id);
	    	model.addAttribute("computer", computer);
	    	return "edit-computer"; 
	    }
	    
	    @PostMapping("/edit/{id}")
	    public String editComputer(@PathVariable("id") Integer id, @ModelAttribute("computer") Computer computer) {
	        computer.setId(id);
	        service.updateComputer(computer);
	        return "redirect:/computer";
	    }
	    @GetMapping("/delete/{id}")
	    public String deleteComputer(@PathVariable("id") Integer id)
	    {
	   	 service.deletebyId(id);
	   	 return "redirect:/computer";
	    }

}
