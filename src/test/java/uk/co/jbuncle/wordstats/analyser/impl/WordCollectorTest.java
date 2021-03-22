package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uk.co.jbuncle.wordstats.analyser.WordCollectorI;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;

/**
 *
 * @author jbuncle
 */
public class WordCollectorTest {

    /**
     * Test of summarise method, of class WordCollector.
     */
    @Test
    public void testSummarise1() {
        System.out.println("summarise");

        final WordCollectorI wordCollector = new WordCollector();

        final String[] words = new String[]{
            "Hello",
            "world",
            "&",
            "good",
            "morning",
            "The",
            "date",
            "is",
            "18/05/2016"
        };

        for (final String word : words) {
            wordCollector.addWord(word);
        }

        final WordStatsI result = wordCollector.summarise();

        assertEquals(9, result.getTotalWordCount());
        assertEquals(4.556, result.getAverageWordLength());
        assertEquals(2, result.getMaxOccurences());
        assertEquals(new HashSet<>(Arrays.asList(4, 5)), result.getMaxOccurringWordLengths());

        final List<WordLengthCountI> expectedCounts = Arrays.asList(
                new WordLengthCount(1, 1),
                new WordLengthCount(2, 1),
                new WordLengthCount(3, 1),
                new WordLengthCount(4, 2),
                new WordLengthCount(5, 2),
                new WordLengthCount(7, 1),
                new WordLengthCount(10, 1)
        );
        assertEquals(expectedCounts, result.getWordLengthCounts());
    }

    /**
     * Test of summarise method, of class WordCollector.
     */
    @Test
    public void testSummarise2() {
        System.out.println("summarise");

        final WordCollectorI wordCollector = new WordCollector();

        final String text = "one two three";

        for (String word : text.split(" ")) {
            wordCollector.addWord(word);
        }

        final WordStatsI result = wordCollector.summarise();

        assertEquals(3, result.getTotalWordCount());
        assertEquals(2, result.getMaxOccurences());
        assertEquals(new HashSet<>(Arrays.asList(3)), result.getMaxOccurringWordLengths());

        assertEquals(
                Arrays.asList(
                        new WordLengthCount(3, 2),
                        new WordLengthCount(5, 1)
                ),
                result.getWordLengthCounts()
        );
        assertEquals(3.667, result.getAverageWordLength());
    }

}
