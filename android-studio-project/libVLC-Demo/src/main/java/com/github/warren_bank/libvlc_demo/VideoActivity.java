package com.github.warren_bank.libvlc_demo;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class VideoActivity extends Activity {
  public static final String TAG       = "VideoActivity";
  public static final String VIDEO_URL = "VIDEO_URL";

  private static final int VideoSizeChanged = -1;

  private String      videoUrl;
  private LibVLC      libVLC;
  private MediaPlayer mMediaPlayer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onPreCreate();

    // Get URL
    Intent intent = getIntent();
    videoUrl = intent.getExtras().getString(VIDEO_URL);

    if (videoUrl.isEmpty()) {
      finish();
      return;
    }
    Log.d(TAG, "Playing back " + videoUrl);

    setContentView(R.layout.activity_video);
    SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);

    SurfaceHolder surfaceHolder = surfaceView.getHolder();
    surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    ArrayList<String> options = new ArrayList<String>();

    // Create media player
    libVLC          = new LibVLC(getApplicationContext(), options);
    mMediaPlayer    = new MediaPlayer(libVLC);
    Media media     = new Media(libVLC, Uri.parse(videoUrl));

    // Set up video output
    mMediaPlayer.getVLCVout().setVideoSurface(surfaceHolder.getSurface(), surfaceHolder);
    mMediaPlayer.getVLCVout().attachViews();
    mMediaPlayer.setEventListener(new MyPlayerListener(this));
    mMediaPlayer.setMedia(media);
    mMediaPlayer.play();
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mMediaPlayer != null) {
      mMediaPlayer.pause();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mMediaPlayer != null) {
      mMediaPlayer.play();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    releasePlayer();
  }

  private void onPreCreate() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    Window window = getWindow();
    if (window != null) {
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      window.getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
          | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
          | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
          | View.SYSTEM_UI_FLAG_IMMERSIVE
      );
      window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
  }

  protected void releasePlayer() {
    if (mMediaPlayer != null) {
      try {
        mMediaPlayer.stop();
        mMediaPlayer.getVLCVout().detachViews();
      }
      catch(Exception e) {}
      mMediaPlayer = null;
    }

    if (libVLC != null) {
      try {
        libVLC.release();
      }
      catch(Exception e) {}
      libVLC = null;
    }
  }
}
