package com.example.apptareasapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptareasapi.navigate.AppNavigation
import com.example.apptareasapi.ui.theme.AppTareasApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTareasApiTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        MainApp()

                }
            }
        }
    }
}
    @Composable
    fun MainApp() {
        AppNavigation(onHomeNavigate = { user, token ->
            // Manejar lógica adicional si es necesario (guardar token, etc.)
        })
    }