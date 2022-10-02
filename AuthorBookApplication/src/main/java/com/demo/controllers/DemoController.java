package com.demo.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DemoController {
	
	@GetMapping("home")
	public String greet(){
		return "Welcome user";
	}
	

	@GetMapping("/movies")
	public List<String> getAllMovies(){
		return Arrays.asList(
			"Superman is swimming",
			"Batman in a day",
			"Hulk is not green now"
		);
	}
	
	@GetMapping("/directors")
	public List<String> getAllDirectors(){
		return Arrays.asList(
			"Thomas",
			"Billy",
			"Mike",
			"Tom",
			"Bingo"
		);
	}
	
}

