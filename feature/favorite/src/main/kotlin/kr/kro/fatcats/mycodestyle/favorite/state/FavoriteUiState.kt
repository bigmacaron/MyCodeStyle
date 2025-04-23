package kr.kro.fatcats.mycodestyle.favorite.state

import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.state.LoadingState

data class FavoriteUiState(
    override val isLoading: Boolean = false,
    val favoriteList: List<ExcuseItems> = emptyList()
): LoadingState {

    override fun copyWithLoading(isLoading: Boolean): LoadingState {
        return copy(isLoading = isLoading)
    }

}