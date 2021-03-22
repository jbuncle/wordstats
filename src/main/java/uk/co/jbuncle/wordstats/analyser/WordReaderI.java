package uk.co.jbuncle.wordstats.analyser;

import java.io.InputStream;
import uk.co.jbuncle.wordstats.WordStatsException;

/**
 * Responsible reading words from a text.
 *
 * @author jbuncle
 */
public interface WordReaderI {

    /**
     * Read words from the InputStream.
     *
     * @param handler
     * @param stream The InputStream to read
     * @throws uk.co.jbuncle.wordstats.WordStatsException
     */
    void readWords(
            final InputStream stream,
            final HandlerI handler
    ) throws WordStatsException;

    interface HandlerI {

        void handle(final String word);
    }
}
