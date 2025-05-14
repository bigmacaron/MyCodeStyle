package kr.kro.fatcats.mycodestyle.domain.excuse

import kotlinx.coroutines.flow.Flow
import kr.kro.fatcats.mycodestyle.data.room.ExcuseRepository
import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetExcuseByCategoryUseCase @Inject constructor(
    private val excuseRepository: ExcuseRepository,
){
    suspend operator fun invoke(category: ExcuseCategory) :  Flow<List<ExcuseItems>>? = excuseRepository.getByCategory(category)
}