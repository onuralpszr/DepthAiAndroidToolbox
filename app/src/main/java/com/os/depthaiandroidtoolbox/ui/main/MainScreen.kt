package com.os.depthaiandroidtoolbox.ui.main

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.os.depthaiandroidtoolbox.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.logging.Handler

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        modifier = Modifier.background(Color.White),
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "DepthAi Preview Bitmap",
    )
}

@Composable
fun MainScreen(assetMng:AssetManager?,viewModel: MainViewModel = viewModel() ) {
    viewModel.setAssetManager(assetMng)
    val rgbImage by viewModel.rgbImage.collectAsState()
    val depthImage by viewModel.depthImage.collectAsState()

    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // content here
            Text(text = "Rgb Camera ")
            BitmapImage(bitmap = rgbImage)
            Text(text = "Depth(YoloV5) Camera ")
            BitmapImage(bitmap = depthImage)
            Spacer(modifier = Modifier.height(0.dp))
            Button(onClick = {
                viewModel.startDevice()
                viewModel.running.value = true
            }) {
                Text(text = "Start Device")
            }
            Button(onClick = { viewModel.runDepthaiCamera()
            }) {
                Text(text = "Run Device")
            }

        }

    // Update the UI on the main thread
    LaunchedEffect(rgbImage) {
        withContext(Dispatchers.Main) {
            // Do nothing, just wait for the effect to recompose the Composable
        }
    }
}

