package fr.bxcchus_.command;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import fr.bxcchus_.main.TwitifyPlaylistManager;
import fr.bxcchus_.main.TwitifyPlaylist;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.main.Twitify;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class TwitifyAddPlaylist {

    public static String id;
    public static String playlistCategory;
    public static String owner;


    public void addPlaylist() throws IOException, ParseException, SpotifyWebApiException {
        /**
         * Add your playlist to the bot
         * Syntax : @Twitify_bot add {Category} {Playlist ID}
         */

        Scanner scanner = new Scanner(System.in);
        Twitify.LOGGER.warn("Indiquez la catégorie de la playlist : ");
        playlistCategory = scanner.next();

        Twitify.LOGGER.warn("Entrez l'id de votre playlist : ");
        id = scanner.next();

        TwitifyPlaylist playlist = new TwitifyPlaylist(playlistCategory, owner, id);
        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();
        manager.getPlaylists().add(playlist);
        manager.savePlaylists();

        TwitifyGetPlaylist t = new TwitifyGetPlaylist();
        t.getPlaylist();
    }
}
