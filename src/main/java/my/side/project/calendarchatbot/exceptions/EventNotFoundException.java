package my.side.project.calendarchatbot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED, reason = "video not found")
public class EventNotFoundException extends RuntimeException {

}
