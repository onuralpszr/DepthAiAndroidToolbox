package com.os.depthaiandroidtoolbox

import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.os.depthaiandroidtoolbox.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


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