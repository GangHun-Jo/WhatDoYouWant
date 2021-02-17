package com.chopa.wdyw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
