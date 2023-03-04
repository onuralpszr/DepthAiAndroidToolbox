package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.os.depthaiandroidtoolbox.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val yolov3_model_path = "yolo-v3-tiny-tf.blob"
    private val yolov4_model_path = "yolov4_tiny_coco_416x416_6shave.blob"
    private val yolov5_model_path = "yolov5s_416_6shave.blob"
    private val mobilenet_model_path = "mobilenet-ssd.blob"

    private val rgbImageView: ImageView? = null
    private var depthImageView:android.widget.ImageView? = null
    private val rgb_image: Bitmap? = null
    private var depth_image:android.graphics.Bitmap? = null

    private val rgbWidth = 416
    private val rgbHeight = 416
    private val disparityWidth = 640
    private val disparityHeight = 400
    private val framePeriod = 30


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = ""
    }

    /**
     * A native method that is implemented by the 'depthaiandroidtoolbox' native library,
     * which is packaged with this application.
     */
    fun getAssetManager(): AssetManager? {
        return assets
    }


    external fun startDevice(modelPath: String?, rgbWidth: Int, rgbHeight: Int)
    external fun imageFromJNI(): IntArray?
    external fun detectionImageFromJNI(): IntArray?
    external fun depthFromJNI(): IntArray?

    companion object {
        // Used to load the 'depthaiandroidtoolbox' library on application startup.
        init {
            System.loadLibrary("depthaiandroidtoolbox")
        }
    }
}