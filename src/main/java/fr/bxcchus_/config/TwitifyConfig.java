package fr.bxcchus_.config;
public class TwitifyConfig {
    /**
     * The entire Twitify configuration can be found here
     */

    static final String twitifyKey = "twitifyAccessKey";
    static final String twitifyRefresh = "twitifyRefreshKey";

    public static String getTwitifyKey() {
        return twitifyKey;
    }

    public static String getTwitifyRefresh() {
        return twitifyRefresh;
    }
}
