package uk.co.jbuncle.wordstats;

/**
 * Main Application Exception class.
 *
 * @author jbuncle
 */
public class WordStatsException extends Exception {

    /**
     * Constructor with message.
     *
     * @param message
     */
    public WordStatsException(final String message) {
        super(message);
    }

    /**
     *
     * Constructor with message and cause.
     *
     * @param message
     * @param cause
     */
    public WordStatsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * Constructor with cause.
     *
     * @param cause
     */
    public WordStatsException(final Throwable cause) {
        super(cause);
    }

}
