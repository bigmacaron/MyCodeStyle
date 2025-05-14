package kr.kro.fatcats.mycodestyle.design_system.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconVector: AppIcon.DrawableWithText? = null,
    imagePainter: AppIcon.DrawableWithText? = null,
    imageOptions: ImageOptions = ImageOptions()
) {
    Box(
        modifier = modifier
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        iconVector?.let {
            BaseImage(
                iconVector = it,
                modifier = modifier,
                imageOptions = imageOptions.copy(
                    contentScale = ContentScale.None
                )
            )
        }?:run {
            imagePainter?.let {
                BaseImage(
                    imagePainter = it,
                    modifier = modifier,
                    imageOptions = imageOptions.copy(
                        contentScale = ContentScale.None
                    )
                )
            }
        }
    }
}