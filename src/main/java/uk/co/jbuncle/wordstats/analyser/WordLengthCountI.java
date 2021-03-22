package uk.co.jbuncle.wordstats.analyser;

/**
 * Interface for objects representing a count of word length occurences.
 *
 * @author jbuncle
 */
public interface WordLengthCountI {

    /**
     * Get the word length.
     *
     * @return The word length.
     */
    int getLength();

    /**
     * Get the number of occurrences of the word length.
     *
     * @return The number of occurrences.
     */
    int getOccurences();
}
