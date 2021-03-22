package uk.co.jbuncle.wordstats.analyser.util.resourceresolver;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Combines resources resolvers by attempting to resolve with each one until
 * success.
 *
 * @author jbuncle
 */
public final class CombinedResourceResolver implements ResourceResolverI {

    /**
     * The list of resolvers.
     */
    private final List<ResourceResolverI> resolvers;

    /**
     * Constructor.
     *
     * @param resolver The resolves.
     */
    public CombinedResourceResolver(final ResourceResolverI... resolver) {
        this.resolvers = Arrays.asList(resolver);
    }

    @Override
    public InputStream getInputStreaam(final String target) {

        for (final ResourceResolverI resolver : this.resolvers) {
            final InputStream inputStream = resolver.getInputStreaam(target);
            if (inputStream != null) {
                return inputStream;
            }
        }

        return null;
    }

}
