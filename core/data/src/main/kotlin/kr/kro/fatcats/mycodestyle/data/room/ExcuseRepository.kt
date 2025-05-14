package kr.kro.fatcats.mycodestyle.data.room

import android.util.Log.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory
import kr.kro.fatcats.mycodestyle.room.dao.ExcuseDao
import kr.kro.fatcats.mycodestyle.room.entitys.toExcuseItems
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExcuseRepository @Inject constructor(
    private val excuseDao: ExcuseDao
){
    val TAG = "ExcuseRepository"

    suspend fun getAllExcuseData() = withContext(Dispatchers.IO) {
        runCatching {
            excuseDao.getAll().map { it.map { it.toExcuseItems() } }.flowOn(Dispatchers.IO)
        }.getOrElse { e-> e(TAG, "getAllExcuseData: failure $e " ) ; flowOf(emptyList()) }
    }

    suspend fun getById(id :Int) = withContext(Dispatchers.IO) {
        runCatching {
            excuseDao.getById(id).toExcuseItems()
        }.getOrElse { e-> e(TAG, "getById: failure $e " ) ; null }
    }

    fun getFavorites() = runCatching { excuseDao.getFavorites().map {
        list -> list.map { it.toExcuseItems() } }.flowOn(Dispatchers.IO)
    }.getOrElse { e-> e(TAG, "getByCategory: failure $e" ) ;  flowOf(emptyList()) }

    suspend fun getByCategory(category: ExcuseCategory) = runCatching {
        withContext(Dispatchers.IO) {
            if (category == ExcuseCategory.ALL) {
                getAllExcuseData()
            } else {
                excuseDao.getByCategory(category).map { it.map { it.toExcuseItems() } }.flowOn(Dispatchers.IO)
            }
        }
    }.getOrElse { e-> e(TAG, "getByCategory: failure $e" ) ;  flowOf(emptyList()) }

    suspend fun setFavorite(id : Int, isFavorite : Boolean) : Boolean = withContext(Dispatchers.IO) {
        runCatching { excuseDao.setFavorite(id, isFavorite) }.onFailure{ e-> e(TAG, "setFavorite: failure $e" ) }.isSuccess
    }

}