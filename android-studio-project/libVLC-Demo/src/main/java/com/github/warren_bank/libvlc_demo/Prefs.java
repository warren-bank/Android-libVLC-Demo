package com.github.warren_bank.libvlc_demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

  private static final SharedPreferences getSharedPrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  private static final SharedPreferences.Editor getSharedPrefsEditor(Context context) {
    SharedPreferences prefs = getSharedPrefs(context);
    return prefs.edit();
  }

  public static final String getVideoUrl(Context context) {
    SharedPreferences prefs = getSharedPrefs(context);

    String key = context.getString(R.string.pref_videourl_key);
    String val = context.getString(R.string.pref_videourl_val);
    return prefs.getString(key, val);
  }

  public static final void setVideoUrl(Context context, String val) {
    SharedPreferences.Editor editor = getSharedPrefsEditor(context);

    String key = context.getString(R.string.pref_videourl_key);
    editor.putString(key, val);
    editor.commit();
  }

}
