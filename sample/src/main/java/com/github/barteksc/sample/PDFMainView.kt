package com.github.barteksc.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.github.barteksc.pdfviewer.util.FitPolicy
import com.github.barteksc.sample.ui.theme.AndroidPdfViewerTheme

class PDFMainView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPdfViewerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                    // widget.ImageView
                    Column() {
                        Box(modifier = Modifier.height(200.dp))
                        //Divider(modifier = Modifier.height(200.dp), 200.dp, Color.Red)
                        AndroidView(modifier = Modifier.fillMaxSize(), factory = { ctx ->

                            //  Initialize a View or View hierarchy here
                            PDFView(ctx, null).apply {
                                val pdfFileName =  "sample_1.pdf"

                                this.fromAsset(PDFViewActivity.SAMPLE_FILE)
                                        .defaultPage(1)
                                        // .onPageChange(this)
                                        .enableAnnotationRendering(true)
                                        //.onLoad(this)
                                        // .scrollHandle(DefaultScrollHandle(this))
                                        .spacing(10) // in dp
                                        //.onPageError(this)
                                        .pageFitPolicy(FitPolicy.BOTH)
                                        .load()
                            }
                        })
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPdfViewerTheme {
        Greeting("Android")
    }
}