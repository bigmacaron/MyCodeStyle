package kr.kro.fatcats.mycodestyle.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.design_system.theme.MainTheme
import kr.kro.fatcats.mycodestyle.favorite.ui.FavoriteScreen
import kr.kro.fatcats.mycodestyle.home.ui.HomeScreen
import kr.kro.fatcats.mycodestyle.model.ImageOptions
import kr.kro.fatcats.mycodestyle.model.Screen

@Composable
fun App(mainViewModel : MainViewModel) {
    AppScreen(mainViewModel)
}

// AppScreen
@Composable
fun AppScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    MainTheme {
        Scaffold(
            bottomBar = {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                NavigationBar {
                    listOf(Screen.Home, Screen.Favorite).forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                BaseImage(
                                    iconVector = getNavIcon(screen) ,
                                    imageOptions = ImageOptions(colorFilter = ColorFilter.tint(AppColor.Black))
                                )
                            },
                            label = {
                                BaseText(
                                    text = screen.title ,
                                    style = AppTypography.Label_2M.copy(color = AppColor.Black)
                                )
                            },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MainNavGraph(navController = navController)
            }
        }
    }
}


@Composable
fun MainNavGraph(navController : NavHostController) {

    val defaultRoute = "Home"
    NavHost(
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