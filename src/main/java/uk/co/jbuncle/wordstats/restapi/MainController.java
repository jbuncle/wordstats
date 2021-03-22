package uk.co.jbuncle.wordstats.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Main/Index Controller for the web app.
 *
 * @author jbuncle
 */
@Controller
public class MainController {

    /**
     * Root endpoint for Web App.
     *
     * @return Text response for client.
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String indexPlain() {
        // Just redirect to the swagger interface
        return "redirect:/swagger-ui/";
    }

}
