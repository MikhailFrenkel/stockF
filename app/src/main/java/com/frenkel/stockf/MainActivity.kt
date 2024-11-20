package com.frenkel.stockf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.frenkel.stockf.features.main.MainScreen
import com.frenkel.ui_kit.ui.theme.FTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FTheme {
                App()
            }
        }
    }
}