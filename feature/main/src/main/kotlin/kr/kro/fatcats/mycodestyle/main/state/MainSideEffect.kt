package kr.kro.fatcats.mycodestyle.main.state

sealed class MainSideEffect {
    data class ShowToast (val message: String) : MainSideEffect()
}
