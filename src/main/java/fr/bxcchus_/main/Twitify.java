package fr.bxcchus_.main;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import fr.bxcchus_.command.TwitifyAddPlaylist;
import fr.bxcchus_.command.TwitifyGetPlaylist;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;
public class Twitify {
    /**
     * All instantiations of objects and classes can be found here
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Twitify.class);
    public static boolean run = true;

    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
        int i = 0;
        while (true) {
            LOGGER.info("Twitify loaded..");
            new TwitifyPlaylistManager();
            // Scanner scanner = new Scanner(System.in);
            // String s = scanner.nextLine();
            TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();


            TwitifyGetPlaylist twitifyGetPlaylist = new TwitifyGetPlaylist();
            twitifyGetPlaylist.getPlaylist();
            i++;
            System.out.println("Utilisation du bot : " + i + " fois.");


            /**
             if (s.contains("@Twitify_bot send playlist")) {
             TwitifyGetPlaylist twitifyGetPlaylist = new TwitifyGetPlaylist();
             twitifyGetPlaylist.getPlaylist();

             }
             if (s.equalsIgnoreCase("@Twitify_bot add playlist")) {
             TwitifyAddPlaylist twitifyAddPlaylist = new TwitifyAddPlaylist();
             LOGGER.info(s);
             twitifyAddPlaylist.addPlaylist();
             }
             **/
        }
    }
}