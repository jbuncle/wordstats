package uk.co.jbuncle.wordstats.restapi;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import uk.co.jbuncle.wordstats.analyser.impl.Application;
import uk.co.jbuncle.wordstats.analyser.impl.WordStatsFomatter;

@RestController("/rest")
public final class RestAPIController {

    /**
     * The "Application" for running text word analysis.
     */
    @Autowired
    private final Application application;

    /**
     * Constructor.
     *
     * @param application
     */
    public RestAPIController(final Application application) {
        this.application = application;
    }

    /**
     * Root endpoint for Web App.
     *
     * @param args
     * @return JSON Model.
     * @throws java.lang.InterruptedException
     */
    @ResponseBody()
    @RequestMapping(path = "/plain", method = RequestMethod.GET)
    public String indexPlain(final String[] args) throws InterruptedException {
        final WordStatsI result = this.application.analyseTargets(args);
        final WordStatsFomatter formatter = new WordStatsFomatter();

        return formatter.humanSummary(result);
    }

    /**
     * Root endpoint for Web App.
     *
     * @param args
     * @return JSON Model.
     * @throws java.lang.InterruptedException
     */
    @RequestMapping(path = "/json", method = RequestMethod.GET)
    public Map<String, Object> indexJson(final String[] args) throws InterruptedException {

        final WordStatsI result = this.application.analyseTargets(args);

        return RestFormatter.toJsonObject(result);
    }


    /**
     * Controller for when an unhandled exception occurs.
     *
     * @return The page content.
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String errorHandler() {
        return "An error occurred";
    }
}
