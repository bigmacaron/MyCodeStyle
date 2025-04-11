package kr.kro.fatcats.mycodestyle.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

abstract class StateReducerViewModel <State, SideEffect>(initialState: State) : ViewModel() {

    private val _reduceChannel = Channel<State.() -> State>()

    private val _sideEffectChannel = Channel<SideEffect>(
        capacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        onUndeliveredElement = {
            Log.d("StateReducer", "onUndeliveredElement $it")
        }
    )

    private val sideEffectFlow = _sideEffectChannel.receiveAsFlow()

    val state: StateFlow<State> = _reduceChannel.receiveAsFlow()
        .runningFold(initialState, ::reduce)
        .onEach { Log.d("StateReducer ", "state > $it") }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)

    protected suspend fun sendSideEffect(sideEffect: SideEffect) {
        _sideEffectChannel.send(sideEffect)
    }

    protected suspend fun reduceState(reduce: State.() -> State) {
        _reduceChannel.send(reduce)
    }

    private fun reduce(currentState: State, reduce: State.() -> State) = currentState.reduce()

}