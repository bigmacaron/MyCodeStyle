package kr.kro.fatcats.mycodestyle.home.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.common.rememberEventHandler
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.button.ImageButton
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.PreviewColor
import kr.kro.fatcats.mycodestyle.home.HomeViewModel
import kr.kro.fatcats.mycodestyle.home.state.HomeIntent
import kr.kro.fatcats.mycodestyle.home.state.HomeUiState
import kr.kro.fatcats.mycodestyle.home.unit.CategoryDropdown
import kr.kro.fatcats.mycodestyle.home.unit.Roulette
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun HomeScreen() {
    val (handleIntent, state) = rememberEventHandler<HomeViewModel, HomeIntent, HomeUiState>(
        getState = { it.state },
        handleIntent = { vm, intent -> vm.handleIntent(intent) }
    )
    HomeContents(handleIntent , state)
}

@Composable
fun HomeContents(
    handleIntent: (HomeIntent) -> Unit = {},
    state: HomeUiState = HomeUiState()
) {

    val selectedCategory by remember(state) {
        derivedStateOf { state.selectedCategory }
    }

    val displayItems by remember(state) {
        derivedStateOf { state.displayItem }
    }
    SideEffect {
        Log.d("HomeScreen", "HomeContents: $state")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CategoryDropdown(
            selected = selectedCategory,
            onSelect = { category -> handleIntent(HomeIntent.ChangeCategory(category)) }
        )
        Spacer(modifier = Modifier.weight(1f))
        Roulette(displayItems = displayItems, handleIntent = handleIntent)
        Spacer(modifier = Modifier.weight(0.7f))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(70.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, AppColor.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            ImageButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .wrapContentSize()
                ,
                iconVector = AppIcon.Common.TouchApp,
                imageOptions = ImageOptions(
                    colorFilter = ColorFilter.tint(AppColor.Black)
                ),
                onClick = { handleIntent(HomeIntent.SpinRoulette) }
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true, backgroundColor = PreviewColor.White)
@Composable
private fun HomeScreenPreview() {
    HomeContents()
}