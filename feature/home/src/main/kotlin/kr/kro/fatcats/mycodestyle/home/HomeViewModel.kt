package kr.kro.fatcats.mycodestyle.home

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kr.kro.fatcats.mycodestyle.domain.model.ExcuseUseCases
import kr.kro.fatcats.mycodestyle.home.state.DisplayItem
import kr.kro.fatcats.mycodestyle.home.state.HomeIntent
import kr.kro.fatcats.mycodestyle.home.state.HomeSideEffect
import kr.kro.fatcats.mycodestyle.home.state.HomeUiState
import kr.kro.fatcats.mycodestyle.ui.IntentAndStateReducerViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val excuseUseCases: ExcuseUseCases,
) : IntentAndStateReducerViewModel<HomeUiState , HomeIntent , HomeSideEffect>(HomeUiState()) {

    val TAG = "HomeViewModel"
    override suspend fun processIntent(intent: HomeIntent) {
        Log.d(TAG, "processIntent: $intent")
        when(intent){
            is HomeIntent.ChangeCategory -> { changeCategory(intent) }
            is HomeIntent.SpinRoulette -> {spinRoulette()}
            is HomeIntent.ChangeFavorite -> {changeFavorite(intent.id)}
        }
    }

    private suspend fun changeCategory(intent : HomeIntent.ChangeCategory){
        val currentState = state.value.selectedCategory
        if(currentState == intent.category) return
        executeWithLoading{
            reduceState{ copy(selectedCategory = intent.category , displayItem = DisplayItem()) }
        }
    }

    private suspend fun spinRoulette(){
        reduceState{ copy(displayItem = displayItem.copy(isSpin = true)) }
        spinPlay()
    }

    private suspend fun spinPlay(){
        var delay = 50L
        val selectList = excuseUseCases.getByCategory(state.value.selectedCategory)?:emptyList()
        while(state.value.displayItem.isSpin){
            val item = selectList.randomOrNull().also {
                Log.d(TAG, "spinPlay: item $it")
            }
            reduceState{ copy(displayItem = displayItem.copy(isSpin = true ,setItems = item)) }
            delay(delay)
            if(delay < 200L) {
                delay += 5L
            }else{
                delay += 50L
            }
            if(delay > 500L) {
                reduceState{ copy(displayItem = displayItem.copy(isSpin = false)) }
            }
        }
    }

    private suspend fun changeFavorite(id: Int) {

        executeWithLoading{
            val result = excuseUseCases.setFavorite.toggleFavorite(id)
            reduceState {
                val updatedSetItem = displayItem.setItems?.copy(isFavorite = result)
                copy(displayItem = displayItem.copy(setItems = updatedSetItem))
            }
        }
    }



}