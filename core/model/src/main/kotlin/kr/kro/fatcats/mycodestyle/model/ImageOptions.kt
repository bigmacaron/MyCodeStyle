package kr.kro.fatcats.mycodestyle.model

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale

data class ImageOptions(
    val colorFilter: ColorFilter? = null,
    val contentScale: ContentScale = ContentScale.Fit,
)