package kr.kro.fatcats.mycodestyle.model

import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory

data class ExcuseItems(
    val id : Int ,
    val category: ExcuseCategory,
    val message: String,
    val isFavorite: Boolean = false
)