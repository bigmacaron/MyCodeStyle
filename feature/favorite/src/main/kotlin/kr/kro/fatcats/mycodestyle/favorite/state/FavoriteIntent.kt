package kr.kro.fatcats.mycodestyle.favorite.state

sealed class FavoriteIntent {
    data class ChangeFavorite(val id: Int) : FavoriteIntent()
//    object LoadFavorite : FavoriteIntent()
}