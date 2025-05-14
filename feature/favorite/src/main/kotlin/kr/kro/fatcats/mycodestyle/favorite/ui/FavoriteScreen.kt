package kr.kro.fatcats.mycodestyle.favorite.ui

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.kro.fatcats.mycodestyle.common.rememberEventHandler
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.design_system.theme.FontColor
import kr.kro.fatcats.mycodestyle.favorite.FavoriteViewModel
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteIntent
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteUiState
import kr.kro.fatcats.mycodestyle.favorite.unit.FavoriteListItem
import java.nio.file.Files.size

@Composable
fun FavoriteScreen() {

    val (handleIntent, state) = rememberEventHandler<FavoriteViewModel, FavoriteIntent, FavoriteUiState>(
        getState = { it.state },
        handleIntent = { vm, intent -> vm.handleIntent(intent) }
    )

    FavoriteContents(handleIntent,state)
}

@Composable
fun FavoriteContents(
    handleIntent: (FavoriteIntent) -> Unit = {},
    state: FavoriteUiState = FavoriteUiState()
) {
    Box{
        LazyColumn(
            modifier = Modifier.padding(20.dp)
        ) {
            items(
                items = state.favoriteList,
                key = { it.id }
            ) {
                FavoriteListItem(excuseItems = it, handleIntent = handleIntent)
            }
        }
        ListHintView(size = state.favoriteList.size)
    }
}

@Composable
fun ListHintView(size : Int) {
    when{
        size == 0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BaseText(
                    text = "즐겨 찾기에 추가한 아이템이 없습니다.",
                    textAlign = TextAlign.Center,
                    style = AppTypography.Default_Medium.copy(
                        fontSize = 14.sp,
                        color = FontColor.Gray
                    )
                )
            }
        }
        size < 5 ->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BaseText(
                    text = "<- 왼쪽으로 밀거나, 별모양 아이콘을 눌러 즐겨 찾기 해제",
                    textAlign = TextAlign.Center,
                    style = AppTypography.Default_Medium.copy(
                        fontSize = 14.sp,
                        color = FontColor.Gray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteContents()
}
