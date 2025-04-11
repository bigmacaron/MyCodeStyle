package kr.kro.fatcats.mycodestyle.design_system.component.base

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun BaseImage(
    modifier: Modifier = Modifier,
    iconVector: AppIcon.DrawableWithText? = null,
    imagePainter: AppIcon.DrawableWithText? = null,
    imageOptions : ImageOptions = ImageOptions()
){
    iconVector?.let {
        Image(
            imageVector = it(),
            contentDescription = it.text,
            modifier = modifier,
            colorFilter = imageOptions.colorFilter,
            contentScale = imageOptions.contentScale
        )
    }?:run{
        imagePainter?.let {
            Image(
                painter = it.painter,
                contentDescription = it.text,
                modifier = modifier,
                colorFilter = imageOptions.colorFilter,
                contentScale = imageOptions.contentScale
            )
        }
    }
}