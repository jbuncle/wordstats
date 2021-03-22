package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;

/**
 * Basic data object for a WordLengthCount.
 *
 * @author jbuncle
 */
final class WordLengthCount implements WordLengthCountI {

    /**
     * The length of the word.
     */
    private final int length;

    /**
     * The number of occurrences of this word length. Atomic to allow thread
     * safe incrementing.
     */
    private final AtomicInteger occurrences;

    public WordLengthCount(final int length) {
        this(length, 1);
    }

    public WordLengthCount(final int length, final int occurences) {
        this.length = length;
        this.occurrences = new AtomicInteger(occurences);
    }

    public void increment() {
        this.occurrences.incrementAndGet();
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getOccurences() {
        return this.occurrences.intValue();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WordLengthCountI)) {
            return false;
        }

        final WordLengthCountI other = (WordLengthCountI) obj;
        return this.getLength() == other.getLength() && this.getOccurences() == other.getOccurences();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.length, this.occurrences.intValue());
    }

}
