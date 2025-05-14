package kr.kro.fatcats.mycodestyle.design_system.component.unit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun RouletteIcons(
    modifier: Modifier = Modifier,
    currentItem: ExcuseItems?,
    event : (Int) -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.width(4.dp))
        BaseImage(
            modifier = Modifier
                .clickable {
                    currentItem?.id?.let {
                        event.invoke(it)
                    }
                },
            iconVector = AppIcon.Common.favorite,
            imageOptions = if (currentItem?.isFavorite == true) {
                ImageOptions(colorFilter = ColorFilter.tint(AppColor.Yellow))
            } else {
                ImageOptions(colorFilter = ColorFilter.tint(AppColor.Gray))
            }
        )
        Spacer(Modifier.width(4.dp))
        currentItem?.message?.let {
            CopyToClipboardButton(it)
        }
        Spacer(Modifier.width(12.dp))
    }
}