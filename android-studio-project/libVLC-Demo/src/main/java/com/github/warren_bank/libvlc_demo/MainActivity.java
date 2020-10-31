package com.github.warren_bank.libvlc_demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final EditText textVideoUrl = (EditText) findViewById(R.id.textVideoUrl);
    final Button   startButton  = (Button)   findViewById(R.id.buttonPlay);

    textVideoUrl.setText(
      Prefs.getVideoUrl(this),
      TextView.BufferType.EDITABLE
    );

    startButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent   = new Intent(MainActivity.this, VideoActivity.class);
        String videoUrl = textVideoUrl.getText().toString().trim();

        if (!videoUrl.isEmpty()) {
          try {
            Uri videoUri = Uri.parse(videoUrl);
            Prefs.setVideoUrl(MainActivity.this, videoUrl);

            intent.setData(videoUri);
            startActivity(intent);
          }
          catch(Exception e) {}
        }
      }
    });
  }
}
