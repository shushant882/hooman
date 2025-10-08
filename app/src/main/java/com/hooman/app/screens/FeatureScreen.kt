package com.hooman.app.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hooman.app.ui.components.*
import com.hooman.app.ui.theme.HoomanAppTheme
import com.hooman.app.ui.theme.HoomanTheme
import com.hooman.app.viewmodel.OnboardingViewModel

@Composable
fun OnboardingNavigation() {
    val navController = rememberNavController()
    val onboardingViewModel: OnboardingViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentStep = when (currentRoute) {
        "screen1" -> 1
        "screen2" -> 2
        "screen3" -> 3
        else -> 0
    }
    HoomanAppTheme {
        Column (modifier = Modifier.background(HoomanTheme.colors.backgroundPrimary)){
            if(currentStep > 0){
                ProgressIndicator(currentStep=currentStep, totalSteps = 3)
            }
            NavHost(navController=navController, startDestination = "screen1"){
                composable("screen1") { OnboardingScreen1(navController = navController, viewModel = onboardingViewModel) }
                composable("screen2") { OnboardingScreen2(navController = navController, viewModel = onboardingViewModel) }
                composable("screen3") { OnboardingScreen3(navController = navController, viewModel = onboardingViewModel) }
                // composable("screen4") { OnboardingScreen4(navController = navController, viewModel = onboardingViewModel) }
            }
        }
    }
}

// --- Screen Composables ---

@Composable
fun OnboardingScreen1(navController: NavController, viewModel: OnboardingViewModel){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {viewModel.startScreen1Animation() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TerminalWindow(text = uiState.screen1TerminalText)
            StatsRow(
                StatInfo("98%","Health Score",HoomanOrange),
                StatInfo("24/7", "AI Support", AccentCyan),
                StatInfo("50+", "Breeds Studied", AccentPurple)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Smart Care", style = HoomanTypography.displayLarge, color = HoomanTheme.colors.textPrimary)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "AI-powered health insights and personalized care recommendations for your pet's wellbeing.",
                    color = HoomanTheme.colors.textSecondary, style = HoomanTypography.titleSmall,textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
        NextButton(text = "Next", onClick={navController.navigate("screen2")})
    }
}

@Composable
fun OnboardingScreen2(navController: NavController,viewModel: OnboardingViewModel){
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {viewModel.startScreen2Animation() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ChatWindow(userMessageVisible=uiState.screen2UserMessageVisible,aiMessageVisible=uiState.screen2AiMessageVisible)

            StatsRow(
                StatInfo("99.7%", "Accuracy", AccentGreen),
                StatInfo("< 1s", "Response", AccentCyan),
                StatInfo("24/7", "Available", AccentGreen)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("AI Assistant", style = HoomanTypography.displayLarge, color = HoomanTheme.colors.textPrimary)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Meet Ezra, your intelligent companion providing instant answers and expert guidance.",
                    color = HoomanTheme.colors.textSecondary, style = HoomanTypography.titleSmall,textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
        NextButton(text = "Next", onClick={navController.navigate("screen3")})
    }
}

@Composable
fun OnboardingScreen3(navController: NavController, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            PetProfileCard()
            StatsRow(
                StatInfo("92%", "Accuracy", AccentPurple),
                StatInfo("24/7", "Monitoring", AccentCyan),
                StatInfo("Daily", "Updates", HoomanOrange)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Personalised", style = HoomanTypography.displayLarge, color = HoomanTheme.colors.textPrimary)
                Text("Insights", style = HoomanTypography.displayLarge, color = HoomanTheme.colors.textPrimary)

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Get tailored recommendations based on your pet's unique breed,age,and behaviour patterns.",
                    color = HoomanTheme.colors.textSecondary, style = HoomanTypography.titleSmall,textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
        NextButton(text = "Get started", onClick={navController.navigate("screen3")})
    }
}

// --- UI Components ---

@Composable
fun ProgressIndicator(currentStep: Int, totalSteps: Int){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 24.dp,
            vertical = 54.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp))
    {
        for(step in 1 .. totalSteps){
            Box(modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(if (step <= currentStep) HoomanOrange else HoomanColors.bgTertiary.resolve())){
            }
        }
    }
}

