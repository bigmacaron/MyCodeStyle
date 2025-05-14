package kr.kro.fatcats.mycodestyle.design_system.component.unit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseText
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun DropDownSet(
    modifier: Modifier = Modifier,
    text : String,
    filterEvent : () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        BaseText(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = AppTypography.Heading_3Sb
        )
        BaseImage(
            modifier = Modifier.size(50.dp).align(Alignment.CenterEnd).clickable { filterEvent.invoke() },
            iconVector = AppIcon.Common.ArrowDropDown,
            imageOptions = ImageOptions(
                colorFilter = ColorFilter.tint(AppColor.Black)
            )
        )
    }
}

@Preview
@Composable
private fun DropDownSetPreview() {
    DropDownSet(text = "전체"){}
}

