package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.os.depthaiandroidtoolbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*  private val yolov3ModelPath = "yolo-v3-tiny-tf.blob"
        private val yolov4ModelPath = "yolov4_tiny_coco_416x416_6shave.blob"*/
    private val yolov5ModelPath = "yolov5s_416_6shave.blob"
    /*  private val mobilenetModelPath = "mobilenet-ssd.blob"*/

    private val rgbWidth = 416
    private val rgbHeight = 416
    private val disparityWidth = 640
    private val disparityHeight = 400
    private var framePeriod: Long = 30

    private var running: Boolean = false
    private var firstTime: Boolean = false
    private var rgbImage: Bitmap = Bitmap.createBitmap(rgbWidth, rgbHeight, Bitmap.Config.ARGB_8888)
    private var depthImage: Bitmap =
        Bitmap.createBitmap(disparityWidth, disparityHeight, Bitmap.Config.ARGB_8888)

    fun runDepthaiCamera() {
        if (running) {
            if (firstTime) {
                // Start the device
                startDevice(yolov5ModelPath, rgbWidth, rgbHeight)
                firstTime = false
            }
            val rgb = imageFromJNI()
            if (rgb != null && rgb.isNotEmpty()) {
                rgbImage.setPixels(rgb, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
                binding.rgbImageView.setImageBitmap(rgbImage)
            }
            val detectionsImg = detectionImageFromJNI()
            if (detectionsImg != null && detectionsImg.isNotEmpty()) {
                rgbImage.setPixels(detectionsImg, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
                binding.rgbImageView.setImageBitmap(rgbImage)
            }
            val depth = depthFromJNI()
            if (depth != null && depth.isNotEmpty()) {
                depthImage.setPixels(
                    depth, 0, disparityWidth, 0, 0, disparityWidth, disparityHeight
                )
                binding.depthImageView.setImageBitmap(depthImage)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Specify running and firstTime (to connect to device)
        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running", true);
            firstTime = savedInstanceState.getBoolean("firstTime", true);
        } else {
            running = true;
            firstTime = true;
        }

        Handler(Looper.getMainLooper()).postDelayed({
            runDepthaiCamera()
        }, framePeriod)
    }

    // Main loop where the data is obtained from the device and shown into the screen


    fun getAssetManager(): AssetManager? {
        return assets
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
        firstTime = true
    }

    // Save the variable before completing the activity
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Write the variable with the key in the Bundle
        outState.putBoolean("running", running)
        outState.putBoolean("firstTime", firstTime)
    }


    external fun startDevice(modelPath: String?, rgbWidth: Int, rgbHeight: Int)
    external fun imageFromJNI(): IntArray?
    external fun detectionImageFromJNI(): IntArray?
    external fun depthFromJNI(): IntArray?

    companion object {
        init {
            System.loadLibrary("depthaiandroidtoolbox")
        }
    }
}