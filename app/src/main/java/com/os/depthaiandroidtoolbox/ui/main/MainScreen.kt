package com.os.depthaiandroidtoolbox.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.os.depthaiandroidtoolbox.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val rgbImage = viewModel.rgbImage.observeAsState().value
    val depthImage = viewModel.depthImage.observeAsState().value
    var count by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var job: Job? = null


    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // content here
        Text(text = "Rgb Camera ")
        Image(bitmap = rgbImage!!.asImageBitmap(), "RgbImage")
        Text(text = "Depth(YoloV5) Camera")
        Image(bitmap = depthImage!!.asImageBitmap(), "Depth(YoloV5) Camera ")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Cycle Count $count")

        Button(onClick = {
            viewModel.startDevice()
        }) {
            Text(text = "Start Device")
        }
        Button(onClick = {
            job?.cancel()
            job = coroutineScope.launch {
                while (isActive) {
                    count++
                    viewModel.runDepthaiCamera()
                    delay(100)
                }
            }
        }) {
            Text(text = "Run Device")
        }

    }
}

