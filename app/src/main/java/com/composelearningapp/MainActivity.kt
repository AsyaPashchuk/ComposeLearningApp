package com.composelearningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.composelearningapp.compose.CircularProgressBar
import com.composelearningapp.compose.ColorBox
import com.composelearningapp.compose.ImageCard
import com.composelearningapp.compose.TextStyleWithAnnotatedString
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.oswald_extralight, FontWeight.ExtraLight),
            Font(R.font.oswald_light, FontWeight.Light),
            Font(R.font.oswald_regular, FontWeight.Normal),
            Font(R.font.oswald_medium, FontWeight.Medium),
            Font(R.font.oswald_semibold, FontWeight.SemiBold),
            Font(R.font.oswald_bold, FontWeight.Bold)
        )
        
        setContent {
            val painter = painterResource(id = R.drawable.snow_img)
            val description = "Children on the snow"
            val title = "Happy childhood with winter"

            //For scaffold
            val scaffoldState = rememberScaffoldState()
            var textFieldState by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()

            //For animations
            var sizeState by remember { mutableStateOf(100.dp) }
            val size by animateDpAsState(
                targetValue = sizeState,
                tween(
                    durationMillis = 1000
//                    delayMillis = 300,
//                    easing = LinearOutSlowInEasing
                )
//                spring(Spring.DampingRatioHighBouncy)
//                keyframes { durationMillis = 5000
//                    sizeState at 0 with LinearEasing
//                    sizeState * 1.5f at 1000 with FastOutLinearInEasing
//                    sizeState * 2 at 5000
//                }
            )
            //Infinite animation
            val infiniteTransition = rememberInfiniteTransition()
            val animatedColor by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(2000),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val color = remember {
                        mutableStateOf(Color.Yellow)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //CircularProgressBar
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressBar(
                            percentage = 0.75f,
                            number = 100)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //Animations
                    Box(modifier = Modifier
                        .size(size)
                        .background(animatedColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            sizeState = 50.dp
                        }) {
                            Text("Increase size")
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //ConstraintLayout - need to add dependency
                    val constraints = ConstraintSet {
                        val greenBox = createRefFor("greenbox")
                        val redBox = createRefFor("redbox")
                        val guideLine = createGuidelineFromTop(0.5f)

                        constrain(greenBox) {
                            top.linkTo(guideLine)
                            start.linkTo(parent.start)
                            width = Dimension.value(100.dp)
                            height = Dimension.value(100.dp)
                        }
                        constrain(redBox) {
                            top.linkTo(parent.top)
                            start.linkTo(greenBox.end)
                            end.linkTo(parent.end)
//                            width = Dimension.fillToConstraints
                            width = Dimension.value(100.dp)
                            height = Dimension.value(100.dp)
                        }
                        createHorizontalChain(
                            greenBox, redBox,
                            chainStyle = ChainStyle.Packed)
                    }
                    
                    ConstraintLayout(constraints,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)) {
                        Box(modifier = Modifier
                            .background(Color.Green)
                            .layoutId("greenbox"))
                        Box(modifier = Modifier
                            .background(Color.Red)
                            .layoutId("redbox"))
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //Lists
                    //Column with scroll
//                    val scrollState = rememberScrollState()
//                    Column(
//                        modifier = Modifier.verticalScroll(scrollState)
//                    ) {
//                        for (i in 0..50) {
//                            Text(
//                                text = "Item $i",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 24.dp)
//                            )
//                        }
//                    }

                    //LazyColumn
//                    LazyColumn {
//                        //Indexed items
//                        itemsIndexed(listOf("This", "is", "list",
//                            "with", "different", "lists", "inside")
//                        ) { index, string ->
//                            Text(
//                                text = string,
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 24.dp)
//                            )
//                        }
                        //Just items
//                        items(500) {
//                            Text(
//                                text = "Item $it",
//                                fontSize = 24.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 24.dp)
//                            )
//                        }
//                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //ImageCard with text and color gradient
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ImageCard(
                            painter = painter,
                            contentDescription = description,
                            title = title)
                    }

                    //Text with different styles and annotated string
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color(0xFF101010))
                        .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        TextStyleWithAnnotatedString(fontFamily = fontFamily, firstLetter = "J", text = "etpackCompose")
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //ColorBox with state
                    ColorBox(
                        fontFamily = fontFamily,
                        Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        color.value = it
                    }

                    Box(modifier = Modifier
                        .background(color.value)
                        .fillMaxWidth()
                        .height(150.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    //TextField, Button, SnackBar
                    TextField(
                        value = textFieldState,
                        label = {
                            Text(text = "Enter your name")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = {
                        scope.launch {
                            scaffoldState
                                .snackbarHostState
                                .showSnackbar("Hello, $textFieldState")
                        }
                    }) {
                        Text(text = "Please greet me")
                    }

                    Spacer(modifier = Modifier.height(15.dp))



                }
            }
        }
    }
}