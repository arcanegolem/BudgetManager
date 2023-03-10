package com.budgetmanager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.budgetmanager.objects.Obligation
import com.budgetmanager.charts.PieChart
import com.budgetmanager.ui.theme.Typography
import com.budgetmanager.R

@Composable
fun HomeScreen(navController: NavController){
    LazyColumn(
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
//            .padding(horizontal = 10.dp)
    ) {
        val startPadding = 25.dp
        val shadowElevation = 10.dp

        item {
            Text(
                modifier = Modifier.padding(
                    start = startPadding / 1.5f,
                    top = 30.dp,
                    bottom = 10.dp
                ),
                text = "Мои финансы",
                style = Typography.h1
            )

            ChartInstance(
                modifier = Modifier.padding(horizontal = 10.dp),
                startPadding = startPadding,
                chartRadius = 140.dp,
                shadowElevation = shadowElevation
            )

            Balance(
                modifier = Modifier.padding(horizontal = 10.dp),
                startPadding = startPadding,
                shadowElevation = shadowElevation
            )

            Recommendations(
                modifier = Modifier.padding(horizontal = 10.dp),
                monthlySum = 28170f,
                dailySum = 5499f,
                startPadding = startPadding,
                shadowElevation = shadowElevation
            )

            ObligationsThumbnail(
                startPadding = startPadding,
                obligations = listOf(
                    Obligation(
                        dueDate = 20,
                        name = "Оплата кредита",
                        sum = 12000f,
                        color = Color.Red
                    ),
                    Obligation(
                        dueDate = 30,
                        name = "Налоги",
                        sum = 15000f,
                        color = Color.Blue
                    )
                ),
                shadowElevation = shadowElevation
            )
        }
    }
}


@Composable
fun ChartInstance(
    modifier: Modifier,
    startPadding : Dp,
    chartRadius: Dp,
    shadowElevation: Dp
){
    val clipShape = RoundedCornerShape(
        topStart = startPadding,
        topEnd = chartRadius / 1.5f,
        bottomStart = startPadding,
        bottomEnd = chartRadius / 1.5f
    )

    Row(
        modifier = modifier
            .height(chartRadius * 1.35f)
            .shadow(
                elevation = shadowElevation,
                shape = clipShape
            )
            .clip( shape = clipShape )
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
fun Balance(
    modifier: Modifier,
    currentBalance : Float = 0f,
    startPadding : Dp,
    shadowElevation : Dp
){
    Row(
        modifier = modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(0.95f)
            .shadow(elevation = shadowElevation, shape = RoundedCornerShape(startPadding))
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
                modifier = Modifier.padding(end = 10.dp),
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
                    style = Typography.overline,
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


@Composable
fun Recommendations(
    modifier: Modifier,
    monthlySum : Float,
    dailySum : Float,
    startPadding : Dp,
    shadowElevation: Dp
){
    val dividerThickness = 2.dp
    val subdividePadding = 5.dp

    Row(
        modifier = modifier
            .shadow(elevation = shadowElevation, shape = RoundedCornerShape(startPadding))
            .clip(RoundedCornerShape(startPadding))
            .background(
                color = Color.White
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            var headerSize by remember { mutableStateOf(Size.Zero) }

            Text(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        headerSize = coordinates.size.toSize()
                    }
                    .padding(bottom = subdividePadding),
                text = "Рекомендуемая сумма:",
                style = Typography.body1
            )
            Divider(
                modifier = Modifier.width(with (LocalDensity.current) { headerSize.width.toDp() }),
                color = Color.LightGray,
                thickness = dividerThickness
            )
            Row (modifier = Modifier.padding(vertical = subdividePadding)) {
                var subColSize by remember { mutableStateOf(Size.Zero) }

                Column(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            subColSize = coordinates.size.toSize()
                        }
                        .padding(end = subdividePadding)
                ) {
                    Text(
                        text = "месяц",
                        style = Typography.caption
                    )
                    Text(
                        text = "$monthlySum ₽",
                        style = Typography.body1
                    )
                }

                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(with(LocalDensity.current) { subColSize.height.toDp() })
                        .width(dividerThickness)
                )

                Column(
                    modifier = Modifier.padding(start = subdividePadding)
                ) {
                    Text(
                        text = "день",
                        style = Typography.caption
                    )
                    Text(
                        text = "$dailySum ₽",
                        style = Typography.body1
                    )
                }
            }
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.super_light_gray)),
                imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
                contentDescription = "see more budget",
                tint = Color.LightGray
            )
        }
    }
}


@Composable
fun ObligationsThumbnail(
    startPadding : Dp,
    obligations : List<Obligation>,
    shadowElevation : Dp
){
    val currentDate = 8

    Text(
        modifier = Modifier.padding(
            start = startPadding / 1.5f,
            top = 30.dp,
            bottom = 10.dp
        ),
        text = "Мои обязательства",
        style = Typography.h1
    )

    LazyRow(
        modifier = Modifier.padding(bottom = 110.dp)
    ){
        items(obligations){ obligation ->
            obligation.ObligationPreview(startPadding = startPadding, currentDate = currentDate, shadowElevation)
        }
    }
}