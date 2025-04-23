package kr.kro.fatcats.mycodestyle.model.state

import kotlinx.coroutines.flow.StateFlow

interface UiState
interface UiIntent

interface IntentHandler<I : UiIntent, S : UiState> {
    val state: StateFlow<S>
    suspend fun handleIntent(intent: I)
}