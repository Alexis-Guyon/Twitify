package fr.bxcchus_.command;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import fr.bxcchus_.main.TwitifyPlaylistManager;
import fr.bxcchus_.main.TwitifyPlaylist;
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
        Twitify.LOGGER.info("Indiquez la catégorie de la playlist : ");
        playlistCategory = scanner.nextLine();

        Twitify.LOGGER.info("Entrez l'id de votre playlist : ");
        id = scanner.nextLine();
        Twitify.LOGGER.info("Votre playlist a été ajouté.\n     Merci de votre contribution.");
        Twitify.LOGGER.info("Lien de la playlist : https://open.spotify.com/playlist/" + id);
        TwitifyPlaylist playlist = new TwitifyPlaylist(playlistCategory, owner, id);
        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();
        manager.getPlaylists().add(playlist);
        manager.savePlaylists();



    }
}
