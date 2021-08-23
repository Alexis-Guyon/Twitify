package fr.bxcchus_.util;

import com.wrapper.spotify.SpotifyApi;
import fr.bxcchus_.command.TwitifyAddPlaylistCommand;
import fr.bxcchus_.command.TwitifyGetPlaylistCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    public List<Command> commands;
    private final SpotifyApi spotifyApi;

    public CommandHandler(SpotifyApi spotifyApi) {
        commands = new ArrayList<>();
        this.spotifyApi = spotifyApi;
        commands.add(new TwitifyAddPlaylistCommand());
        commands.add(new TwitifyGetPlaylistCommand());
    }
}
