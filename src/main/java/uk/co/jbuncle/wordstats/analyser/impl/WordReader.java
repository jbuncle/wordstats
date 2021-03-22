package uk.co.jbuncle.wordstats.analyser.impl;

import uk.co.jbuncle.wordstats.analyser.util.WordCleaner;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import uk.co.jbuncle.wordstats.WordStatsException;
import uk.co.jbuncle.wordstats.analyser.WordReaderI;

/**
 * Responsible for taking a text and generating a "WordStats" report object for
 * that text.
 *
 * @author jbuncle
 */
public final class WordReader implements WordReaderI {

    /**
     * Analyse the given InputStream giving words to the given WordCollector.
     *
     * @param handler The word handler.
     * @param inputStream The input stream to read words from.
     *
     * @throws WordStatsException
     */
    @Override
    public void readWords(
            final InputStream inputStream,
            final HandlerI handler
    ) throws WordStatsException {

        try (BufferedInputStream in = new BufferedInputStream(inputStream)) {
            final Scanner scanner = new Scanner(in);

            while (scanner.hasNext()) {
                final String word = scanner.next();

                // Clean word to remove unwanted characters
                final String cleanWord = WordCleaner.cleanWord(word);
                // Ignore non words
                if (cleanWord == null || cleanWord.isEmpty()) {
                    continue;
                }
                handler.handle(cleanWord);
            }
        } catch (IOException ex) {
            throw new WordStatsException(ex);
        }
    }

}
