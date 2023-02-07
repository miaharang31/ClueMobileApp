package com.cs309.tutorial.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
	
	
	@GetMapping("/book")
	public String getTest(@RequestParam String message) {
		return String.format("Hello, %s! You sent a get request with a parameter!", message);
	}
	
	@PostMapping("/book")
	public String createbookposttest(@RequestBody String words)  {
		//message.save(words);
		return String.format("Hello, %s! You sent a post request with a parameter!", words);
	}
	
	@PostMapping("/postTest2")
	public String postTest2(@RequestBody TestData testData) {
		//TODO
		return String.format("Hello, %s! You sent a post request with a requestbody!", testData.getMessage());
	}
	
	@DeleteMapping("/deleteTest")
	public void deleteTest() {
		//TODO
	}
	
	@PutMapping("/putTest")
	public void putTest() {
		//TODO
	}
}
