package com.tabbycat.greeting

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(name = "Charles")
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(8, 54, 5))) {

        // Select Gif loader in order of fastest to slowest option
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        // Use a badly sized image three times to cover the entire view area
        Image(painter = rememberAsyncImagePainter(R.drawable.confetti, imageLoader),
            contentDescription = "Falling Confetti",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter)

        Image(painter = rememberAsyncImagePainter(R.drawable.confetti, imageLoader),
            contentDescription = "Falling Confetti",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center)

        Image(painter = rememberAsyncImagePainter(R.drawable.confetti, imageLoader),
            contentDescription = "Falling Confetti",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.BottomCenter)


        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "Happy Birthday $name!",
                // Scalable pixel, best for text because it adjusts to user font settings
                fontSize = 30.sp,
                color = Color(48, 145, 41)
            )

            Image(painter = painterResource(id = R.drawable.bday_icon),
                contentDescription = "Birthday Cake")
        }
    }
}

