package kr.kro.fatcats.mycodestyle.design_system.component.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.design_system.theme.AppTypography

@Composable
fun BaseText(
    modifier: Modifier = Modifier,
    text : String,
    style: TextStyle = AppTypography.Body_1M.copy(color = AppColor.Black),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    textAlign : TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLine : Int = Int.MAX_VALUE,
    textDecoration: TextDecoration? = null,
)
{
    Text(
        text = text,
        style = style,
        modifier = modifier,
        textAlign = textAlign,
        onTextLayout = onTextLayout,
        overflow = overflow,
        maxLines = maxLine,
        textDecoration = textDecoration
    )
}