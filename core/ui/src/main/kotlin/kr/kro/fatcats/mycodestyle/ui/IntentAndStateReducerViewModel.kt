package kr.kro.fatcats.mycodestyle.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kr.kro.fatcats.mycodestyle.model.state.LoadingState

abstract class IntentAndStateReducerViewModel<State, Intent, SideEffect>(
    initialState: State
) : StateReducerViewModel<State, SideEffect>(initialState) where State : LoadingState {

    private val intentChannel = Channel<Intent>(Channel.UNLIMITED)

    init {
        processIntents()
    }

    fun handleIntent(intent: Intent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    abstract suspend fun processIntent(intent: Intent)

    private fun processIntents() {
        intentChannel.receiveAsFlow()
            .onEach { processIntent(it) }
            .launchIn(viewModelScope)
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun setLoadingState(isLoading: Boolean) {
        reduceState {
            copyWithLoading(isLoading) as State
        }
    }

    suspend fun <T> executeWithLoading(action: suspend () -> T): T {
        try { setLoadingState(true)
            return action.invoke()
        } finally {
            setLoadingState(false)
        }
    }

}