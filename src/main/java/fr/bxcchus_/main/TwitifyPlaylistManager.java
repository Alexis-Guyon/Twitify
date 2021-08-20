package fr.bxcchus_.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.bxcchus_.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TwitifyPlaylistManager {

    private static TwitifyPlaylistManager instance;
    private final Gson gson = new Gson();

    private List<TwitifyPlaylist> playlists = new ArrayList<>();
    private final File file = new File("./playlist.txt");

    public TwitifyPlaylistManager() {
        instance = this;
        FileUtils.createFile(file);
        this.loadPlaylists();
    }

    public void loadPlaylists() {
        playlists = gson.fromJson(FileUtils.readFile(file), new TypeToken<List<TwitifyPlaylist>>(){}.getType());
        if(playlists == null) playlists = new ArrayList<>();
    }

    public void savePlaylists() {
        FileUtils.saveFile(file, gson.toJson(playlists));
    }

    public List<TwitifyPlaylist> getPlaylists() {
        return playlists;
    }

    public static TwitifyPlaylistManager getInstance() {
        return instance;
    }
}
