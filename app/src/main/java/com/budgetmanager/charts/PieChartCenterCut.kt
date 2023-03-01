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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PieChartCenterCut(
    data: Map<String, Float>,
    outerRadius: Dp = 180.dp,
    chartBarWidth: Dp = 40.dp,
    chartAnimationDuration: Int = 1000,
    colors: List<Color>,
){
    val dataTotal = data.values.sum()
    val arcValues = mutableListOf<Float>()

    data.values.forEachIndexed { index, value -> arcValues.add(index, element = 360 * value / dataTotal) }

    var animationExecuted by remember { mutableStateOf( false ) }

    var trailingValue = 0f

    val animatedSize by animateFloatAsState(
        targetValue = if (animationExecuted) outerRadius.value else 0f,
        animationSpec = tween(
            durationMillis = chartAnimationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val animatedRotation by animateFloatAsState(
        targetValue = if (animationExecuted) 90f * 11 else 0f,
        animationSpec = tween(
            durationMillis = chartAnimationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(true){ animationExecuted = true }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
            }
        }

        ChartLegend(data = data, colors = colors)
    }
}


@Composable
fun ChartLegend(
    data: Map<String, Float>,
    colors: List<Color>,
){
    Column(
        modifier = Modifier
            .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
    ) {
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
    height: Dp = 30.dp,
    color: Color,
){

    Surface(
        modifier = Modifier
            .padding(5.dp),
        color = Color.Transparent
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
            ) {
                Text(text = itemData.first)
                Text(text = itemData.second.toString())
            }
        }
    }

}