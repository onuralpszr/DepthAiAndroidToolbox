package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.os.depthaiandroidtoolbox.ui.theme.DepthAiAndroidToolboxTheme

class MainActivity : ComponentActivity() {

    /*  private val yolov3ModelPath = "yolo-v3-tiny-tf.blob"
        private val yolov4ModelPath = "yolov4_tiny_coco_416x416_6shave.blob"
    */
    private val yolov5ModelPath = "yolov5s_416_6shave.blob"
    /*  private val mobilenetModelPath = "mobilenet-ssd.blob"*/

    private val rgbWidth = 416
    private val rgbHeight = 416
    private val disparityWidth = 640
    private val disparityHeight = 400
    private val framePeriod:Long = 30

    private var running: Boolean = false
    private var firstTime: Boolean = false
    private var rgbImage: Bitmap =
        Bitmap.createBitmap(rgbWidth, rgbHeight, Bitmap.Config.ARGB_8888)
    private var depthImage: Bitmap =
        Bitmap.createBitmap(disparityWidth, disparityHeight, Bitmap.Config.ARGB_8888)
    val handler = Handler(Looper.getMainLooper())


    private fun runDepthaiCamera() {
            if (firstTime) {
                // Start the device
                startDevice(yolov5ModelPath, rgbWidth, rgbHeight)
                firstTime = false
            }
                val rgb = imageFromJNI()
                if (rgb != null && rgb.isNotEmpty()) {
                    rgbImage.setPixels(rgb, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
                    //binding.rgbImageView.setImageBitmap(rgbImage)
                }
                val detectionsImg = detectionImageFromJNI()
                if (detectionsImg != null && detectionsImg.isNotEmpty()) {
                    rgbImage.setPixels(detectionsImg, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
                    //binding.rgbImageView.setImageBitmap(rgbImage)
                }
                val depth = depthFromJNI()
                if (depth != null && depth.isNotEmpty()) {
                    depthImage.setPixels(
                        depth, 0, disparityWidth, 0, 0, disparityWidth, disparityHeight
                    )
                    //binding.depthImageView.setImageBitmap(depthImage)
                }
    }

    private fun scheduleDepthaiCamera() {
        handler.postDelayed({
            // Call the function
            runDepthaiCamera()
            // Schedule the function to run again after 30ms
            scheduleDepthaiCamera()
        }, framePeriod)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // Specify running and firstTime (to connect to device)
        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running", true)
            firstTime = savedInstanceState.getBoolean("firstTime", true)
        } else {
            running = true
            firstTime = true
        }
        scheduleDepthaiCamera()

        setContent {
            DepthAiAndroidToolboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DefaultLayout(rgbImage,rgbImage)
                }
            }
        }
    }


    // We need AssetManager for JNI part
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

    private external fun startDevice(modelPath: String?, rgbWidth: Int, rgbHeight: Int)
    private external fun imageFromJNI(): IntArray?
    private external fun detectionImageFromJNI(): IntArray?
    private external fun depthFromJNI(): IntArray?

    companion object {
        init {
            System.loadLibrary("depthaiandroidtoolbox")
        }
    }
}

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        modifier = Modifier.background(Color.White),
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "DepthAi Preview Bitmap",
    )
}

@Composable
fun DefaultLayout(rgbBmp:Bitmap,depthBmp:Bitmap) {
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // content here
        Text(text = "Rgb Camera ")
        BitmapImage(bitmap = rgbBmp)
        Text(text = "YoloV5 Camera ")
        BitmapImage(bitmap = depthBmp)
    }
}


// Default Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DepthAiAndroidToolboxTheme {
    }
}
