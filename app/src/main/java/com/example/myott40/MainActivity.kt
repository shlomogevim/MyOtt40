package com.example.myott40

import android.animation.AnimatorInflater
import android.content.res.Resources
import android.graphics.drawable.AnimatedVectorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    var counter = 0
    private var animationMode = true
    lateinit var otts: ArrayList<Ott>
    private var videoBG: VideoView? = null
    var mMediaPlayer: MediaPlayer? = null
    var mCurrentVideoPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setDimension()

        videoBackgroundPreperation()
        // mainLayout.setBackgroundResource(R.drawable.paper)

        /*  button.setOnClickListener {
              if (imageView.visibility==View.VISIBLE) imageView.visibility=View.GONE
                      else imageView.visibility=View.VISIBLE
          }*/

        mainLayout.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                createOtt()
                drawAllOtts()
                lastApizode()
            }
        }
    }

    private suspend fun lastApizode() {
        delay(5000)
        for (index in 0 until otts.size) {

            val anim = AnimatorInflater.loadAnimator(this, R.animator.set3)
            anim?.apply {
                setTarget(otts[index].iv)
                start()
            }
        }
    }

    private suspend fun drawAllOtts() {
        withContext(Dispatchers.Main) {
            for (i in 0 until otts.size) {
                drawOneOtt(otts[i])
            }
        }
    }

    private suspend fun drawOneOtt(ott: Ott) {
        val image = ott.iv

        if (animationMode) {
            if (counter > 0) delay(250)
            counter++
            mainLayout.addView(image)
            setParameters(ott)
            val avd = image.drawable as AnimatedVectorDrawable
            avd.start()
        } else {
            mainLayout.addView(image)
            setParameters(ott)
        }
        if (ott.index == 29) {
            val anim = AnimatorInflater.loadAnimator(this, R.animator.rotate)
            anim?.apply {
                setTarget(otts[counter].iv)
                start()
            }

        }
    }

    private fun setParameters(ott: Ott) {
        with(ott) {
            if (width > 0) {
                iv.layoutParams.height = height.toPx()
                iv.layoutParams.width = width.toPx()
            }
            val imageView = ott.iv
            imageView.id = View.generateViewId()
            val set = ConstraintSet()
            set.clone(mainLayout)

            /* set.connect(
                 imageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID,
                 ConstraintSet.TOP, ott.mT.toPx()
             )*/
            /* set.connect(
                 imageView.id, ConstraintSet.START, ConstraintSet.PARENT_ID,
                 ConstraintSet.START, ott.mL.toPx()
             )*/

            set.connect(
                    imageView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM, ott.mBottom.toPx()
            )

            set.connect(
                    imageView.id, ConstraintSet.END, ConstraintSet.PARENT_ID,
                    ConstraintSet.END, ott.mRight.toPx()
            )

            set.applyTo(mainLayout)
        }
    }

    private fun createOtt() {
        otts = ArrayList()
        val int00 = 30
        val int0 = -20
        val int1 = -40
        val int2 = 0
        val int3 = 165
        val int4 = 30
        val int44 = 30
        val scale0 = 120
        val scale1 = 60
        val scale2 = 60
        val scale4 = 60
        val bottom0 = 670
        val buttom1 = 570
        val buttom2 = 565
        val buttom3 = 565
        val buttom4 = 500
        /* width,height,mLeft,mTop,mRight,mBelow*/
        otts = arrayListOf(

                Ott(mV("ה"), 0, scale0, scale0, 80 + int00, bottom0),
                Ott(mV("ק"), 1, scale0, scale0, 150 + int00, bottom0 - 20),
                Ott(mV("ס"), 2, scale0 - 20, scale0 - 20, 215 + int00, bottom0 + 2),
                Ott(mV("ם"), 3, scale0 - 15, scale0 - 15, 283 + int00, bottom0 - 1),

                Ott(mV("ג"), 4, scale1, scale1, 100 + int0, buttom1),
                Ott(mV("ל"), 5, scale1, scale1, 128 + int0, buttom1),
                Ott(mV("ו"), 6, scale1, scale1, 150 + int0, buttom1 - 5),
                Ott(mV("י"), 7, scale1, scale1, 160 + int0, buttom1 - 5),

                Ott(mV("ר"), 8, scale1+10, scale1+10, 220 + int1, buttom2-10),
                Ott(mV("ק"), 9, scale1, scale1, 255 + int1, buttom2-15),

                Ott(mV("ל"), 10,  scale2,scale2, 100+int3,  buttom3),
                Ott(mV("ר"), 11,  scale2+10,scale2+10, 130+int3,  buttom3-12),
                Ott(mV("ו"), 12,  scale2,scale2, 157+int3,  buttom3-2),
                Ott(mV("א"), 13,  scale2,scale2, 182+int3,  buttom3-2),
                Ott(mV("י"), 14,  scale2,scale2, 200+int3,  buttom3),
                Ott(mV("ם"), 15,  scale2,scale2, 225+int3,  buttom3-3),

                Ott(mV("א"), 16,  scale4,scale4, 87+int4,  buttom4),
                Ott(mV("ת"), 17,  scale4,scale4, 120+int4,  buttom4-10),

                Ott(mV("ה"), 18,  scale4,scale4, 170+int44,  buttom4),
                Ott(mV("ק"), 19,  scale4+10,scale4+10, 205+int44,  buttom4-10),
                Ott(mV("ס"), 20,  scale4,scale4, 243+int44,  buttom4),
                Ott(mV("מ"), 21,  scale4,scale4, 276+int44,  buttom4),
                Ott(mV("י"), 22,  scale4,scale4, 295+int44,  buttom4),
                Ott(mV("ם"), 23,  scale4,scale4, 320+int44,  buttom4)


                /*    Ott(mV("ן"), 24,  scale4,scale4, 500+int44,  buttom4),
                   Ott(mV("א"), 25,  scale2,scale2, 85,  buttom3),
                   Ott(mV("י"), 26,  scale2,scale2, 100,  buttom3),
                   Ott(mV("ן"), 27,  scale2,scale2, 105,  buttom3-10),
                   Ott(mV("א"), 28,  scale2,scale2, 152+int2,  buttom3),
                   Ott(mV("ג"), 29,  scale2,scale2, 175+int2,  buttom3),
                   Ott(mV("י"), 30,  scale2,scale2, 187+int2,  buttom3+10),
                   Ott(mV("נ"), 31,  scale2,scale2, 208+int2,  buttom3-10),
                   Ott(mV("ד"), 32,  scale2+5,scale2+5, 220+int2,  buttom3),
                   Ott(mV("ה"), 33,  scale2,scale2, 250+int2,  buttom3),
                   Ott(mV("פ"), 34,  scale2,scale2, 295+int2,  buttom3),
                   Ott(mV("ו"), 35,  scale2,scale2, 315+int2,  buttom3),
                   Ott(mV("ל"), 36,  scale2,scale2, 330+int2,  buttom3),
                   Ott(mV("י"), 37,  scale2,scale2, 350+int2,  buttom3),
                   Ott(mV("ט"), 38,  scale2,scale2, 370+int2,  buttom3),
                   Ott(mV("י"), 39,  scale2,scale2, 390+int2,  buttom3),
                   Ott(mV("ת"), 40,  scale2,scale2, 410+int2,  buttom3),

                   Ott(mV("ה"), 41,  scale2,scale2, 90+int3,  buttom4),
                   Ott(mV("ם"), 42,  scale2-8,scale2, 125+int3,  buttom4),

                   Ott(mV("פ"), 43,  scale2,scale2, 172+int3,  buttom4),
                   Ott(mV("ש"), 44,  scale2,scale2, 197+int3,  buttom4),
                   Ott(mV("ו"), 45,  scale2,scale2, 215+int3,  buttom4),
                   Ott(mV("ט"), 46,  scale2+3,scale2+3, 233+int3,  buttom4),

                   Ott(mV("ק"), 47,  scale2+5,scale2+5, 285+int3,  buttom4-15),
                   Ott(mV("י"), 48,  scale2,scale2, 305+int3,  buttom4),
                   Ott(mV("י"), 49,  scale2,scale2, 315+int3,  buttom4),
                   Ott(mV("מ"), 50,  scale2,scale2, 330+int3,  buttom4-3),
                   Ott(mV("י"), 51,  scale2,scale2, 350+int3,  buttom4),
                   Ott(mV("ם"), 52,  scale2,scale2, 373+int3,  buttom4)*/
        )

    }

    private fun mV(letter: String): ImageView {
        val imageView = ImageView(this)
        val address = Helper(this).getAnimation1(letter)
        if (animationMode) {
            val address = Helper(this).getAnimation2(letter)
            imageView.setImageResource(address)
        } else {
            val address = Helper(this).getAnimation1(letter)
            imageView.setImageResource(address)
        }
        return imageView
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


    private fun setDimension() { // Adjust the size of the video
// so it fits on the screen
        val videoProportion = getVideoProportion()
        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels
        val screenProportion =
                screenHeight.toFloat() / screenWidth.toFloat()
        val lp = videoView.layoutParams
        if (videoProportion < screenProportion) {
            lp.height = screenHeight
            lp.width = (screenHeight.toFloat() / videoProportion).toInt()
            lp.height = lp.height * 3
            lp.width = lp.width * 3
        } else {
            lp.width = screenWidth
            lp.height = (screenWidth.toFloat() * videoProportion).toInt()
        }
        videoView.layoutParams = lp
    }

    // This method gets the proportion of the video that you want to display.
// I already know this ratio since my video is hardcoded, you can get the
// height and width of your video and appropriately generate  the proportion
//    as :height/width
    private fun getVideoProportion(): Float {
        return 1.5f
    }

    private fun videoBackgroundPreperation() {
        videoBG = findViewById(R.id.videoView) as VideoView
        val uri = Uri.parse(
                ("android.resource://" // First start with this,
                        + packageName // then retrieve your package name,
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

    /*override fun onPause() {
        super.onPause()
        // Capture the current video position and pause the video.
        mCurrentVideoPosition = mMediaPlayer!!.currentPosition
        videoBG!!.pause()
    }*/

    override fun onResume() {
        super.onResume()
        // Restart the video when resuming the Activity
        videoBG!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer!!.release()
        mMediaPlayer = null
    }
}

