package fr.bxcchus_.main;


import com.wrapper.spotify.model_objects.specification.Playlist;

public class TwitifyPlaylist {

    private final String category;
    private final String owner;
    private final String id;

    public TwitifyPlaylist(String category, String owner, String id) {
        this.category = category;
        this.owner = owner;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getOwner() {
        Playlist p = new Playlist.Builder().build();
        return p.getOwner().getDisplayName();
    }

    public String getId() {
        return id;
    }
}
