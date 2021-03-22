package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.List;
import java.util.Set;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;

/**
 * Largely a basic data object for storing analysis results.
 *
 * @author jbuncle
 */
final class WordStats implements WordStatsI {

    /**
     * The number of words counted.
     */
    private final int wordCount;

    /**
     * The average word length.
     */
    private final double averageWordLength;

    /**
     * All word lengths and number of occurrences.
     */
    private final List<WordLengthCountI> wordLengths;

    /**
     * Most frequent word lengths.
     */
    private final Set<Integer> mostFrequentLengths;
    /**
     * Most frequent word length occurrences.
     */
    private final int mostFrequentLengthOccurences;

    public WordStats(
            final int wordCount,
            final double averageWordLength,
            final List<WordLengthCountI> wordLengths,
            final Set<Integer> mostFrequentLengths,
            final int mostFrequentLengthOccurences
    ) {
        this.wordCount = wordCount;
        this.averageWordLength = averageWordLength;
        // Ensure this object is immutable
        this.wordLengths = wordLengths;
        this.mostFrequentLengths = mostFrequentLengths;
        this.mostFrequentLengthOccurences = mostFrequentLengthOccurences;
    }

    @Override
    public int getTotalWordCount() {
        return this.wordCount;
    }

    @Override
    public double getAverageWordLength() {
        return this.averageWordLength;
    }

    @Override
    public List<WordLengthCountI> getWordLengthCounts() {
        return this.wordLengths;
    }

    @Override
    public Set<Integer> getMaxOccurringWordLengths() {
        return this.mostFrequentLengths;
    }

    @Override
    public int getMaxOccurences() {
        return this.mostFrequentLengthOccurences;
    }

}
