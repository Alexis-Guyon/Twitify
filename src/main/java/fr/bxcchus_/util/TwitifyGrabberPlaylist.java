package fr.bxcchus_.util;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.main.Twitify;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TwitifyGrabberPlaylist {
    public static SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(TwitifyConfig.getTwitifyKey()).build();
    private static final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist("6scurl7rt2wX5VY8No4PAc").build();


    public static void getPlaylist_Sync() throws IOException, ParseException, SpotifyWebApiException {
        final Playlist playlist = getPlaylistRequest.execute();
    }

    public static void getPlaylist_Async()  {
        final CompletableFuture<Playlist> playlistFuture = getPlaylistRequest.executeAsync();
        final Playlist playlist = playlistFuture.join();

        Twitify.LOGGER.info("Name : " + playlist.getName());
        Twitify.LOGGER.info("Track list : " + "https://open.spotify.com/playlist/" + playlist.getId());
    }
}
