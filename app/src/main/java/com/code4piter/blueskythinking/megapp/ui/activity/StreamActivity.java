package com.code4piter.blueskythinking.megapp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.code4piter.blueskythinking.megapp.R;
import com.longtailvideo.jwplayer.JWPlayerFragment;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

/**
 * Created by pavel on 12.05.2017.
 */

public class StreamActivity extends Activity {
//    private static final String mPlaceUri = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
//    private static final String mPlaceUri = "http://213.163.74.10/live/eurosport/ratus42.m3u8";
//    private static final String mPlaceUri = "http://85.238.112.40:8810/hls_sec/239.33.16.32-.m3u8";
//    private static final String mPlaceUri = "http://46.46.143.222:1935/live/mp4:ldpr.stream_720p/playlist.m3u8";
    public static final String EXTRA_PLACE_URI = "EXTRA_PLACE_URI";
    private String mPlaceUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlaceUri = getIntent().getStringExtra(StreamActivity.EXTRA_PLACE_URI);
        setContentView(R.layout.stream_activity);
        JWPlayerFragment fragment = (JWPlayerFragment) getFragmentManager().findFragmentById(R.id.playerFragment);
        JWPlayerView playerView = fragment.getPlayer();
        PlaylistItem video = new PlaylistItem(mPlaceUri);
        playerView.load(video);
    }

}
