package com.budgetmanager.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.budgetmanager.ui.theme.Typography

class Obligation(
    val dueDate : Int,
    val name : String,
    val sum : Float,
    val color: Color
) {
    @Composable
    fun ObligationPreview(
        startPadding : Dp,
        currentDate: Int,
        shadowElevation : Dp
    ){
        Column(
            modifier = Modifier
                .padding(end = 20.dp)
                .width(170.dp)
                .height(220.dp)
                .shadow(elevation = shadowElevation, shape = RoundedCornerShape(startPadding))
                .clip(RoundedCornerShape(startPadding))
                .background(color = Color.White),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column{
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(3.dp),
                        text = "через ${dueDate - currentDate} дн.",
                        style = Typography.h6
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var textSize by remember { mutableStateOf(Size.Zero) }

                    Box(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .width(3.dp)
                            .clip(RoundedCornerShape((1.5).dp))
                            .height(with(LocalDensity.current) { textSize.height.toDp() })
                            .background(color)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .onGloballyPositioned { coordinates ->
                                textSize = coordinates.size.toSize()
                            },
                        text = name,
                        style = Typography.subtitle1
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp, top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Column() {
                        Text(
                            text = "Сумма:",
                            style = Typography.overline,
                        )
                        Text(
                            text = "$sum ₽"
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = startPadding, bottomEnd = startPadding))
                        .background(color),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "remove obligation",
                        tint = Color.White
                    )
                }
            }
        }
    }
}