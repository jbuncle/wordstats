package uk.co.jbuncle.wordstats.analyser;

import java.util.List;
import java.util.Set;

/**
 * Interface for objects defining word based statistics.
 *
 * @author jbuncle
 */
public interface WordStatsI {

    /**
     * Get the average word length.
     *
     * @return The average word length.
     */
    double getAverageWordLength();

    /**
     * Get a list of the the most occurring word lengths (e.g. the lengths with
     * the most occurrences in the analysed text)
     *
     * @return The most occurring lengths.
     */
    Set<Integer> getMaxOccurringWordLengths();

    /**
     * Get the most counted occurrences of a word length.
     *
     * @return The most occurrences of a word length.
     */
    int getMaxOccurences();

    /**
     * Get the total words counted.
     *
     * @return The total word count.
     */
    int getTotalWordCount();

    /**
     * Get the word length counts (i.e. the different word length size and the
     * number of occurrences).
     *
     * @return The list of word length counts.
     */
    List<WordLengthCountI> getWordLengthCounts();

}
