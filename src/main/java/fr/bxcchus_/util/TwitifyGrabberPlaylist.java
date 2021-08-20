package fr.bxcchus_.util;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.main.Twitify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TwitifyGrabberPlaylist {
    public static SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(TwitifyConfig.getTwitifyKey()).build();

    /**
     * public static void getPlaylist_Sync() throws IOException, ParseException, SpotifyWebApiException {
     *  final Playlist playlist = getPlaylistRequest.execute();
    }
     **/

    public static void getPlaylist_Async() throws ExecutionException, InterruptedException {
        List<Playlist> getPlaylistRequests = new ArrayList<>();
        HashMap<String, String> playlists = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        Twitify.LOGGER.warn("Indiquez la catégorie de la playlist : ");
        String playlistCategory = scanner.next();

        Twitify.LOGGER.warn("Entrez l'id de votre playlist : ");
        String id = scanner.next();

        playlists.put(playlistCategory, id);

        final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(id).build();
        final CompletableFuture<Playlist> playlistFuture = getPlaylistRequest.executeAsync();

        getPlaylistRequests.add(playlistFuture.get());

        for (int i = 0; i < playlists.size(); i++) {
            if(playlists.containsKey(playlistCategory)) {
                for (Playlist playlist : getPlaylistRequests) {
                    Twitify.LOGGER.info("Name : " + playlist.getName());
                    Twitify.LOGGER.info("Playlist link : " + "https://open.spotify.com/playlist/" + id + "\n");
                    Twitify.LOGGER.info("Playlist auhor : " + playlist.getOwner().getDisplayName());
                    Twitify.LOGGER.info("https://open.spotify.com/user/" + playlist.getOwner().getDisplayName());
                }
            }
        }
    }
}
