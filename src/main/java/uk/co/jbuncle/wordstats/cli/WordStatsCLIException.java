package uk.co.jbuncle.wordstats.cli;

import uk.co.jbuncle.wordstats.WordStatsException;

/**
 * Represents an Exception triggered by the CLI.
 *
 * @author jbuncle
 */
public class WordStatsCLIException extends WordStatsException {

    /**
     * Constructor with message.
     *
     * @param message
     */
    public WordStatsCLIException(final String message) {
        super(message);
    }

    /**
     *
     * Constructor with message and cause.
     *
     * @param message
     * @param cause
     */
    public WordStatsCLIException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * Constructor with cause.
     *
     * @param cause
     */
    public WordStatsCLIException(final Throwable cause) {
        super(cause);
    }

}
