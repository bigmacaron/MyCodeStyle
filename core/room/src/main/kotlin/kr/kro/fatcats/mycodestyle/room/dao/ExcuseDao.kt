package kr.kro.fatcats.mycodestyle.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory
import kr.kro.fatcats.mycodestyle.room.entitys.ExcuseEntity

@Dao
interface ExcuseDao {

    @Query("SELECT * FROM excuse")
    fun getAll(): Flow<List<ExcuseEntity>>

//    @Query("SELECT * FROM excuse WHERE is_favorite = 1")
//    suspend fun getFavorites(): List<ExcuseEntity>

    @Query("SELECT * FROM excuse WHERE id = :id")
    suspend fun getById(id: Int ): ExcuseEntity

    @Query("SELECT * FROM excuse WHERE is_favorite = :isFavorite")
    fun getFavorites(isFavorite: Boolean = true): Flow<List<ExcuseEntity>>

    @Query("UPDATE excuse SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM excuse WHERE category = :category")
    fun getByCategory(category: ExcuseCategory): Flow<List<ExcuseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(excuses: List<ExcuseEntity>)

    @Update
    suspend fun update(excuse: ExcuseEntity)
}
