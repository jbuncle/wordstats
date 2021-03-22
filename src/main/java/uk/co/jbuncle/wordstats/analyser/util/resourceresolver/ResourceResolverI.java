package uk.co.jbuncle.wordstats.analyser.util.resourceresolver;

import java.io.InputStream;

/**
 *
 * @author jbuncle
 */
public interface ResourceResolverI {

    /**
     * Get an InputStream for the given target, null if resolver is unable to do so.
     * @param target
     * @return The InputStream or null.
     */
    InputStream getInputStreaam(final String target);
}
