package kr.kro.fatcats.mycodestyle.favorite.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.component.unit.RouletteIcons
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.design_system.theme.FontColor
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteIntent
import kr.kro.fatcats.mycodestyle.model.ExcuseItems

@Composable
fun FavoriteListItem(
    modifier: Modifier = Modifier,
    excuseItems: ExcuseItems,
    handleIntent : (FavoriteIntent) -> Unit
) {
    val swipeThresholdFraction = 0.45f
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val thresholdPx = with(density) { (screenWidth * swipeThresholdFraction).toPx() }

    val event = remember {
        { id: Int -> handleIntent(FavoriteIntent.ChangeFavorite(id)) }
    }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                event.invoke(excuseItems.id)
                true
            } else {
                false
            }
        },
        positionalThreshold = { thresholdPx }
    )
    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            ListItemDismissBackground()
        },
        content = {
            FavoriteListContent(
                modifier = modifier.background(AppColor.White),
                excuseItem = excuseItems,
                event = { id -> handleIntent(FavoriteIntent.ChangeFavorite(id)) }
            )
        }
    )


}

@Composable
fun FavoriteListContent(
    modifier: Modifier = Modifier,
    excuseItem: ExcuseItems,
    event: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize().padding(vertical = 4.dp)
            .border(
                width = 1.dp,
                color = AppColor.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        BaseText(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterStart),
            text = excuseItem.message,
            style = AppTypography.Body_1M,
            textAlign = TextAlign.Center
        )
        RouletteIcons(
            modifier = Modifier.align(Alignment.CenterEnd),
            currentItem = excuseItem,
            event = event
        )
    }
}
@Composable
fun ListItemDismissBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize().padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColor.Red)
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        BaseText(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp),
            text = "삭제",
            style = AppTypography.Body_1B.copy(color = FontColor.Primary),
        )
    }
}

@Preview
@Composable
private fun FavoriteListItemPreview() {

}