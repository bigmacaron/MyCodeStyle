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
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseBorderTextButton
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography
import kr.kro.fatcats.mycodestyle.design_system.theme.FontColor

@Composable
fun BorderButton(
    modifier: Modifier = Modifier.height(32.dp),
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = FontColor.Black,
    style : TextStyle = AppTypography.Label_1M,
    backGroundColor: Color =  AppColor.White,
    backGroundShapeSize: Dp = 4.dp,
    borderColor: Color = AppColor.Gray,
    borderShapeSize: Dp = 4.dp,
    onClick: () -> Unit,
){
    BaseBorderTextButton(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = style,
        backGroundColor = backGroundColor,
        backGroundShapeSize = backGroundShapeSize,
        borderColor = borderColor,
        borderShapeSize = borderShapeSize,
        onClick = onClick
    )
}

@Preview
@Composable
private fun BorderButtonPreview() {
    BorderButton(text = "버튼"){}
}