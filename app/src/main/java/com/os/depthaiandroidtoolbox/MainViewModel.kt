package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import timber.log.Timber

class MainViewModel(val assets: AssetManager) : ViewModel() {

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
    private val _rgbImage =
        MutableLiveData(Bitmap.createBitmap(rgbWidth, rgbHeight, Bitmap.Config.ARGB_8888))
    private val _depthImage = MutableLiveData(
        Bitmap.createBitmap(
            disparityWidth,
            disparityHeight,
            Bitmap.Config.ARGB_8888
        )
    )

    private var job: Job? = null
    val rgbImage: LiveData<Bitmap> = _rgbImage
    val depthImage: LiveData<Bitmap> = _depthImage


    fun startDevice() {
        startDevice(yolov5ModelPath, rgbWidth, rgbHeight)
    }

    fun runDepthaiCamera() {
        val rgb = imageFromJNI()
        if (rgb != null && rgb.isNotEmpty()) {
            _rgbImage.value?.setPixels(rgb, 0, rgbWidth, 0, 0, rgbWidth, rgbHeight)
            updateImageBitmap(_rgbImage.value!!)
        }
        val detectionsImg = detectionImageFromJNI()
        if (detectionsImg != null && detectionsImg.isNotEmpty()) {
            _rgbImage.value?.setPixels(
                detectionsImg,
                0,
                rgbWidth,
                0,
                0,
                rgbWidth,
                rgbHeight
            )
            updateImageBitmap(_rgbImage.value!!)
        }
        val depth = depthFromJNI()
        if (depth != null && depth.isNotEmpty()) {
            _depthImage.value!!.setPixels(
                depth, 0, disparityWidth, 0, 0, disparityWidth, disparityHeight
            )
            updateDepthBitmap(_depthImage.value!!)
        }
    }

    fun stopUpdatingImageBitmap() {
        job?.cancel()
        job = null
    }

    private fun updateImageBitmap(bitmap: Bitmap) {
        _rgbImage.postValue(bitmap)
    }

    private fun updateDepthBitmap(bitmap: Bitmap) {
        _depthImage.postValue(bitmap)
    }

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

    // AssetManager for JNI part
    // readModelFromAsset from cvDaiUtils uses it.
    fun getAssetManager(): AssetManager {
        return assets
    }
}