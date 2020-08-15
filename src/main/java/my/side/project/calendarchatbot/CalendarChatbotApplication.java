package my.side.project.calendarchatbot;

import static my.side.project.calendarchatbot.utils.LogLevel.INFO;

import my.side.project.calendarchatbot.utils.Output;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CalendarChatbotApplication {

    private static final Output OUTPUT = Output.getOutput(CalendarChatbotApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(CalendarChatbotApplication.class, args);
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
        OUTPUT.print(INFO, "Received request with param: name={}", name);
        return String.format("Hello %s!", name);
    }
}
