package uk.co.jbuncle.wordstats.analyser.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jbuncle
 */
public final class WordCleaner {

    private WordCleaner() {
    }

    /**
     * Regex for trimming bad characters from the start and end of a text.
     *
     * Characters are considered "bad" if they are not alphanumric, &, $, £, \,
     * / or '.
     *
     * A "word" is a string of characters that are alphanumeric, &, £, $ or
     * slashes (/ and \).
     */
    private static final Pattern WORD_PATTERN = Pattern.compile("^[^\\w\\d&\\/'£$]*(.*?)[^\\w\\d&\\/'£$]*$");

    /**
     * Clean word.
     *
     * @param word
     *
     * @return The cleaned word.
     */
    public static String cleanWord(final String word) {
        final Matcher matcher = WORD_PATTERN.matcher(word);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }
}
