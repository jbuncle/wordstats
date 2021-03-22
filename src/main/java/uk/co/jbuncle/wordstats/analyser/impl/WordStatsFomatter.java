package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;

/**
 * Class for formatting WordStatsI objects into a human readable text.
 *
 * @author jbuncle
 */
public final class WordStatsFomatter {

    /**
     * Generate a human readable text string from the given WordStatsI object.
     *
     * @param stats The stats to generate from.
     *
     * @return The human readable summary.
     */
    public String humanSummary(final WordStatsI stats) {

        final StringBuilder summary = new StringBuilder();

        summary.append("Word count = ")
                .append(stats.getTotalWordCount())
                .append("\n");
        summary.append("Average word length = ")
                .append(stats.getAverageWordLength())
                .append("\n");

        // Sort by lengths
        Collections.sort(stats.getWordLengthCounts(), (WordLengthCountI t, WordLengthCountI t1) -> {
            return t.getLength() - t1.getLength();
        });

        // Loop
        stats.getWordLengthCounts().forEach((WordLengthCountI wordCount) -> {
            final int wordLength = wordCount.getLength();
            final int occurences = wordCount.getOccurences();

            summary.append("Number of words of length ")
                    .append(wordLength)
                    .append(" is ")
                    .append(occurences)
                    .append("\n");
        });

        summary.append("The most frequently occurring word length is ")
                .append(stats.getMaxOccurences())
                .append(", for word lengths of ")
                .append(this.humanReadableList(new ArrayList<>(stats.getMaxOccurringWordLengths())))
                .append("\n");
        return summary.toString();
    }

    /**
     * Join values into a human readable list (e.g. 1, 2 & 3).
     *
     * @param values The values to format.
     * @return The list represented as a human readable list.
     */
    public String humanReadableList(final List<Integer> values) {
        final StringBuilder sb = new StringBuilder();

        for (int index = 0; index < values.size(); index++) {
            final Integer value = values.get(index);

            if (index + 2 < values.size()) {
                sb.append(value).append(", ");
            } else if (index + 1 < values.size()) {
                sb.append(value).append(" & ");
            } else {
                sb.append(value);
            }
        }
        return sb.toString();
    }
}
