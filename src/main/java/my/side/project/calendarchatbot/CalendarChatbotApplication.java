package my.side.project.calendarchatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CalendarChatbotApplication {




	public static void main(String[] args) {
		SpringApplication.run(CalendarChatbotApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "guest") String name) {
		Output.print(LogLevel.INFO, name);
		return String.format("Hello %s!", name);
	}


}
