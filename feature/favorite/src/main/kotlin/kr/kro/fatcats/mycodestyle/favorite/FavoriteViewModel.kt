package kr.kro.fatcats.mycodestyle.favorite

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.kro.fatcats.mycodestyle.domain.model.ExcuseUseCases
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteIntent
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteSideEffect
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteUiState
import kr.kro.fatcats.mycodestyle.ui.IntentAndStateReducerViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val excuseUseCases: ExcuseUseCases,
) : IntentAndStateReducerViewModel<FavoriteUiState , FavoriteIntent , FavoriteSideEffect>(FavoriteUiState()) {

    init {
        loadFavorites()
    }
    private fun loadFavorites() {
        viewModelScope.launch {
            excuseUseCases.getFavorites().collect {
                reduceState {
                    copy(favoriteList = it)
                }
            }
        }
    }


    override suspend fun processIntent(intent: FavoriteIntent) {
    }

}