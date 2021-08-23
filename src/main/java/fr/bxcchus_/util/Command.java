package fr.bxcchus_.util;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public abstract class Command {
    public final String identifier;
    public final String syntax;
    public final String description;

    protected Command(String identifier, String syntax, String description) {
        this.identifier = identifier;
        this.syntax = syntax;
        this.description = description;
    }

    public abstract void execute() throws IOException, ParseException, SpotifyWebApiException;
}
