package fr.bxcchus_.command;

import fr.bxcchus_.main.TwitifyPlaylistManager;
import fr.bxcchus_.main.TwitifyPlaylist;
import fr.bxcchus_.main.Twitify;
import fr.bxcchus_.util.Command;
import java.util.Scanner;

public class TwitifyAddPlaylistCommand extends Command {

    public static String id;
    public static String playlistCategory;
    public static String owner;

    public TwitifyAddPlaylistCommand() {
        super("TwitifyAddPlaylistCommand", "@Twitify_bot ajoute", "Ajoute votre playlist au bot");
    }

    /**
     * Add your playlist to the bot
     * Syntax : @Twitify_bot add {Category} {Playlist ID}
     */
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        Twitify.LOGGER.info("Indiquez la catégorie de la playlist : ");
        playlistCategory = scanner.nextLine();

        Twitify.LOGGER.info("Entrez l'ID de votre playlist  ");
        Twitify.LOGGER.info("Exemple https://open.spotify.com/playlist/ID/ :\n");

        id = scanner.nextLine();
        Twitify.LOGGER.info("""

                Votre playlist a été ajouté.
                Merci de votre contribution.""");
        Twitify.LOGGER.info("Lien de la playlist : https://open.spotify.com/playlist/" + id);

        TwitifyPlaylist playlist = new TwitifyPlaylist(playlistCategory, owner, id);
        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();
        manager.getPlaylists().add(playlist);
        manager.savePlaylists();
    }
}
