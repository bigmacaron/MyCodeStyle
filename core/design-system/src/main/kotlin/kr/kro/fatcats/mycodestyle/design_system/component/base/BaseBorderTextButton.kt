package kr.kro.fatcats.mycodestyle.design_system.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun BaseBorderTextButton(
    modifier: Modifier ,
    text : String,
    textAlign: TextAlign,
    color: Color,
    style : TextStyle,
    backGroundColor: Color,
    backGroundShapeSize: Dp,
    borderColor: Color,
    borderShapeSize: Dp,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = borderShapeSize)
            )
            .background(color = backGroundColor, shape = RoundedCornerShape(backGroundShapeSize))
            .padding(start = 20.dp, end = 20.dp)
            .clickable() { onClick() },
        contentAlignment = Alignment.Center
    ){
        BaseText(
            text = text,
            style = style.copy(
                color = color
            ),
            textAlign = textAlign
        )
    }
}