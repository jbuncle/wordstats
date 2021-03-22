package uk.co.jbuncle.wordstats.analyser.util;

import uk.co.jbuncle.wordstats.analyser.util.WordCleaner;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author jbuncle
 */
public class WordCleanerTest {

    /**
     * Test of cleanWord method, of class WordCleaner.
     *
     * @param input
     * @param expected
     */
    @ParameterizedTest
    @MethodSource("cleanWordTestDataSource")
    public void testCleanWord(
            final String input,
            final String expected
    ) {
        System.out.println("cleanWord");

        assertEquals(expected, WordCleaner.cleanWord(input));
    }

    private static Stream<Arguments> cleanWordTestDataSource() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("1", "1"),
                Arguments.of("ac", "ac"),
                Arguments.of("$1.00", "$1.00"),
                Arguments.of("£12.355", "£12.355"),
                Arguments.of("end.", "end"),
                Arguments.of("But,", "But"),
                Arguments.of("what", "what"),
                Arguments.of("else!", "else"),
                Arguments.of("*******", ""),
                Arguments.of("01/02/90", "01/02/90"),
                // Can't do much if separator part of word
                Arguments.of("com,ma", "com,ma")
        );
    }
}
