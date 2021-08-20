package fr.bxcchus_.command;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import fr.bxcchus_.main.TwitifyPlaylist;
import fr.bxcchus_.main.TwitifyPlaylistManager;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.main.Twitify;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;


public class TwitifyGetPlaylist {
    public static SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(TwitifyConfig.getTwitifyKey()).build();

    public void getPlaylist() throws IOException, ParseException, SpotifyWebApiException {
        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();
        for(TwitifyPlaylist p : manager.getPlaylists()) {
            if(p.getCategory().equalsIgnoreCase("Sleep")) {
                GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(p.getId()).build();
                Playlist playlist = getPlaylistRequest.execute();
                Twitify.LOGGER.info("\n\nCatégorie : " + p.getCategory() + "\nLien de la playlist : https://open.spotify.com/playlist/" + p.getId() + "\nCréateur de la playlist : " + playlist.getOwner().getDisplayName() + "\nLien du Spotify du créateur de la playlist : https://open.spotify.com/user/" + playlist.getOwner().getId());
            }
        }
    }
}