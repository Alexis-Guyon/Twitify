package fr.bxcchus_.main;


import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import fr.bxcchus_.util.TwitifyGrabberPlaylist;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Twitify {
    /**
     * All instantiations of objects and classes can be found here
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Twitify.class);
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
        LOGGER.info("Twitify loaded..");

        TwitifyGrabberPlaylist.getPlaylist_Sync();
        TwitifyGrabberPlaylist.getPlaylist_Async();

    }
}
