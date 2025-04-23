package kr.kro.fatcats.mycodestyle.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.kro.fatcats.mycodestyle.domain.excuse.GetExcuseByCategoryUseCase
import kr.kro.fatcats.mycodestyle.domain.excuse.GetExcuseFavoriteUseCase
import kr.kro.fatcats.mycodestyle.domain.excuse.SetExcuseFavoriteUseCase
import kr.kro.fatcats.mycodestyle.domain.model.ExcuseUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideExcuseUseCases(
        getByCategory: GetExcuseByCategoryUseCase,
        getFavorites: GetExcuseFavoriteUseCase,
        setFavorite: SetExcuseFavoriteUseCase,
    ): ExcuseUseCases {
        return ExcuseUseCases(
            getByCategory = getByCategory,
            getFavorites = getFavorites,
            setFavorite = setFavorite
        )
    }
}
