package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel: ViewModel() {

    /*  private val yolov3ModelPath = "yolo-v3-tiny-tf.blob"
    private val yolov4ModelPath = "yolov4_tiny_coco_416x416_6shave.blob"
    */

    private val yolov5ModelPath = "yolov5s_416_6shave.blob"
        /*  private val mobilenetModelPath = "mobilenet-ssd.blob"*/

    private val rgbWidth = 416
    private val rgbHeight = 416
    private val disparityWidth = 640
    private val disparityHeight = 400

    // private val framePeriod:Long = 30

    val running = mutableStateOf(false)
    private var assets:AssetManager? = null
    private val _rgbImage =
        MutableStateFlow(Bitmap.createBitmap(rgbWidth, rgbHeight, Bitmap.Config.ARGB_8888))
    private val _depthImage = MutableStateFlow(
        Bitmap.createBitmap(
            disparityWidth,
            disparityHeight,
            Bitmap.Config.ARGB_8888
        )
    )

    private var job: Job? = null
    var rgbImage: StateFlow<Bitmap> = _rgbImage.asStateFlow()
    var depthImage: StateFlow<Bitmap> = _depthImage.asStateFlow()


    fun startDevice(){
        startDevice(yolov5ModelPath, rgbWidth, rgbHeight)
    }

     fun runDepthaiCamera() {
         job?.cancel()
        job = viewModelScope.launch {
            while (isActive) {
                    val rgb = imageFromJNI()
                    if (rgb != null && rgb.isNotEmpty()) {
                        _rgbImage.value.setPixels(rgb, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
                        updateImageBitmap(_rgbImage.value)
                    }
                    val detectionsImg = detectionImageFromJNI()
                    if (detectionsImg != null && detectionsImg.isNotEmpty()) {
                        _rgbImage.value.setPixels(
                            detectionsImg,
                            0,
                            rgbWidth,
                            0,
                            0,
                            rgbWidth,
                            rgbHeight
                        )
                        updateImageBitmap(_rgbImage.value)
                    }
                    val depth = depthFromJNI()
                    if (depth != null && depth.isNotEmpty()) {
                        _depthImage.value.setPixels(
                            depth, 0, disparityWidth, 0, 0, disparityWidth, disparityHeight
                        )
                        updateDepthBitmap(_depthImage.value)
                    }
                    delay(1000)
            }
        }

    }

    fun stopUpdatingImageBitmap() {
        job?.cancel()
        job = null
    }

    private suspend fun updateImageBitmap(bitmap: Bitmap) {
        withContext(Dispatchers.Main) {
            _rgbImage.value = bitmap
        }
    }

    private suspend fun updateDepthBitmap(bitmap: Bitmap) {
        withContext(Dispatchers.Main) {
            _depthImage.value = bitmap
        }

    }

/*    private fun scheduleDepthaiCamera() {
        handler.postDelayed({
            // Call the function
            runDepthaiCamera()
            // Schedule the function to run again after 30ms
            scheduleDepthaiCamera()
        }, framePeriod)
    }*/

    init {
        Timber.d("Init MainViewModel")
        Timber.d("DepthAiCamera started")
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

    // We need AssetManager for JNI part
    fun getAssetManager(): AssetManager? {
        return assets
    }

    fun setAssetManager(assetMng:AssetManager?){
        assets = assetMng
    }

}