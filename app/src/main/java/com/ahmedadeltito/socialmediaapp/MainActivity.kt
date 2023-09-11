package com.ahmedadeltito.socialmediaapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahmedadeltito.socialmediaapp.ui.theme.SocialMediaAppTheme
import com.ahmedadeltito.ui.home.PostContainerActivity
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialMediaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                }
            }
        }
    }


    @Composable
    fun SplashScreen(modifier: Modifier = Modifier) {
        val alpha = remember {
            Animatable(initialValue = 0F)
        }

        LaunchedEffect(key1 = true) {
            alpha.animateTo(
                targetValue = 1F,
                animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
            )
            delay(500)
            startActivity(
                Intent(
                    this@MainActivity,
                    PostContainerActivity::class.java
                )
            )
            this@MainActivity.finish()
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.purple_200)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = R.drawable.social_media_icon),
                contentDescription = stringResource(id = R.string.splash_screen_content_description)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SocialMediaAppTheme {
            SplashScreen()
        }
    }
}