package kr.kro.fatcats.mycodestyle.home.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.common.rememberEventHandler
import kr.kro.fatcats.mycodestyle.home.HomeViewModel
import kr.kro.fatcats.mycodestyle.home.state.HomeIntent
import kr.kro.fatcats.mycodestyle.home.state.HomeUiState
import kr.kro.fatcats.mycodestyle.home.unit.CategoryDropdown
import kr.kro.fatcats.mycodestyle.home.unit.Roulette

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
        Spacer(modifier = Modifier.height(16.dp))
        Roulette(displayItems = displayItems, handleIntent = handleIntent)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { handleIntent(HomeIntent.SpinRoulette) }) {}
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeContents()
}