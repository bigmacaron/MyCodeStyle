package kr.kro.fatcats.mycodestyle.model.state

interface LoadingState {
    val isLoading: Boolean
    fun copyWithLoading(isLoading: Boolean): LoadingState
}