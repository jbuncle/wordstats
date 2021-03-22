package uk.co.jbuncle.wordstats.analyser.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;
import uk.co.jbuncle.wordstats.analyser.WordCollectorI;

/**
 * Thread safe object for receiving words and producing a report on them.
 *
 */
public final class WordCollector implements WordCollectorI {

    /**
     * Map of the WordLengthCounts, keyed by length for faster updating.
     */
    private final Map<Integer, WordLengthCount> counts = new ConcurrentHashMap<>();

    /**
     * The sum of all word lengths.
     */
    private final AtomicInteger wordLengthsSum = new AtomicInteger(0);

    /**
     * The total number of words counted.
     */
    private final AtomicInteger totalWordCount = new AtomicInteger(0);

    /**
     * The most frequently occurring word lengths.
     */
    private final AtomicInteger mostFrequentWordLengthOccurences = new AtomicInteger(0);

    /**
     * The most frequently occurred count of word lengths.
     */
    private final Set<Integer> lengthsWithMostOccurences = new TreeSet<>();

    /**
     * Constructor.
     */
    public WordCollector() {
    }

    /**
     * Add/"collect" a word.
     *
     * @param word
     */
    @Override
    public void addWord(final String word) {
        final int wordLength = word.length();

        synchronized (this) {
            this.addLength(wordLength);
        }
    }

    /**
     * Get a snapshot report object for data collected so far at this point in
     * time.
     *
     * @return The summary object.
     */
    @Override
    public WordStatsI summarise() {

        synchronized (this) {
            return new WordStats(
                    this.getTotalWordCount(),
                    this.getAverageWordLength(),
                    this.getWordLengthCounts(),
                    this.getMaxOccurringWordLengths(),
                    this.getMaxOccurences()
            );
        }
    }

    private Set<Integer> getMaxOccurringWordLengths() {
        return this.lengthsWithMostOccurences;
    }

    private int getMaxOccurences() {
        return this.mostFrequentWordLengthOccurences.intValue();
    }

    private int getTotalWordCount() {
        return this.totalWordCount.intValue();
    }

    private List<WordLengthCountI> getWordLengthCounts() {
        return new ArrayList<>(this.counts.values());
    }

    private double getAverageWordLength() {
        final MathContext mc = new MathContext(4, RoundingMode.CEILING);

        final BigDecimal totalWordsLengthBd = new BigDecimal(this.wordLengthsSum.intValue());
        final BigDecimal totalWordCountBd = new BigDecimal(this.totalWordCount.intValue());

        return totalWordsLengthBd.divide(totalWordCountBd, mc).doubleValue();
    }

    private void addLength(final int wordLength) {
        final WordLengthCountI wordLengthCount = incrementLengthCount(wordLength);

        this.totalWordCount.incrementAndGet();
        this.wordLengthsSum.addAndGet(wordLength);

        final int currentOccurences = wordLengthCount.getOccurences();
        if (currentOccurences > this.mostFrequentWordLengthOccurences.intValue()) {
            this.mostFrequentWordLengthOccurences.set(currentOccurences);
            // We have a new winner, clear out the last set
            this.lengthsWithMostOccurences.clear();
            this.lengthsWithMostOccurences.add(wordLength);
        } else if (currentOccurences == this.mostFrequentWordLengthOccurences.intValue()) {
            // Another winner
            this.lengthsWithMostOccurences.add(wordLength);
        }

    }

    private WordLengthCountI incrementLengthCount(final int wordLength) {
        // Check if we already have a count for the given word length
        if (!this.counts.containsKey(wordLength)) {
            // Add first entry for word length
            this.counts.put(wordLength, new WordLengthCount(wordLength));
        } else {
            this.counts.get(wordLength).increment();
        }
        return this.counts.get(wordLength);
    }
}
