package com.budgetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.budgetmanager.charts.PieChartCenterCut
import com.budgetmanager.ui.theme.BudgetManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetManagerTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    PieChartCenterCut(
                        data = mapOf(
                            Pair("Sample-data-1", 21f),
                            Pair("Sample-data-2", 30f),
                            Pair("Sample-data-3", 10f),
                            Pair("Sample-data-4", 48f),
                            Pair("Sample-data-5", 100f),
                            Pair("Sample-data-6", 58f)
                        ),
                        colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Magenta, Color.Yellow, Color.Cyan)
                    )
                }
            }
        }
    }
}
