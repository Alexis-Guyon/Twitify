package fr.bxcchus_.command;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import fr.bxcchus_.main.TwitifyPlaylist;
import fr.bxcchus_.main.TwitifyPlaylistManager;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.main.Twitify;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class TwitifyGetPlaylist {
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(TwitifyConfig.getTwitifyKey()).build();

    public void getPlaylist() throws IOException, ParseException, SpotifyWebApiException {
        Twitify.LOGGER.info("Quel type de playlist voulez-vous : ");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        SearchPlaylistsRequest search = spotifyApi.searchPlaylists(query).build();
        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();

        for (TwitifyPlaylist playlist : manager.getPlaylists()) {
            if(playlist.getCategory().equalsIgnoreCase(query)) {
                GetPlaylistRequest request = spotifyApi.getPlaylist(playlist.getId()).build();
                Playlist p = request.execute();
                Twitify.LOGGER.info("\n\nCatégorie : " + playlist.getCategory() +
                        "\nLien de la playlist : https://open.spotify.com/playlist/" + playlist.getId() +
                        "\nCréateur de la playlist : " + p.getOwner().getDisplayName() +
                        "\nLien du Spotify du créateur de la playlist : https://open.spotify.com/user/" + p.getOwner().getId());
            }
            else {
                if(query.equals("") || query.startsWith(" ")) return;
                searchPlayFromSpotify(query, search);
            }
        }
    }

    private void searchPlayFromSpotify(String query, SearchPlaylistsRequest search) throws CompletionException, CancellationException, IOException, ParseException, SpotifyWebApiException {
        Random random = new Random();

        final Paging<PlaylistSimplified> playlistSimplifiedPaging = search.execute();

        int c = playlistSimplifiedPaging.getItems().length;
        if(c <= 0) return;
        int nb = random.nextInt(c);

        PlaylistSimplified[] items = playlistSimplifiedPaging.getItems();
        PlaylistSimplified playlist = items[nb];


        Twitify.LOGGER.info("\n\nListe de playlist trouvé sur Spotify contenant le mot clef : \"" + query + "\"\n\n");

        Twitify.LOGGER.info("\n\n" +
                "\nNom de la playlist : " + playlist.getName() +
                "\nLien de la playlist : https://open.spotify.com/playlist/" + playlist.getId() +
                "\nCréateur de la playlist : " + playlist.getOwner().getDisplayName() +
                "\nLien du créateur de la playlist : https://open.spotify.com/user/" + playlist.getOwner().getId() + "\n");
    }

}
