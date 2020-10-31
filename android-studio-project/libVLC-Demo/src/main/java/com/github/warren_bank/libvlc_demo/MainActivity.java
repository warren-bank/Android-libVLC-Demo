package com.github.warren_bank.libvlc_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
  public static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button startButton = (Button)findViewById(R.id.buttonPlay);
    startButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        EditText textVideoUrl = (EditText)findViewById(R.id.textVideoUrl);
        String videoUrl = textVideoUrl.getText().toString().trim();

        if (!videoUrl.isEmpty()) {
          intent.putExtra(VideoActivity.VIDEO_URL, videoUrl);
          startActivity(intent);
        }
      }
    });
  }
}
