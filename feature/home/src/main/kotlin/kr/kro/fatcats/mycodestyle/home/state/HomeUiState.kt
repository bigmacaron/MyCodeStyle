package kr.kro.fatcats.mycodestyle.home.state

import kr.kro.fatcats.mycodestyle.model.ExcuseItems
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory
import kr.kro.fatcats.mycodestyle.model.state.LoadingState

data class HomeUiState(
    override val isLoading: Boolean = false,
    val selectedCategory: ExcuseCategory = ExcuseCategory.ALL,
    val displayItem: DisplayItem = DisplayItem(),
): LoadingState {
    override fun copyWithLoading(isLoading: Boolean): LoadingState {
        return copy(isLoading = isLoading)
    }
}

data class DisplayItem(
    val isSpin : Boolean = false,
    val setItems : ExcuseItems? = null,
    val items : List<ExcuseItems> = emptyList(),
)