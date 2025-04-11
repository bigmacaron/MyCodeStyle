package kr.kro.fatcats.mycodestyle.design_system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import kr.kro.fatcats.mycodestyle.design_system.R

 val PretendardMediumFontFamily = FontFamily(Font(R.font.pretendard_medium)) //500
 val PretendardSemiBoldFontFamily = FontFamily(Font(R.font.pretendard_semibold)) //600
 val PretendardBoldFontFamily = FontFamily(Font(R.font.pretendard_bold)) //700

@Immutable
private class AppTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    lineHeight: TextUnit,
) {
    val style: TextStyle = TextStyle(
        fontFamily = fontFamily,
        fontSize = fontSize,
        lineHeight = lineHeight,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    )
}

@Immutable
object AppTypography {

    val Default_Medium = AppTextStyle(
        fontFamily = PretendardMediumFontFamily,
        fontSize = 16.sp,
        lineHeight = 1.5.em,
    ).style

    val Default_Bold = AppTextStyle(
        fontFamily = PretendardSemiBoldFontFamily,
        fontSize = TextUnit.Unspecified,
        lineHeight =TextUnit.Unspecified,
    ).style

    val Heading_1B = AppTextStyle(
        fontFamily = PretendardBoldFontFamily,
        fontSize = 36.sp,
        lineHeight = 1.2.em
    ).style


    val Heading_1Sb = AppTextStyle(
        fontFamily = PretendardSemiBoldFontFamily,
        fontSize = 36.sp,
        lineHeight = 43.2.sp
    ).style

    val Heading_2B = AppTextStyle(
        fontFamily = PretendardBoldFontFamily,
        fontSize = 32.sp,
        lineHeight = 1.2.em
    ).style

    val Body_1B = AppTextStyle(
        fontFamily = PretendardBoldFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ).style


    val Body_1M = AppTextStyle(
        fontFamily = PretendardMediumFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ).style

    val Body_2B = AppTextStyle(
        fontFamily = PretendardBoldFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ).style


    val Body_2M = AppTextStyle(
        fontFamily = PretendardMediumFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ).style

    val Label_1M = AppTextStyle(
        fontFamily = PretendardMediumFontFamily,
        fontSize = 16.sp,
        lineHeight = TextUnit.Unspecified
    ).style

    val Label_2Sb = AppTextStyle(
        fontFamily = PretendardSemiBoldFontFamily,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified
    ).style

    val Label_2M = AppTextStyle(
        fontFamily = PretendardMediumFontFamily,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified
    ).style

}


