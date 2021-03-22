package uk.co.jbuncle.wordstats.restapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;

/**
 * Class for formatting a WordStats object into a REST/JSON friendly structure.
 *
 * @author jbuncle
 */
public final class RestFormatter {

    private RestFormatter() {
    }

    /**
     * Format WordStats into a JSON friendly object.
     *
     * @param wordStats
     *
     * @return The object.
     */
    public static Map<String, Object> toJsonObject(final WordStatsI wordStats) {
        final Map<String, Object> response = new HashMap<>();
        response.put("wordCount", wordStats.getTotalWordCount());
        response.put("averageWordLength", wordStats.getAverageWordLength());

        // Sort by lengths
        Collections.sort(wordStats.getWordLengthCounts(), (WordLengthCountI t, WordLengthCountI t1) -> {
            return t.getLength() - t1.getLength();
        });

        Stream<Map<String, Integer>> lengths = wordStats.getWordLengthCounts().stream().map(wordLengthCount -> {
            Map<String, Integer> entry = new HashMap<>();
            entry.put("length", wordLengthCount.getLength());
            entry.put("occurrences", wordLengthCount.getOccurences());
            return entry;
        });
        response.put("wordLengths", lengths);
        response.put("mostFrequentlyOccurringLengths", wordStats.getMaxOccurringWordLengths());
        return response;
    }
}
