package be.bt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

	@GetMapping("/public")
	public String sayPublicMsg() {
		return "Helloo, this is the public message!";
	}
	
	@GetMapping("/secret")
	public String sayASecretMsg() {
		return "Secret, Secret, Keep It ...";
	}
}
