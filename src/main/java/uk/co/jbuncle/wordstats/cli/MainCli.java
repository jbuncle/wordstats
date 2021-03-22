package uk.co.jbuncle.wordstats.cli;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.jbuncle.wordstats.analyser.impl.Application;
import uk.co.jbuncle.wordstats.analyser.impl.WordStatsFomatter;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;

/**
 *
 * @author jbuncle
 */
public final class MainCli {

    /**
     * Number of nanoseconds in a millisecond.
     */
    private static final int NANOSEC_IN_MILLIS = 1000000;

    /**
     * Number of milliseconds in a second.
     */
    private static final int MILLIS_IN_SEC = 1000;

    private MainCli() {
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            if (args.length < 1) {
                throw new WordStatsCLIException("No targets");
            }

            final Application app = Application.createForCli();

            final WordStatsFomatter fomatter = new WordStatsFomatter();

            final long startTime = System.nanoTime();

            final WordStatsI result = app.analyseTargets(args);
            final String formatted = fomatter.humanSummary(result);

            System.out.println(formatted);

            final long executionTimeMs = (System.nanoTime() - startTime) / NANOSEC_IN_MILLIS;
            final long executionTimeSeconds = executionTimeMs / MILLIS_IN_SEC;

            System.out.println("Analysis completed in " + executionTimeMs + "ms (" + executionTimeSeconds + "s)");
        } catch (WordStatsCLIException | InterruptedException ex) {
            Logger.getLogger(MainCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
