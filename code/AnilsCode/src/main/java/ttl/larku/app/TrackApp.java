package ttl.larku.app;

import ttl.larku.domain.Track;

/**
 * @author whynot
 */
public class TrackApp {

    public static void main(String[] args) {
        Track tr = new Track();
        tr.setAlbum("Good Album");

        Track tr2 = new Track.Builder()
                .album("Good Album")
                .title("Sammy Goes Home")
                .build();

        Track tr3 = Track.album("Good Album")
                .title("Sammy Goes Home")
                .album("Here we go")
                .build();
    }
}
