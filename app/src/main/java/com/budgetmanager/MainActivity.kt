package com.budgetmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.budgetmanager.charts.PieChartCenterCut
import com.budgetmanager.navigation.NavItem
import com.budgetmanager.navigation.Navigation
import com.budgetmanager.screensbase.Screen
import com.budgetmanager.ui.theme.BudgetManagerTheme


// DEV
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


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BudgetManagerComposable(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier,
                backgroundColor = Color.Blue,
                unselectedContentColor = Color.White,
                selectedContentColor = Color.White,
                navTabs = listOf(
                    NavItem(
                        title = Screen.Home.title,
                        route = Screen.Home.route,
                        icon =  Icons.Rounded.Home,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController = navController, scaffoldState = scaffoldState)
        }
    }
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    backgroundColor: Color,
    unselectedContentColor: Color,
    selectedContentColor: Color,
    navTabs: List<NavItem>,
    navController: NavController,
    onItemClick: (NavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier.clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
        backgroundColor = backgroundColor,
        elevation = 5.dp
    ) {
        navTabs.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,

                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                        if (selected) {
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

