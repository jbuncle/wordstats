package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Test;
import uk.co.jbuncle.wordstats.analyser.WordLengthCountI;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jbuncle
 */
public class ApplicationTest {

    /**
     * Test of analyseTargets method.Technically speaking, this is an
     * integration test as it's not only testing a single unit.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAnalyseTargetsPlainText() throws Exception {
        System.out.println("analyseTargets");
        String[] targets = {
            "Hello world & good morning. The date is 18/05/2016"
        };
        final Application instance = Application.createForCli();
        final WordStatsI result = instance.analyseTargets(targets);

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

}
