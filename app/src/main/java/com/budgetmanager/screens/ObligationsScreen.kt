package com.budgetmanager.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.budgetmanager.objects.Obligation
import com.budgetmanager.ui.theme.Typography


@Composable
fun ObligationsScreen (navController: NavController) {
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
            .padding(20.dp)
    ) {
       Card(
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(20.dp)),

       ) {
           Text(
               text = "Sample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\nSample text\n",
               modifier = Modifier.padding(10.dp)
           )
       }
        // Ряд с добавлением обязательств
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                "Добавить обязательство",
                modifier = Modifier.align(CenterVertically),
                style = Typography.subtitle1
            )
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.White)
                    .align(Alignment.CenterVertically),
                onClick = {}
            ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Icon for adding obligation"
                )
            }

        }
        // ObligationList()

        ObligationsList(
            startPadding = 20.dp,
            obligations = listOf(
                Obligation(
                    dueDate = 20,
                    name = "Оплата кредита",
                    sum = 12000f,
                    color = Color.Red
                ),
                Obligation(
                    dueDate = 22,
                    name = "Налоги",
                    sum = 2200f,
                    color = Color.Blue
                ),
                Obligation(
                    dueDate = 30,
                    name = "Квартплата",
                    sum = 15000f,
                    color = Color.Cyan
                )
            )
        )
    }
}

@Composable
fun ObligationsList(
    startPadding : Dp,
    obligations : List<Obligation>
){
    val currentDate = 8

    LazyColumn(
    ){
        items(obligations){ obligation ->
            obligation.ObligationCard(startPadding = startPadding, currentDate = currentDate)
        }
    }
}
