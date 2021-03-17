package uk.co.jbuncle.wordstats;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPI {


	@RequestMapping(path = "/", method = RequestMethod.GET)
	public Map<String, Object> indexPlain() {
		return new HashMap<>();
	}

	@RequestMapping(path = "/json", method = RequestMethod.GET)
	public Map<String, Object> indexJson() {
		return new HashMap<>();
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public String conflict() {
		return "An error occurred";
	}
}
