package uk.co.jbuncle.wordstats.analyser.impl;

import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import uk.co.jbuncle.wordstats.analyser.WordCollectorI;
import uk.co.jbuncle.wordstats.analyser.WordReaderI;

/**
 *
 * @author jbuncle
 */
public class WordReaderTest {

    /**
     * Test of analyse method.
     *
     * @param inputText
     * @param expectedWords
     *
     * @throws java.lang.Exception
     */
    @ParameterizedTest
    @MethodSource("analyseDataSource")
    public void testAnalyse(
            final String inputText,
            final String[] expectedWords
    ) throws Exception {
        System.out.println("analyse");

        // Setup instance and mocks
        final WordReaderI instance = new WordReader();
        final WordReaderI.HandlerI mock = Mockito.mock(WordReaderI.HandlerI.class);
        final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        // Run
        instance.readWords(IOUtils.toInputStream(inputText), mock);

        // Check result
        verify(mock, times(expectedWords.length)).handle(argument.capture());
        assertEquals(Arrays.asList(expectedWords), argument.getAllValues());
    }

    private static Stream<Arguments> analyseDataSource() {
        return Stream.of(
                Arguments.of("***** uno dos tres.  12/12/12", new String[]{"uno", "dos", "tres", "12/12/12"}),
                Arguments.of("***** test", new String[]{"test"}),
                Arguments.of("Testing $1.00", new String[]{"Testing", "$1.00"})
        );
    }
}
