package rva;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServer {
	
	@GetMapping("/zbir")
	public double zbir() {
		double a = Math.random() * 10;
		double b = Math.random() * 10;
		return a + b;
	}
}
