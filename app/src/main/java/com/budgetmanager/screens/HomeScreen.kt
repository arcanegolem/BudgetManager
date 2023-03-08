package com.budgetmanager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.budgetmanager.charts.PieChart
import com.budgetmanager.ui.theme.Typography
import com.budgetmanager.R

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE9B1AE),
                        Color(0xFFD2ECE0),
                        Color(0xFFD1D2E7),
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                    tileMode = TileMode.Decal
                )
            )
            .padding(10.dp)
    ) {
        val startPadding = 25.dp

        Text(
            modifier = Modifier.padding(
                start = startPadding / 1.5f,
                top = 30.dp,
                bottom = 10.dp
            ),
            text = "Мои финансы",
            style = Typography.h1
        )

        ChartInstance(startPadding = startPadding, chartRadius = 140.dp)
        Balance(startPadding = startPadding)
    }
}


@Composable
fun ChartInstance(
    startPadding : Dp,
    chartRadius: Dp
){
    Row(
        modifier = Modifier
            .height(chartRadius * 1.35f)
            .clip(
                shape = RoundedCornerShape(
                    topStart = startPadding,
                    topEnd = chartRadius / 1.5f,
                    bottomStart = startPadding,
                    bottomEnd = chartRadius / 1.5f
                )
            )
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PieChart(
            outerRadius = chartRadius,
            chartBarWidth = 20.dp,
            data = mapOf(
                Pair("Кредит"    , 56000f),
                Pair("Отдых"     , 30000f),
                Pair("Автомобиль", 30000f),
                Pair("Питание"   , 40000f),
                Pair("Жилье"     , 100000f)
            ),
            colors = listOf(
                Color(0xFFBDFC73),
                Color(0xFF59DE8B),
                Color(0xFF00B9A2),
                Color(0xFF018EA1),
                Color(0xFF00658A),
            ),
            chartCenterValue = "256.000 ₽"
        )
    }
}


@Composable
fun Balance(currentBalance : Float = 0f, startPadding: Dp){
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(0.95f)
            .clip(RoundedCornerShape(startPadding))
            .background(color = Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = 20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.wallet),
                contentDescription = "wallet",
                tint = colorResource(id = R.color.cash_green)
            )
            Column() {
                Text(
                    text = "$currentBalance ₽",
                    style = Typography.body1
                )
                Text(
                    text = "Баланс",
                    style = Typography.overline
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.cash_green),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(startPadding / 2f)
        ) {
            Text(
                text = "Пополнить баланс",
                style = Typography.caption
            )
        }
    }
}