package kr.kro.fatcats.mycodestyle.domain.excuse

import kr.kro.fatcats.mycodestyle.data.room.ExcuseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetExcuseFavoriteUseCase @Inject constructor(
    private val excuseRepository: ExcuseRepository,
) {
    suspend fun toggleFavorite(id : Int) : Boolean {
        val excuse = excuseRepository.getById(id)
        excuse?.let {
            excuseRepository.setFavorite(id, !it.isFavorite)
        }
        return excuseRepository.getById(id)?.isFavorite == true
    }
    suspend operator fun invoke(id : Int, isFavorite: Boolean) = excuseRepository.setFavorite(id , isFavorite)
}