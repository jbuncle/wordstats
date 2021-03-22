package uk.co.jbuncle.wordstats.analyser.util.resourceresolver;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author jbuncle
 */
public final class StringResourceResolver implements ResourceResolverI {

    @Override
    public InputStream getInputStreaam(final String target) {
        return IOUtils.toInputStream(target);
    }

}
