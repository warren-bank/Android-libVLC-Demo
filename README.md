### [libVLC Demo](https://github.com/warren-bank/Android-libVLC-Demo)

Android app to demo the _libVLC_ library.

#### Goals

* main motivation is to test whether [_libVLC_](https://code.videolan.org/videolan/vlc-android) can play the RTSP stream produced by [_libstreaming_](https://github.com/fyhertz/libstreaming)
  - plays well on VLC desktop
  - doesn't play in any other RTSP client (that I've tested)

#### Notes

* _libVLC_ [issue &num;275](https://code.videolan.org/videolan/vlc-android/-/issues/275)
  - _libVLC_ `v3.x` no longer works with _libstreaming_
  - _libVLC_ `v2.x` did
* [RtspServerAndVlcPlay](https://github.com/wobiancao/RtspServerAndVlcPlay)
  - bundles both _libstreaming_ (server) with _libVLC_ (client)
  - uses unofficial [_libVLC_ `v2.x`](https://github.com/mrmaffen/vlc-android-sdk)
* distribution on JCenter:
  - [official builds](https://mvnrepository.com/artifact/org.videolan.android/libvlc-all?repo=jcenter) are available starting at `v3.1.0`
  - an [unofficial build](https://mvnrepository.com/artifact/de.mrmaffen/libvlc-android) is available for `v2.1.12`

#### minSdkVersion

* _libVLC_ `v2.1.12`
  - [libvlc/AndroidManifest.xml](https://code.videolan.org/videolan/vlc-android/-/blob/2.1.12/libvlc/AndroidManifest.xml)
  - [libvlc/build.gradle](https://code.videolan.org/videolan/vlc-android/-/blob/2.1.12/libvlc/build.gradle)
  - [build.gradle](https://code.videolan.org/videolan/vlc-android/-/blob/2.1.12/build.gradle)
    * minSdkVersion=9
      - Android 2.3 (Gingerbread, API level 9)
* _libVLC_ `v3.3.1`
  - [libvlc/AndroidManifest.xml](https://code.videolan.org/videolan/vlc-android/-/blob/3.3.1/libvlc/AndroidManifest.xml)
  - [libvlc/build.gradle](https://code.videolan.org/videolan/vlc-android/-/blob/3.3.1/libvlc/build.gradle)
  - [build.gradle](https://code.videolan.org/videolan/vlc-android/-/blob/3.3.1/build.gradle)
    * minSdkVersion=17
      - Android 4.2 (Jelly Bean, API level 17)

#### Results

* _libVLC_ `v2.1.12` is confirmed to work with [_libstreaming_](https://github.com/fyhertz/libstreaming)
  - successfully tested with:
    * [_libstreaming_ `example1`](https://github.com/fyhertz/libstreaming-examples/tree/master/example1) unofficial build [`v01.00.00`](https://github.com/warren-bank/Android-libraries/releases/tag/fyhertz%2Flibstreaming-examples%2Fv01.00.00)
      - _libstreaming_ serves RTSP stream from camera and microphone
    * [RTSP ScreenCaster](https://github.com/warren-bank/Android-RTSP-ScreenCaster) release [`v01.01.03`](https://github.com/warren-bank/Android-RTSP-ScreenCaster/releases/tag/v01.01.03)
      - _libscreening_ serves RTSP stream from screen and microphone
* _libVLC_ `v3.3.1` is confirmed to work with [_libstreaming_](https://github.com/fyhertz/libstreaming), but with caveats
  - the official [`v3.3.1` release](https://get.videolan.org/vlc-android/3.3.1/) of [_VLC for Android_](https://www.videolan.org/vlc/download-android.html) acceptably handles orientation changes
  - my demo app doesn't fare so well
    * RTSP stream plays in portrait orientation
    * freezes in landscape orientation
    * resumes again when rotated back to portrait orientation
    * this could probably be fixed by using a different event handling methodology
  - general observations/conclusions:
    * this library seems a lot more tempermental
    * the minSdkVersion is considerably higher
    * the APK file size is nearly double
    * the end result is that the video doesn't play any better than in _libVLC_ `v2.1.12`

#### Legal:

* copyright: [Warren Bank](https://github.com/warren-bank)
* license: [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt)
