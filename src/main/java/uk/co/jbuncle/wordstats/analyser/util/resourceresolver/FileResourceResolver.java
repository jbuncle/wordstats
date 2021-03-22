package uk.co.jbuncle.wordstats.analyser.util.resourceresolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author jbuncle
 */
public final class FileResourceResolver implements ResourceResolverI {

    @Override
    public InputStream getInputStreaam(final String target) {
        try {
            final File file = new File(target);
            return new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

}
