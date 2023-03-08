package com.budgetmanager.charts

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.budgetmanager.ui.theme.Typography

@OptIn(ExperimentalTextApi::class)
@Composable
fun PieChart(
    data: Map<String, Float>,
    outerRadius: Dp,
    chartBarWidth: Dp,
    chartAnimationDuration: Int = 1000,
    colors: List<Color>,
    showLegend: Boolean = true,
    chartCenterValue: String
){
    val dataTotal = data.values.sum()
    val arcValues = mutableListOf<Float>()

    data.values.forEachIndexed { index, value -> arcValues.add(index, element = 360 * value / dataTotal) }

    var animationExecuted by remember { mutableStateOf( false ) }

    var trailingValue = -90f

    var textVisible by remember { mutableStateOf(false) }

    val animatedSize by animateFloatAsState(
        targetValue = if (animationExecuted) outerRadius.value else 0f,
        animationSpec = tween(
            durationMillis = chartAnimationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = { textVisible = true }
    )

    val animatedRotation by animateFloatAsState(
        targetValue = if (animationExecuted) 90f * 12 else 0f,
        animationSpec = tween(
            durationMillis = chartAnimationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(true){ animationExecuted = true }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showLegend) {
            ChartLegend(data = data, colors = colors)
        }

        val textMeasurer = rememberTextMeasurer()
        val textLayoutResult: TextLayoutResult =
            textMeasurer.measure(text = AnnotatedString(chartCenterValue))
        val textSize = textLayoutResult.size

        Box(
            modifier = Modifier.size(animatedSize.dp),
            contentAlignment = Alignment.Center,
        ){
            Canvas(
                modifier = Modifier
                    .size(outerRadius * 2f)
                    .rotate(animatedRotation),
            ){
                arcValues.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        trailingValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    trailingValue += value
                }

                val canvasWidth = size.width
                val canvasHeight = size.height

                if (textVisible) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = chartCenterValue,
                        topLeft = Offset(
                            (canvasWidth - textSize.width) / 2f,
                            (canvasHeight - textSize.height) / 2f
                        ),
                        style = Typography.subtitle1
                    )
                }
            }
        }
    }
}


@Composable
fun ChartLegend(
    data: Map<String, Float>,
    colors: List<Color>,
){
    Column {
        data.values.forEachIndexed { index, value ->
            ChartLegendItem(
                itemData = Pair(data.keys.elementAt(index), value),
                color = colors[index]
            )
        }
    }
}


@Composable
fun ChartLegendItem(
    itemData: Pair<String, Float>,
    height: Dp = 10.dp,
    color: Color,
){

    Surface(
        modifier = Modifier
            .padding(5.dp),
        color = Color.Transparent
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .size(height)
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = itemData.first,
                style = Typography.caption
            )
        }
    }
}