package uk.co.jbuncle.wordstats.restapi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class for running the Spring Boot app from a traditional WAR.
 *
 * @author jbuncle
 */
public final class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(WordStatsRestApplication.class);
    }

}
