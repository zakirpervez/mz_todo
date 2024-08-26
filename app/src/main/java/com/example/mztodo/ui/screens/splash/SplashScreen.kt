package com.example.mztodo.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mztodo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }

    val alpha = animateFloatAsState(targetValue = if (startAnimation) 1f else 0f, label = "")
    val scale = animateFloatAsState(targetValue = if (startAnimation) 1f else 0.5f, label = "")

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(3000)
        onNavigate()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        BasicText(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(3f, 3f),
                    blurRadius = 6F
                )
            ),
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_5)
@Composable
fun SplashScreenPreview() {
    SplashScreen {

    }
}