@Composable
fun PetProfileCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        //colors = CardDefaults.cardColors(containerColor = HoomanTheme.colors.cardBackground),
        colors = CardDefaults.cardColors(containerColor = HoomanColors.terminal.resolve())
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "Pet Icon",
                    tint = HoomanOrange,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Max (Golden Retriever)", fontWeight = FontWeight.Bold, color = HoomanTheme.colors.textPrimary)
                    Text("3 years old - 65 lbs", fontSize = 14.sp, color = HoomanTheme.colors.textSecondary)
                }
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(AccentPurple.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text("Today", fontSize = 12.sp, fontWeight = FontWeight.Medium,color=AccentPurple.copy(alpha = 1f))
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CircularStatIndicator(progress = 0.92f, value = "92", label = "Health", color = HoomanOrange)
                CircularStatIndicator(progress = 0.68f, value = "68", label = "Activity", color = AccentGreen)
                CircularStatIndicator(progress = 0.85f, value = "85", label = "Mood", color = AccentPurple)
            }
            Spacer(Modifier.height(20.dp))
            Surface(
                color = HoomanColors.bgTertiary.resolve(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Lightbulb, contentDescription = "Suggestion", tint = HoomanColors.accentGold)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Max needs 15 more minutes of exercise",
                        color = HoomanTheme.colors.textSecondary,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

// --- ANIMATED CIRCULAR STAT ---
@Composable
fun CircularStatIndicator(
    progress: Float,
    value: String,
    label: String,
    color: Color,
    size: Dp = 70.dp,
    strokeWidth: Dp = 6.dp
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) progress else 0f,
        animationSpec = tween(
            durationMillis = 2500,
            delayMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "progressAnimation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(size)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = HoomanColors.bgTertiary.light,
                style = Stroke(width = strokeWidth.toPx())
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, color = HoomanColors.pureWhite, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(label, color = HoomanTheme.colors.textTertiary, fontSize = 12.sp)
        }
    }
}

@Composable
fun FeatureHighlightsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FeatureHighlightCard("92%", "Accuracy", modifier = Modifier.weight(1f))
        FeatureHighlightCard("24/7", "Monitoring", modifier = Modifier.weight(1f))
        FeatureHighlightCard("Daily", "Updates", isSpecial = true, modifier = Modifier.weight(1f))
    }
}

@Composable
fun FeatureHighlightCard(
    line1: String,
    line2: String,
    modifier: Modifier = Modifier,
    isSpecial: Boolean = false
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HoomanTheme.colors.cardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = line1,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if(isSpecial) HoomanOrange else HoomanTheme.colors.textPrimary
            )
            Text(
                text = line2,
                fontSize = 14.sp,
                color = HoomanTheme.colors.textSecondary
            )
        }
    }
}

@Composable
fun TerminalWindow(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp).padding(top = 10.dp),
        shape = RoundedCornerShape(24.dp),
        // Minor fix: Replaced undefined 'terminal' color with a theme color
        colors = CardDefaults.cardColors(containerColor = HoomanColors.terminal.resolve())
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(Color.Red))
                Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(Color.Yellow))
                Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(Color.Green))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text, color = AccentGreen, fontSize = 16.sp, fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
fun ChatWindow(userMessageVisible: Boolean, aiMessageVisible: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HoomanColors.terminal.resolve())
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(AccentCyan))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ezra AI Assistant", color = HoomanColors.pureWhite, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.weight(1f))
                Text("Now", color = HoomanTheme.colors.textSecondary, fontSize = 12.sp)
            }
            Text("Online. Responds in <1s", modifier = Modifier.padding(start = 32.dp), color = AccentGreen, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(12.dp))
            AnimatedVisibility(visible = userMessageVisible, enter = fadeIn() + slideInVertically()) {
                UserMessageBubble("Is my dog's behavior normal?")
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = aiMessageVisible, enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })) {
                AiMessageBubble("Based on your data, Max's behavior is perfectly normal! ")
            }
        }
    }
}

@Composable
fun UserMessageBubble(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Surface(color = UserBubbleColor, shape = RoundedCornerShape(16.dp, 20.dp, 4.dp, 20.dp)) {
            Text(text, color = Color.White, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
        }
    }
}

@Composable
fun AiMessageBubble(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Surface(color = HoomanColors.bgOverlay.resolve(), shape = RoundedCornerShape(16.dp, 20.dp, 20.dp, 4.dp)) {
            Text(text,   color = HoomanColors.pureWhite, modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
        }
    }
}

data class StatInfo(val value: String, val label: String, val color: Color)
@Composable

fun StatsRow(vararg stats: StatInfo) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        stats.forEach { stat ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HoomanColors.terminal.resolve())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(modifier = Modifier.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(stat.value, style = HoomanTypography.titleMedium, color = stat.color, modifier = Modifier.padding(start = 16.dp))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(stat.label, style = HoomanTypography.titleSmall, color = HoomanTheme.colors.textSecondary, modifier = Modifier.padding(start = 20.dp))
                    }}
            }
        }
    }
}

@Composable
fun NextButton(text: String, onClick: () -> Unit) {
    // Minor Fix: Your 'logoColor' is a @Composable function, so it must be called
    // within another Composable's scope before being passed as a parameter.
    val buttonColor = logoColor

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            // Minor Fix: Removed reference to 'Urbanist' font as it was not defined.
            Text(
                text = text,
                color = HoomanTheme.colors.textInverse,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, "Next", tint = HoomanTheme.colors.textInverse)
        }
    }
}

// --- Previews ---

@Preview(name = "Onboarding Screen 3 - Light", showBackground = true, widthDp = 375, heightDp = 812)
@Composable
fun OnboardingScreen3Preview() {
    HoomanAppTheme(darkTheme = false) {
        Surface(color = HoomanTheme.colors.backgroundPrimary) {
            OnboardingScreen3(rememberNavController(), viewModel())
        }
    }
}