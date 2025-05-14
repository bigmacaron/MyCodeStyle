package kr.kro.fatcats.mycodestyle.home.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.component.unit.DropDownSet
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.model.enums.ExcuseCategory

@Composable
fun CategoryDropdown(
    selected: ExcuseCategory,
    onSelect: (ExcuseCategory) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        DropDownSet(
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            text = selected.s
        ) { expanded = true }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            DropdownMenu(
                modifier = Modifier.background(AppColor.Primary),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ExcuseCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            modifier = Modifier.padding(top = 10.dp),
                            text = { BaseText(text = category.s, style = AppTypography.Body_1M) },
                            onClick = {
                                expanded = false
                                onSelect(category)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryDropdownPreview() {
    CategoryDropdown(ExcuseCategory.ALL ){

    }
}

