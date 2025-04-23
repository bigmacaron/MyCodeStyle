package kr.kro.fatcats.mycodestyle.room.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory

@Entity(tableName = "excuse")
data class ExcuseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "category")
    val category: ExcuseCategory,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)

fun ExcuseEntity.toExcuseItems(): ExcuseItems {
    return ExcuseItems(
        id = id,
        category = category,
        message = message,
        isFavorite = isFavorite
    )
}
