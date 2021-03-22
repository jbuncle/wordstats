package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author jbuncle
 */
public class WordStatsFomatterTest {

    /**
     * Test of humanSummary method using simple input.
     */
    @Test
    public void testHumanSummarySimple() {
        System.out.println("humanSummary");
        final WordStatsI stats = new WordStats(
                7,
                4.556,
                Arrays.asList(
                        new WordLengthCount(1, 2),
                        new WordLengthCount(2, 2),
                        new WordLengthCount(3, 2),
                        new WordLengthCount(4, 1)
                ),
                new HashSet<>(Arrays.asList(1,2,3)),
                2
        );
        final WordStatsFomatter instance = new WordStatsFomatter();
        final String expResult = "Word count = 7\n"
                + "Average word length = 4.556\n"
                + "Number of words of length 1 is 2\n"
                + "Number of words of length 2 is 2\n"
                + "Number of words of length 3 is 2\n"
                + "Number of words of length 4 is 1\n"
                + "The most frequently occurring word length is 2, for word lengths of 1, 2 & 3\n";

        final String result = instance.humanSummary(stats);

        assertEquals(expResult, result);
    }

    /**
     * Test of humanSummary method using complex data.
     */
    @Test
    public void testHumanSummary() {
        System.out.println("humanSummary");
        final WordStatsI stats = new WordStats(
                9,
                4.556,
                Arrays.asList(
                        new WordLengthCount(1, 1),
                        new WordLengthCount(2, 1),
                        new WordLengthCount(3, 1),
                        new WordLengthCount(4, 2),
                        new WordLengthCount(5, 2),
                        new WordLengthCount(7, 1),
                        new WordLengthCount(10, 1)
                ),
                new HashSet<>(Arrays.asList(4, 5)),
                2
        );
        final WordStatsFomatter instance = new WordStatsFomatter();
        final String expResult = "Word count = 9\n"
                + "Average word length = 4.556\n"
                + "Number of words of length 1 is 1\n"
                + "Number of words of length 2 is 1\n"
                + "Number of words of length 3 is 1\n"
                + "Number of words of length 4 is 2\n"
                + "Number of words of length 5 is 2\n"
                + "Number of words of length 7 is 1\n"
                + "Number of words of length 10 is 1\n"
                + "The most frequently occurring word length is 2, for word lengths of 4 & 5\n";

        final String result = instance.humanSummary(stats);

        assertEquals(expResult, result);
    }

    @ParameterizedTest
    @MethodSource("humanReadableListTestData")
    public void testHumanReadableList(final List<Integer> values, final String expResult) {
        final WordStatsFomatter instance = new WordStatsFomatter();
        final String result = instance.humanReadableList(values);

        assertEquals(expResult, result);
    }

    private static Stream<Arguments> humanReadableListTestData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4), "1, 2, 3 & 4"),
                Arguments.of(Arrays.asList(1, 2, 3), "1, 2 & 3"),
                Arguments.of(Arrays.asList(1, 2), "1 & 2"),
                Arguments.of(Arrays.asList(1), "1")
        );
    }
}
