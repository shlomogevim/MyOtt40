package com.example.myott40

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

class Uties(val context: Context) {

    val activity = context as Activity

    fun setDimension() { // Adjust the size of the video
// so it fits on the screen
        val videoProportion = getVideoProportion()
        val screenWidth = context.resources.displayMetrics.widthPixels
        val screenHeight = context.resources.displayMetrics.heightPixels
        val screenProportion =
            screenHeight.toFloat() / screenWidth.toFloat()
        val lp = activity.videoView.layoutParams
        if (videoProportion < screenProportion) {
            lp.height = screenHeight
            lp.width = (screenHeight.toFloat() / videoProportion).toInt()
            lp.height = lp.height * 3
            lp.width = lp.width * 3
        } else {
            lp.width = screenWidth
            lp.height = (screenWidth.toFloat() * videoProportion).toInt()
        }
        activity.videoView.layoutParams = lp
    }

    // This method gets the proportion of the video that you want to display.
// I already know this ratio since my video is hardcoded, you can get the
// height and width of your video and appropriately generate  the proportion
//    as :height/width
    private fun getVideoProportion(): Float {
        return 1.5f
    }

    fun videoBackgroundPreperation() {
         var videoBG: VideoView? = null
        var mMediaPlayer: MediaPlayer? = null
        var mCurrentVideoPosition = 0

        videoBG = activity.videoView as VideoView
        val uri = Uri.parse(
            ("android.resource://" // First start with this,
                    + context.packageName // then retrieve your package name,
                    ) + "/" // add a slash,
                    //+ R.raw.myott30
                    + R.raw.wave
        ) // and then finally add your video resource. Make sure it is stored
        // in the raw folder.
// Set the new Uri to our VideoView
        videoBG!!.setVideoURI(uri)
        // Start the VideoView
        videoBG!!.start()
        // Set an OnPreparedListener for our VideoView. For more information about VideoViews,
// check out the Android Docs: https://developer.android.com/reference/android/widget/VideoView.html
        videoBG!!.setOnPreparedListener { mediaPlayer ->
            mMediaPlayer = mediaPlayer
            // We want our video to play over and over so we set looping to true.
            mMediaPlayer!!.isLooping = true
            // We then seek to the current posistion if it has been set and play the video.
            if (mCurrentVideoPosition != 0) {
                mMediaPlayer!!.seekTo(mCurrentVideoPosition)
                mMediaPlayer!!.start()
            }
        }
    }
}