package kr.kro.fatcats.mycodestyle.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.button.NavButton
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.MainTheme
import kr.kro.fatcats.mycodestyle.favorite.ui.FavoriteScreen
import kr.kro.fatcats.mycodestyle.home.ui.HomeScreen
import kr.kro.fatcats.mycodestyle.model.Screen

@Composable
fun App(mainViewModel : MainViewModel) {
    AppScreen(mainViewModel)
}

// AppScreen
@Composable
fun AppScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val tabHistory = remember { mutableStateListOf<String>() }
    MainTheme {
        Scaffold(
            modifier = Modifier.background(brush = AppColor.Brush.BackGround),
            containerColor = AppColor.Transparent,
            bottomBar = {
                CustomBottomNavigation(
                    navController = navController,
                    tabHistory = tabHistory
                )
            }
        ) { innerPadding ->
            MainNavGraph(modifier = Modifier.padding(innerPadding), navController = navController)
        }
    }
}

@Composable
fun MainNavGraph(modifier : Modifier = Modifier, navController : NavHostController) {
    val defaultRoute = "Home"
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = defaultRoute,
    ){
        composable(Screen.Home.route){
            HomeScreen()
        }
        composable(Screen.Favorite.route){
            FavoriteScreen()
        }
    }
}

@Composable
fun getNavIcon(screen: Screen) : AppIcon.DrawableWithText {
    return when(screen){
        Screen.Home -> AppIcon.Nav.NavHome24
        Screen.Favorite -> AppIcon.Nav.NavStar24
    }
}

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    tabHistory: MutableList<String>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screens = listOf(Screen.Home, Screen.Favorite)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 4.dp)
    ) {
        Surface(
            tonalElevation = 4.dp,
            shadowElevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().background(AppColor.WarmSandBeige),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEachIndexed { index , screen ->
                    val buttonHeight = 56
                    NavButton(
                        modifier = Modifier.weight(1f),
                        isEnabled = currentRoute == screen.route,
                        height = buttonHeight.dp,
                        iconWithText = getNavIcon(screen = screen),
                        text = screen.title,
                        onClick = {
                            navigateToRoute(
                                navController = navController,
                                route = screen.route,
                                currentRoute = currentRoute,
                                tabHistory = tabHistory,
                            )
                        }
                    )
                    if(index < screens.lastIndex){
                        Spacer(
                            Modifier
                                .width(1.dp)
                                .height((buttonHeight * 0.4).dp)
                                .background(AppColor.Black.copy(alpha = 0.05f))
                        )
                    }
                }
            }
        }
    }
}

fun navigateToRoute(
    navController: NavController,
    route: String,
    currentRoute: String?,
    tabHistory: MutableList<String>,
) {
    val routesplit =  route.split("/")
    val currntsplit = currentRoute?.split("/")
    if (routesplit.firstOrNull() == currntsplit?.firstOrNull()) {
        return
    }
    if (currentRoute != null && currentRoute != route) {
        tabHistory.add(currentRoute)
    }

    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            inclusive = true
            saveState = true
        }
//        launchSingleTop = routesplit.first() != Keypad.routePrefix // 동일한 경로로 중복되는 네비게이션 방지
        restoreState = true
    }
}