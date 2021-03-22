package uk.co.jbuncle.wordstats.analyser.util.resourceresolver;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author jbuncle
 */
public final class URLResourceResolver implements ResourceResolverI {

    @Override
    public InputStream getInputStreaam(final String target) {
        try {
            final URL url = new URL(target);
            // Target is a URL
            return this.fromUrl(url);
        } catch (IOException ex) {
            return null;
        }
    }

    private InputStream fromUrl(
            final URL url
    ) throws IOException {
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true);
        HttpURLConnection.setFollowRedirects(true);

        return conn.getInputStream();
    }
}
