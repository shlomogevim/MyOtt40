package com.example.myott40

import android.animation.AnimatorInflater
import android.content.res.Resources
import android.graphics.drawable.AnimatedVectorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var util: Uties
    var counter = 0
    private var animationMode = false
    lateinit var otts: ArrayList<Ott>
    private var videoBG: VideoView? = null
    var mMediaPlayer: MediaPlayer? = null
    var mCurrentVideoPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        util = Uties(this)

        util.setDimension()

        //  util.videoBackgroundPreperation()

        animationMode = true
       /* mainLayout.setBackgroundResource(R.drawable.paper)
        videoView.visibility=View.INVISIBLE*/

            //   mainLayout.setOnClickListener {
        CoroutineScope(Dispatchers.Main).launch {

            createOtt()
            drawAllOtts()
            // lastApizode()
        }
        //   }
    }



    private suspend fun drawAllOtts() {
        delay(1000)
        withContext(Dispatchers.Main) {
            for (i in 0 until otts.size) {
                drawOneOtt(otts[i])
            }
        }
    }

    private suspend fun drawOneOtt(ott: Ott) {
        val image = ott.iv

        if (animationMode) {
            if (counter > 0) delay(500)
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



    private fun createOtt() {
        otts = ArrayList()
        val bottom0 = 605
        val buttom1 = 550
        val buttom2 = 410
        val buttom3 = 295
        val buttom4 = 242
        val right0 = 60
        val right1 = 45
        val right11 = 40
        val right2 = 40
        val right3 = 40
        val right4= 25
        val right40= 25
        val right41= 15
        val right42= 20
        val scale0 = 120
        val scale1 = 110
        val scale2 = 100
        val scale3 = 70
        val scale4 = 40
        otts = arrayListOf(
            Ott(mV("ח"), 0, scale0+10, scale0+10, 80 + right0, bottom0+10),
            Ott(mV("פ"), 1, scale0+0 ,scale0+0, 160 + right0, bottom0+20),
            Ott(mV("ש"), 2, scale0-20, scale0 - 25, 225 + right0, bottom0 +30),

         Ott(mV("א"), 3, scale1+0 , scale1 +0, 30 + right1, buttom1 -10),
         Ott(mV("ת"), 4, scale1+0 , scale1 -10, 90 + right1, buttom1 -20),

         Ott(mV("ה"), 5, scale1+0 , scale1 -10, 182 + right11, buttom1-10),
         Ott(mV("א"), 6, scale1+0 , scale1 +0, 255 + right11, buttom1 -10),
         Ott(mV("ו"), 7, scale1+0 , scale1 +0, 285 + right11, buttom1 -10),
         Ott(mV("ר"), 8, scale1+0 , scale1 +0, 321 + right11, buttom1-20 ),

         Ott(mV("ב"), 9, scale2+0 , scale2 +0, 100 + right2, buttom2-0 ),
         Ott(mV("י"), 10, scale2+0 , scale2 +0, 135 + right2, buttom2-0 ),
         Ott(mV("נ"), 11, scale2+0 , scale2 +0, 170 + right2, buttom2-10 ),
         Ott(mV("י"), 12, scale2+0 , scale2 +0, 190 + right2, buttom2-0 ),
         Ott(mV("נ"), 13, scale2+0 , scale2 +0, 225 + right2, buttom2-10 ),
         Ott(mV("ו"), 14, scale2+0 , scale2 +0, 250 + right2, buttom2-0 ),

         Ott(mV("ח"), 15, scale3+0 , scale3 +0, 50 + right3, buttom3-0 ),
         Ott(mV("ו"), 16, scale3+0 , scale3 +0, 73 + right3, buttom3+5 ),
         Ott(mV("ש"), 17, scale3+0 , scale3 +0, 95 + right3, buttom3-0 ),
         Ott(mV("ך"), 18, scale3+0 , scale3 +0, 130 + right3, buttom3-10),

         Ott(mV("י"), 19, scale4+0 , scale4 +0, 80 + right4, buttom4-0 ),
         Ott(mV("ש"), 20, scale4+0 , scale4 +0, 93 + right4, buttom4-5 ),

         Ott(mV("כ"), 21, scale4+0 , scale4 +0, 130 + right40, buttom4-6 ),
         Ott(mV("ב"), 22, scale4+5 , scale4 +5, 150 + right40, buttom4-3 ),
         Ott(mV("ר"), 23, scale4+0 , scale4 +0, 176 + right40, buttom4-6 ),

         Ott(mV("מ"), 24, scale4+0 , scale4 +0, 215 + right41, buttom4-4 ),
         Ott(mV("ס"), 25, scale4+0 , scale4 +0, 239 + right41, buttom4-5 ),
         Ott(mV("פ"), 26, scale4+0 , scale4 +0, 265 + right41, buttom4-2 ),
         Ott(mV("י"), 27, scale4+0 , scale4 +0, 280 + right41, buttom4-0 ),
         Ott(mV("ק"), 28, scale4+10 , scale4 +10, 292 + right41, buttom4-15 ),

         Ott(mV("ל"), 28, scale4+0 , scale4 +0, 330 + right42, buttom4-0 ),
         Ott(mV("כ"), 28, scale4+0 , scale4 +0, 351 + right42, buttom4-5 ),
         Ott(mV("ו"), 28, scale4+0 , scale4 +0, 362 + right42, buttom4-1 ),
         Ott(mV("ל"), 28, scale4+0 , scale4 +0, 375 + right42, buttom4-0 ),
         Ott(mV("ם"), 28, scale4-5 , scale4 -5, 400 + right42, buttom4-1 )










            /* Ott(mV("ם"), 3, scale0 - 15, scale0 - 15, 283 + int00, bottom0 - 1)

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
             Ott(mV("ם"), 23,  scale4,scale4, 320+int44,  buttom4)*/


        )

    }

    private fun mV(letter: String): ImageView {
        val imageView = ImageView(this)
        //   val address = Helper(this).getAnimation1(letter)
        if (animationMode) {
            val address = Helper(this).getAnimation2(letter)
            imageView.setImageResource(address)
        } else {
            val address = Helper(this).getAnimation1(letter)
            imageView.setImageResource(address)
        }
        return imageView
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

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


    /* override fun onResume() {
         super.onResume()
         // Restart the video when resuming the Activity
         videoBG!!.start()
     }

     override fun onDestroy() {
         super.onDestroy()
         // When the Activity is destroyed, release our MediaPlayer and set it to null.
         mMediaPlayer!!.release()
         mMediaPlayer = null
     }*/


    /*  private fun setDimension() { // Adjust the size of the video
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
      }*/

    /*  private fun videoBackgroundPreperation() {
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
      }*/

    /*override fun onPause() {
        super.onPause()
        // Capture the current video position and pause the video.
        mCurrentVideoPosition = mMediaPlayer!!.currentPosition
        videoBG!!.pause()
    }*/


}

