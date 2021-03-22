package uk.co.jbuncle.wordstats.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import uk.co.jbuncle.wordstats.analyser.impl.Application;

/**
 * Main SpringBoot Application class for the RestAPI.
 *
 * @author jbuncle
 */
@SpringBootApplication
public class WordStatsRestApplication {

    /**
     * Main method for RestAPI.
     *
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(WordStatsRestApplication.class, args);
    }

    /**
     * Bean for setting up Swagger and Swagger UI endpoints.
     *
     * @return The Docket.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build();
    }

    /**
     * Get the "Application" instance to use for word analysis.
     *
     * @return The Application.
     */
    @Bean
    public Application getApplication() {
        return Application.createForRestApi();
    }
}
