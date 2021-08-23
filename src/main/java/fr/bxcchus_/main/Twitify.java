package fr.bxcchus_.main;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import fr.bxcchus_.command.TwitifyAddPlaylistCommand;
import fr.bxcchus_.command.TwitifyGetPlaylistCommand;
import fr.bxcchus_.command.TwitifyHelpCommand;
import fr.bxcchus_.config.TwitifyConfig;
import fr.bxcchus_.util.CommandHandler;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Twitify {

    /**
     * All instantiations of objects and classes can be found here
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Twitify.class);
    public static int i = 0;
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(TwitifyConfig.getTwitifyKey()).build();

    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException, TwitterException {
        new TwitifyPlaylistManager();
        getTwitterInstance();
        getTwitterInstance().getId();

        TwitifyPlaylistManager manager = TwitifyPlaylistManager.getInstance();
        TwitifyAddPlaylistCommand addPlaylist = new TwitifyAddPlaylistCommand();
        TwitifyGetPlaylistCommand getPlaylist = new TwitifyGetPlaylistCommand();
        CommandHandler c = new CommandHandler(spotifyApi);

        TwitifyHelpCommand help = new TwitifyHelpCommand(c);
        boolean run = true;
        LOGGER.info("Twitify loaded..");
        while (run) {

            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command.equalsIgnoreCase("@Twitify_bot help") || command.equalsIgnoreCase("@Twitify_bot aide")) {
                help.execute();
            } else if(command.equalsIgnoreCase("@Twitify_bot cherche")){
                getPlaylist.execute();
            } else if (command.equalsIgnoreCase("@Twitify_bot ajoute")) {
                addPlaylist.execute();
            } else {
                LOGGER.info("Essayer la commande \"@Twitify_bot help\" ou \"@Twitify_bot aide\"");
            }
            if(command.equals(spotifyApi.getAccessToken() + "stop")){
                run = false;
            }
        }
    }
    public static Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("OAuthConsumerKey")
                .setOAuthConsumerSecret("OAuthConsumerSecret")
                .setOAuthAccessToken("OAuthAccessToken")
                .setOAuthAccessTokenSecret("OAuthAccessTokenSecret");
        return new TwitterFactory(cb.build()).getInstance();
    }
}