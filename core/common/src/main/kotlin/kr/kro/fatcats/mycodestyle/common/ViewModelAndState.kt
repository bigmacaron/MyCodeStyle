package kr.kro.fatcats.mycodestyle.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
inline fun <reified VM : ViewModel, I, S> rememberEventHandler(
    noinline getState: (VM) -> StateFlow<S>,
    noinline handleIntent: suspend (VM, I) -> Unit
): Pair<(I) -> Unit, S> {
    val viewModel: VM = hiltViewModel()
    val scope = rememberCoroutineScope()
    val state = getState(viewModel).collectAsState().value

    val handler: (I) -> Unit = remember(viewModel) {
        { intent -> scope.launch { handleIntent(viewModel, intent) } }
    }

    return handler to state
}