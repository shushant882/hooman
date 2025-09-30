package com.hooman.app.screens

import android.graphics.Paint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.hooman.app.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.unit.IntOffset

//import androidx.wear.compose.materialcore.Text
//import androidx.wear.compose.materialcore.screenWidthDp

import com.hooman.app.ui.components.HoomanColors
import com.hooman.app.ui.components.Urbanist
import com.hooman.app.ui.components.logoColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted



@Composable
fun OnboardingScreen(windowSizeClass: WindowSizeClass, onGetStarted: ()-> Unit){
    var visibleText1 by remember { mutableStateOf(false) }
    var visibleText2 by remember { mutableStateOf(false) }
    var visibleText3 by remember { mutableStateOf(false) }
    var visibleButton by remember { mutableStateOf(false) }


    var visibleContent by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        visibleContent=true
    }

//    //get current screen size for responsive design
//    val configuration= LocalConfiguration.current
//    val screenWidth= configuration.screenWidthDp.dp
//    val screenHeight= configuration.screenHeightDp.dp
//    val isTablet = configuration.screenWidthDp >= 600
//    val isFoldable = configuration.screenWidthDp >= 900 ||
//            (configuration.screenWidthDp > configuration.screenHeightDp && configuration.screenWidthDp >= 700)
//


    //Hooman color theme
    val backgroundColor= HoomanColors.bgPrimary.resolve()
    val textPrimary= HoomanColors.textPrimary.resolve()
    val textSecondary= HoomanColors.textSecondary.resolve()
    val buttonColor= HoomanColors.accentOrange
    val buttonTextColor= HoomanColors.pureWhite

//    // Responsive dimensions
//    val logoSize = when {
//        isFoldable -> 300.dp
//        isTablet -> 350.dp
//        screenWidth < 360.dp -> 280.dp // Small phones
//        else -> 320.dp // Normal phones
//    }
//
//    val titleFontSize = when {
//        isFoldable -> 45.sp
//        isTablet -> 42.sp
//        screenWidth < 360.dp -> 32.sp
//        else -> 38.sp
//    }
//
//    val subtitleFontSize = when {
//        isFoldable -> 40.sp
//        isTablet -> 38.sp
//        screenWidth < 360.dp -> 28.sp
//        else -> 35.sp
//    }
//
//    val horizontalPadding = when {
//        isFoldable -> 64.dp
//        isTablet -> 48.dp
//        screenWidth < 360.dp -> 24.dp
//        else -> 32.dp
//    }
//
//    val topSpacing = when {
//        isFoldable -> 80.dp
//        isTablet -> 100.dp
//        screenHeight < 700.dp -> 60.dp // Short screens
//        else -> 100.dp
//    }

    //Control the appearance delay
    LaunchedEffect(Unit){
        delay(1000); visibleText1=true
        delay(2000);visibleText2=true
        delay(3000);visibleText3=true
        delay(3500) ;visibleButton =true}

    val animationSpec = tween<IntOffset>(durationMillis =500)
    val enterAnimation = slideInHorizontally(animationSpec = tween(2000)) { fullWidth ->
        // Start from the far left (-fullWidth)
        -fullWidth
    } + fadeIn(animationSpec = tween(3000))


    Column (modifier = Modifier.fillMaxSize().background(backgroundColor).padding(horizontal = 32.dp), horizontalAlignment = Alignment.Start){
        Spacer(modifier = Modifier.height(100.dp))
        Icon(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Hooman Logo",
            tint = logoColor // This will be orange or white
        )
        Spacer(modifier = Modifier.height(100.dp))
        Box(modifier = Modifier.weight(1f) ){

            //AnimatedVisibility(visible=visibleContent,enter = fadeIn(tween(1000)) + scaleIn(tween(1000))) {
                Column(horizontalAlignment = Alignment.Start){
                    AnimatedVisibility(visible = visibleText1, enter = enterAnimation){
                    Text(
                    text="Smart Care.",
                    style= MaterialTheme.typography.displaySmall,
                    color=textPrimary,
                    fontWeight = FontWeight.Bold)}
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedVisibility(visible = visibleText2, enter = enterAnimation)
                    {Text(
                        text="Tailored for your pet." ,
                        color=textPrimary,
                        fontWeight = FontWeight.Bold,
                        style= MaterialTheme.typography.displaySmall
                    )}
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedVisibility(visible = visibleText3, enter = enterAnimation){
                    Text(
                        text = "Backed by AI.",
                        style= MaterialTheme.typography.displaySmall,
                        color=textPrimary,
                        fontWeight = FontWeight.Bold
                    )}
                    Spacer(modifier = Modifier.height(48.dp))
                    AnimatedVisibility(visible =visibleButton, enter = enterAnimation){
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(0.8f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor =logoColor)
                    ){
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, "Get Started",  tint = HoomanColors.textInverse.resolve())
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Get Started", color = HoomanColors.textInverse.resolve(), fontWeight = FontWeight.Bold, fontSize = 18.sp)

                    }}



                }}
        }

    }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    // STEP 1: DEFINE the variable first
    val mockSizeClass = WindowSizeClass.calculateFromSize(DpSize(411.dp, 891.dp))

    // STEP 2: USE the variable you just defined
    OnboardingScreen(
        windowSizeClass = mockSizeClass,
        onGetStarted = {} // <-- FIXED: Name matches the function's definition
    )
}