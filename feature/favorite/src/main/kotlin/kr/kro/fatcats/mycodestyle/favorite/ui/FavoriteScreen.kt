package kr.kro.fatcats.mycodestyle.favorite.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kr.kro.fatcats.mycodestyle.common.rememberEventHandler
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.favorite.FavoriteViewModel
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteIntent
import kr.kro.fatcats.mycodestyle.favorite.state.FavoriteUiState

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
    LazyColumn {
        items(state.favoriteList) {
            BaseText(
                text = it.message
            )
        }
    }

}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteContents()
}
