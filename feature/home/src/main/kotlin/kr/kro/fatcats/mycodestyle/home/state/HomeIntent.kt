package kr.kro.fatcats.mycodestyle.home.state

import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory

sealed class HomeIntent {
    data class ChangeCategory(val category: ExcuseCategory) : HomeIntent()
    object SpinRoulette : HomeIntent()
    data class ChangeFavorite(val id : Int) : HomeIntent()
}