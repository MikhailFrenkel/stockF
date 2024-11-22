package com.frenkel.stockf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.frenkel.finnhub_client.startWebSocket
import com.frenkel.stockf.features.main.MainScreen
import com.frenkel.ui_kit.ui.theme.FTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                startWebSocket()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

        enableEdgeToEdge()
        setContent {
            FTheme {
                KoinContext {
                    App()
                }
            }
        }
    }
}