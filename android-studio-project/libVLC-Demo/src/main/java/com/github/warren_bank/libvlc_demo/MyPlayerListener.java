package com.github.warren_bank.libvlc_demo;

import org.videolan.libvlc.MediaPlayer;

import android.util.Log;

import java.lang.ref.WeakReference;

class MyPlayerListener implements MediaPlayer.EventListener {
  private static final String TAG = "PlayerListener";

  private WeakReference<VideoActivity> mOwner;

  public MyPlayerListener(VideoActivity owner) {
    mOwner = new WeakReference<VideoActivity>(owner);
  }

  @Override
  public void onEvent(MediaPlayer.Event event) {
    VideoActivity player = mOwner.get();

    switch(event.type) {
      case MediaPlayer.Event.EndReached:
        Log.d(TAG, "MediaPlayerEndReached");
        player.releasePlayer();
        player.finish();
        break;
      case MediaPlayer.Event.Playing:
      case MediaPlayer.Event.Paused:
      case MediaPlayer.Event.Stopped:
      default:
        break;
    }
  }
}
