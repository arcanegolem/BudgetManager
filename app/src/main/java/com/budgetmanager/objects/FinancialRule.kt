package com.budgetmanager.objects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.budgetmanager.R
import com.budgetmanager.ui.theme.Typography

class FinancialRule(
    val ruleName : String,
    val maxAmount : Float,
    val currentAmount : Float,
    val ruleIcon : ImageVector,
    val ruleColor : Color,
    val ruleDescription : String
) {
    @Composable
    fun RulePreview(
        clipShape : Shape,
        shadowElevation : Dp,
        bottomPadding : Dp
    ){
        Row(
            modifier = Modifier
                .padding(bottom = bottomPadding)
                .shadow(elevation = shadowElevation, shape = clipShape)
                .clip(clipShape)
                .background(color = Color.White)
                .padding(start = 15.dp, end = 5.dp, top = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .height(45.dp)
                    .width(45.dp)
                    .background(color = ruleColor, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    imageVector = ruleIcon,
                    contentDescription = ruleDescription,
                    tint = Color.White
                )
            }
            
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = ruleName,
                        style = Typography.caption
                    )

                    Text(
                        text = "$maxAmount ₽",
                        style = Typography.caption
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ещё ${maxAmount - currentAmount} ₽",
                        style = Typography.subtitle2,
                        color = colorResource(id = R.color.cash_green)
                    )

                    Text(
                        text = "$currentAmount из $maxAmount",
                        style = Typography.subtitle2
                    )
                }
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.padding(start = 5.dp),
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = ruleDescription
                )
            }
        }
    }
}