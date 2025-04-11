package kr.kro.fatcats.mycodestyle.model

interface LoadingState {
    val isLoading: Boolean
    fun copyWithLoading(isLoading: Boolean): LoadingState
}