package kr.kro.fatcats.mycodestyle.design_system.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.FontColor

@Composable
fun PrimaryButtonChip(
    modifier: Modifier,
    color : Color ,
    text : String,
    backGroundShapeSize : Dp ,
    textAlign: TextAlign ,
    isDisable: Boolean,
    style : TextStyle ,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .background(
                color = if(!isDisable) color else AppColor.Gray,
                shape = RoundedCornerShape(size = backGroundShapeSize)
            )
            .padding(start = 20.dp, end = 20.dp)
            .clickable {
                if(!isDisable) onClick()
            },
        contentAlignment = Alignment.Center
    ){
        BaseText(
            text = text,
            style = style.copy(
                color = FontColor.Primary
            ),
            textAlign = textAlign,
        )
    }
}