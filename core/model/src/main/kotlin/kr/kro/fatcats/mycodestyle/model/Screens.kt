package kr.kro.fatcats.mycodestyle.model

open class Screens(
    val link: String,
    val route: String,
    open val title: String = "Default Title",
) {
    val routePrefix: String
        get() = route.split("/").first()
}

sealed class Screen(
    link: String = "",
    route: String,
    title: String,
) : Screens(
    link = link,
    route = route,
    title = title
) {
    data object Home : Screen(
        route = "Home",
        title = "홈"
    )
    data object Text : Screen(
        route = "Text",
        title = "텍스트"
    )
}