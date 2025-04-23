package kr.kro.fatcats.mycodestyle.room.converters

import androidx.room.TypeConverter
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory

class Converters {

    @TypeConverter
    fun fromCategory(category: ExcuseCategory): String =
        category.name

    @TypeConverter
    fun toCategory(value: String): ExcuseCategory =
        ExcuseCategory.valueOf(value)

}