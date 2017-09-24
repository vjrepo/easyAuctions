package org.vj.auctions.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vj.auctions.response.HelloWorld;

@RestController
public class RootController {
	private static final String FORMATTED_GREETING = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/helloworld")
	public HelloWorld greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new HelloWorld(counter.incrementAndGet(), String.format(FORMATTED_GREETING, name));
	}

}
