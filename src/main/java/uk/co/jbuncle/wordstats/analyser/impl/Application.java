package uk.co.jbuncle.wordstats.analyser.impl;

import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import uk.co.jbuncle.wordstats.WordStatsException;
import uk.co.jbuncle.wordstats.analyser.WordCollectorI;
import uk.co.jbuncle.wordstats.analyser.WordReaderI;
import uk.co.jbuncle.wordstats.analyser.WordStatsI;
import uk.co.jbuncle.wordstats.analyser.util.resourceresolver.CombinedResourceResolver;
import uk.co.jbuncle.wordstats.analyser.util.resourceresolver.FileResourceResolver;
import uk.co.jbuncle.wordstats.analyser.util.resourceresolver.ResourceResolverI;
import uk.co.jbuncle.wordstats.analyser.util.resourceresolver.StringResourceResolver;
import uk.co.jbuncle.wordstats.analyser.util.resourceresolver.URLResourceResolver;

/**
 *
 * @author jbuncle
 */
public final class Application {

    /**
     * Timeout minutes for thread pooling.
     */
    private static final int THREADPOOL_TIMEOUT_MIN = 10;

    /**
     * Class for turning a target string to an input stream.
     */
    private final ResourceResolverI resourceResolver;

    /**
     * The instance for "collecting" words.
     */
    private final WordCollectorI wordCollector;

    /**
     * The instance for analysing the collected words.
     */
    private final WordReaderI wordReader;

    /**
     * Constructor.
     *
     * @param resourceResolver
     * @param wordCollector
     * @param wordReader
     */
    private Application(
            final ResourceResolverI resourceResolver,
            final WordCollectorI wordCollector,
            final WordReaderI wordReader
    ) {
        this.resourceResolver = resourceResolver;
        this.wordCollector = wordCollector;
        this.wordReader = wordReader;
    }

    /**
     * Create CLI Application instance.
     *
     * @return The CLI Application.
     */
    public static Application createForCli() {
        final ResourceResolverI resourceResolver = new CombinedResourceResolver(
                new URLResourceResolver(),
                new FileResourceResolver(),
                new StringResourceResolver()
        );
        final WordCollectorI wordStatsCollector = new WordCollector();
        final WordReaderI wordReader = new WordReader();

        return new Application(resourceResolver, wordStatsCollector, wordReader);
    }

    /**
     * Create Rest API Application instance.
     *
     * @return The Rest API Application.
     */
    public static Application createForRestApi() {
        final ResourceResolverI resourceResolver = new URLResourceResolver();
        final WordCollectorI wordStatsCollector = new WordCollector();
        final WordReaderI wordReader = new WordReader();

        return new Application(resourceResolver, wordStatsCollector, wordReader);
    }

    /**
     * Analyse given target strings.
     *
     * Actually executes each in a thread pool.
     *
     * @param targets
     *
     * @return The result of processing all given targets.
     *
     * @throws InterruptedException
     */
    public WordStatsI analyseTargets(
            final String[] targets
    ) throws InterruptedException {

        final int threadPoolSize = 4;
        final ThreadPoolExecutor executor
                = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
        // Allow multiple
        for (final String target : targets) {
            executor.submit(() -> {
                analyseTarget(target);
                return true;
            });
        }
        executor.shutdown();
        executor.awaitTermination(THREADPOOL_TIMEOUT_MIN, TimeUnit.MINUTES);

        return this.wordCollector.summarise();
    }

    private void analyseTarget(
            final String target
    ) throws WordStatsException {
        final InputStream inputStream = this.resourceResolver.getInputStreaam(target);
        this.wordReader.readWords(inputStream, (word) -> {
            this.wordCollector.addWord(word);
        });

    }
}
