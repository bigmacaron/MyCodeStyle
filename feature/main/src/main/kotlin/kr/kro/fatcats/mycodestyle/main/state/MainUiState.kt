package kr.kro.fatcats.mycodestyle.main.state

import kr.kro.fatcats.mycodestyle.model.state.LoadingState

data class MainUiState(
    override val isLoading: Boolean = false,
): LoadingState {
    override fun copyWithLoading(isLoading: Boolean): LoadingState {
        return copy(isLoading = isLoading)
    }

    val initialState = MainUiState()
}