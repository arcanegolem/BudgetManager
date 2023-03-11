package com.budgetmanager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
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
import com.budgetmanager.ui.theme.Typography
import com.budgetmanager.R
import com.budgetmanager.objects.FinancialRule

@Composable
fun RulesScreen(navController: NavController){
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
            .padding(horizontal = 20.dp)
    ){
        val startPadding = 25.dp
        val shadowElevation = 10.dp

        item {
            Spacer(modifier = Modifier.padding(30.dp))
            
            RulesHeader(
                totalSum = 120000f,
                startPadding = startPadding,
                shadowElevation = shadowElevation
            )

            FinancialRules(
                rules = listOf(
                    FinancialRule(
                        ruleName = "Авто",
                        ruleIcon = ImageVector.vectorResource(id = R.drawable.auto_transport),
                        ruleColor = Color.Blue,
                        ruleDescription = "Авто",
                        maxAmount = 69000f,
                        currentAmount = 61070f,
                    ),
                    FinancialRule(
                        ruleName = "Образование",
                        ruleIcon = ImageVector.vectorResource(id = R.drawable.education),
                        ruleColor = Color.Magenta,
                        ruleDescription = "Образование",
                        maxAmount = 15000f,
                        currentAmount = 3400f,
                    ),
                    FinancialRule(
                        ruleName = "Рестораны",
                        ruleIcon = ImageVector.vectorResource(id = R.drawable.food),
                        ruleColor = Color.Red,
                        ruleDescription = "Рестораны",
                        maxAmount = 10000f,
                        currentAmount = 5350f,
                    ),
                    FinancialRule(
                        ruleName = "Шоппинг",
                        ruleIcon = ImageVector.vectorResource(id = R.drawable.shopping),
                        ruleColor = Color.Green,
                        ruleDescription = "Шоппинг",
                        maxAmount = 50000f,
                        currentAmount = 23480f,
                    ),
                ),
                startPadding = startPadding,
                shadowElevation = shadowElevation
            )
        }
    }
}


@Composable
fun RulesHeader(
    totalSum : Float,
    startPadding : Dp,
    shadowElevation : Dp
){
    val clipShape = RoundedCornerShape(startPadding)

    var colSize by remember { mutableStateOf(Size.Zero) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = shadowElevation, shape = clipShape)
            .clip(shape = clipShape)
            .background(color = Color.White)
            .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    colSize = coordinates.size.toSize()
                }
        ) {
            Text(
                text = "Общая сумма",
                style = Typography.overline
            )

            Text(
                text = "$totalSum ₽",
                style = Typography.h2
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier
                    .height(with(LocalDensity.current) { colSize.height.toDp() })
                    .width(with(LocalDensity.current) { colSize.height.toDp() })
                    .background(
                        color = colorResource(id = R.color.cash_green),
                        shape = CircleShape
                    ),
                imageVector = Icons.Rounded.Add,
                contentDescription = "add category",
                tint = Color.White
            )
        }
    }
}


@Composable
fun FinancialRules(
    rules : List<FinancialRule>,
    startPadding: Dp,
    shadowElevation: Dp
){
    Text(
        modifier = Modifier.padding(
            start = startPadding / 1.5f,
            top = 30.dp,
            bottom = 10.dp
        ),
        text = "Правила",
        style = Typography.h1
    )

    Column() {
        rules.forEachIndexed { index, financialRule ->
            financialRule.RulePreview(
                clipShape = RoundedCornerShape(startPadding),
                shadowElevation = shadowElevation,
                bottomPadding = if (index == rules.size - 1) 110.dp else 10.dp
            )
        }
    }
}