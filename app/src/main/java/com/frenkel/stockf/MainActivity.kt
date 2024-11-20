package com.frenkel.stockf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.frenkel.ui_kit.ui.components.Button
import com.frenkel.ui_kit.ui.components.ButtonSize
import com.frenkel.ui_kit.ui.components.ButtonStyle
import com.frenkel.ui_kit.ui.theme.FTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Button(
                            text = "Preview",
                            onClick = {},
                            buttonStyle = ButtonStyle.TERTIARY,
                            buttonSize = ButtonSize.SMALL,
                            enabled = false,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}