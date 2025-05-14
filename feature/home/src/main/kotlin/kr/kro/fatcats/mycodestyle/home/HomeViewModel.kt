package kr.kro.fatcats.mycodestyle.home

import android.util.Log
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.kro.fatcats.mycodestyle.domain.model.ExcuseUseCases
import kr.kro.fatcats.mycodestyle.home.state.DisplayItem
import kr.kro.fatcats.mycodestyle.home.state.HomeIntent
import kr.kro.fatcats.mycodestyle.home.state.HomeSideEffect
import kr.kro.fatcats.mycodestyle.home.state.HomeUiState
import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory
import kr.kro.fatcats.mycodestyle.ui.IntentAndStateReducerViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val excuseUseCases: ExcuseUseCases,
) : IntentAndStateReducerViewModel<HomeUiState , HomeIntent , HomeSideEffect>(HomeUiState()) {

    val TAG = "HomeViewModel"
    private var spinJob: Job? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    private val selectListFlow = snapshotFlow { state.value.selectedCategory }
        .distinctUntilChanged()
        .flatMapLatest { excuseUseCases.getByCategory(it) ?: flowOf(emptyList()) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        observerFavorite()
    }


    override suspend fun processIntent(intent: HomeIntent) {
        Log.d(TAG, "processIntent: $intent")
        when(intent){
            is HomeIntent.ChangeCategory -> { changeCategory(intent) }
            is HomeIntent.SpinRoulette -> {spinRoulette()}
            is HomeIntent.ChangeFavorite -> {changeFavorite(intent.id)}
        }
    }

    private fun observerFavorite(){
        viewModelScope.launch {
            excuseUseCases.getFavorites().collect { favList ->
                state.value.displayItem.setItems?.let { current ->
                    current.id
                    val updated = current.copy(
                        isFavorite = favList.any { it.id == current.id }
                    )
                    reduceState {
                        copy(
                            displayItem = displayItem.copy(
                                setItems = updated
                            )
                        )
                    }
                }
            }
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
        spinToggle()
    }

    suspend fun spinToggle() {
        spinCancel()
        spinJob = viewModelScope.launch {
            var delayTime = 50L
            val items = mutableListOf<ExcuseItems>()
            items.addAll(selectListFlow.first())
            while (state.value.displayItem.isSpin) {
                val item = items.randomOrNull().also { Log.d(TAG, "spinPlay: item $it") }
                reduceState {
                    copy(displayItem = displayItem.copy(isSpin = true, setItems = item))
                }
                items.removeIf { it == item }
                delay(delayTime)
                delayTime += if (delayTime < 200L) 5L else 50L
                if (delayTime > 500L) {
                    reduceState {
                        copy(displayItem = displayItem.copy(isSpin = false))
                    }
                }
            }
        }
    }
    suspend fun spinCancel() {
        if(spinJob?.isActive == true) {
            spinJob?.cancel()
            reduceState { copy(displayItem = displayItem.copy(isSpin = false)) }
            return
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