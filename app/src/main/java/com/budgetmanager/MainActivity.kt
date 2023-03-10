package com.budgetmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
                BudgetManagerComposable()
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
                backgroundColor = colorResource(id = R.color.super_light_gray),
                unselectedContentColor = Color.DarkGray,
                selectedContentColor = colorResource(id = R.color.cash_green),
                navTabs = listOf(
                    NavItem(
                        title = Screen.Home.title,
                        route = Screen.Home.route,
                        icon =  ImageVector.vectorResource(id = R.drawable.home),
                    ),
                    NavItem(
                        title = Screen.Obligations.title,
                        route = Screen.Obligations.route,
                        icon =  ImageVector.vectorResource(id = R.drawable.calendar),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Column() {
            Navigation(navController = navController, scaffoldState = scaffoldState)
        }
    }
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    unselectedContentColor: Color,
    selectedContentColor: Color,
    navTabs: List<NavItem>,
    navController: NavController,
    onItemClick: (NavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    val iconHeight = 34.dp

    BottomNavigation(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(25.dp))
            .clip(shape = RoundedCornerShape(25.dp))
            .height(iconHeight * 2f),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        navTabs.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,

                icon = {
                    var iconSize by remember { mutableStateOf(Size.Zero) }
                    val dividerThickness = 3.dp
                    val iconPadding = dividerThickness * 3

                    val animatedPadding by animateDpAsState(
                        targetValue =  if (!selected) 0.dp else iconPadding,
                        animationSpec = tween(durationMillis = 200, easing = EaseInOutQuad)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            modifier = Modifier
                                .onGloballyPositioned { coordinates ->
                                    iconSize = coordinates.size.toSize()
                                }
                                .padding(bottom = animatedPadding),
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                        if (selected) {
                            Box(
                                modifier = Modifier
                                    .height(dividerThickness)
                                    .width(with(LocalDensity.current) { iconSize.width.toDp() * 1.2f })
                                    .background(
                                        color = colorResource(id = R.color.cash_green),
                                        shape = RoundedCornerShape(dividerThickness / 2)
                                    )
                            )
                        }
                    }
                }
            )
        }
    }
}

