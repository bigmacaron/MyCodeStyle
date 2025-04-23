package kr.kro.fatcats.mycodestyle.domain.model

import kr.kro.fatcats.mycodestyle.domain.excuse.GetExcuseByCategoryUseCase
import kr.kro.fatcats.mycodestyle.domain.excuse.GetExcuseFavoriteUseCase
import kr.kro.fatcats.mycodestyle.domain.excuse.SetExcuseFavoriteUseCase

data class ExcuseUseCases(
    val getByCategory: GetExcuseByCategoryUseCase,
    val getFavorites: GetExcuseFavoriteUseCase,
    val setFavorite: SetExcuseFavoriteUseCase,
)