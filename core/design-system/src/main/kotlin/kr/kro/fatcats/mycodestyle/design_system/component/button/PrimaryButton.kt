package kr.kro.fatcats.mycodestyle.design_system.component.button

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.component.base.PrimaryButtonChip
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier.height(32.dp),
    color: Color = AppColor.Primary,
    style : TextStyle = AppTypography.Label_1M,
    text: String,
    backGroundShapeSize: Dp = 4.dp,
    textAlign: TextAlign = TextAlign.Start,
    isDisable: Boolean = false,
    onClick: () -> Unit
){
    PrimaryButtonChip(
        modifier = modifier,
        color = color,
        text = text,
        backGroundShapeSize = backGroundShapeSize,
        textAlign = textAlign,
        isDisable = isDisable,
        onClick = onClick,
        style = style
    )
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(text = "버튼"){}
}