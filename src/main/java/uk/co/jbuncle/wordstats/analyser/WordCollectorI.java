package uk.co.jbuncle.wordstats.analyser;

/**
 * Defines class that receive words and produce a report from those words.
 *
 * @author jbuncle
 */
public interface WordCollectorI {

    /**
     * Add and analyse given word.
     *
     * @param word
     */
    void addWord(final String word);

    /**
     * Get a report object for data collected so far.
     *
     * @return The summary object.
     */
    WordStatsI summarise();
}
