package kr.kro.fatcats.mycodestyle.design_system.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.design_system.theme.FontColor
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun NavButton(
    modifier: Modifier = Modifier,
    height: Dp = 56.dp,
    isEnabled: Boolean,
    text : String,
    iconWithText: AppIcon.DrawableWithText,
    onClick: () -> Unit,
) {
    val tint = if (isEnabled) FontColor.Primary else FontColor.Gray
    val appliedHeight  =if (height <= 56.dp) 56.dp else height
    Box(
        modifier = modifier
            .width(72.dp)
            .height(appliedHeight)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        ) {
            Box(modifier = Modifier) {
                BaseImage(
                    iconVector = iconWithText,
                    imageOptions = ImageOptions().copy(
                        colorFilter = tint?.let { ColorFilter.tint(it) }
                    ),
                    modifier = Modifier
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            BaseText(
                text = text,
                textAlign = TextAlign.Center,
                style = AppTypography.Default_Medium.copy(
                    fontSize = 10.sp,
                    color = if (isEnabled) FontColor.Primary.copy(alpha = 0.7f) else FontColor.Gray
                ),
                modifier = Modifier
                    .width(48.dp)
                    .height(16.dp)
            )
        }
    }
}