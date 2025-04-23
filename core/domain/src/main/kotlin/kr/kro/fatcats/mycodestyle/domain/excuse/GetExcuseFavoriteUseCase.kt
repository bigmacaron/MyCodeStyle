package kr.kro.fatcats.mycodestyle.domain.excuse

import kr.kro.fatcats.mycodestyle.data.room.ExcuseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetExcuseFavoriteUseCase @Inject constructor(
    private val excuseRepository: ExcuseRepository,
){
    operator fun invoke() = excuseRepository.getFavorites()
}