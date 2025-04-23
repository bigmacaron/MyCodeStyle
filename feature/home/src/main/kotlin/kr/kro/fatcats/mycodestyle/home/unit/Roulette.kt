package kr.kro.fatcats.mycodestyle.home.unit

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.AppString
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.home.state.DisplayItem
import kr.kro.fatcats.mycodestyle.home.state.HomeIntent
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun Roulette(
    modifier: Modifier = Modifier,
    displayItems: DisplayItem,
    handleIntent: (HomeIntent) -> Unit
) {
    val defaultText = AppString.Common.SelectedDescription.invoke()

    // 현재 선택된 아이템 상태 추적
    val currentItem by rememberUpdatedState(displayItems.setItems)

    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, AppColor.Gray, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            modifier = Modifier.align(Alignment.Center),
            targetState = currentItem?.message,
            label = "roulette",
            transitionSpec = {
                slideInVertically { it } + fadeIn() togetherWith
                        slideOutVertically { -it } + fadeOut()
            }
        ) { message ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                //선택된 내용
                BaseText(
                    modifier = Modifier.align(Alignment.Center),
                    text = message ?: defaultText,
                    style = AppTypography.Body_1M,
                    textAlign = TextAlign.Center
                )

                //즐겨 찾기 버튼
                if (!displayItems.isSpin && currentItem?.message != null) {
                    BaseImage(
                        modifier = Modifier
                            .padding(20.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                currentItem?.id?.let {
                                    handleIntent.invoke(HomeIntent.ChangeFavorite(it))
                                }
                            },
                        iconVector = AppIcon.Common.favorite,
                        imageOptions = if (currentItem?.isFavorite == true) {
                            ImageOptions(colorFilter = ColorFilter.tint(AppColor.Yellow))
                        } else {
                            ImageOptions()
                        }
                    )
                }
            }
        }
    }
}