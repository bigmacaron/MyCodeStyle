package kr.kro.fatcats.mycodestyle.design_system

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource

object AppIcon {

    data class DrawableWithText(@DrawableRes private val _image: Int, private val _text: AppString.TextWithResId) {
        @Composable
        operator fun invoke() = _image.getImageVector()
        val id = _image
        val painter @Composable get()= _image.getPainterResource()
        val text @Composable
        get() = _text.invoke()
    }

    @Composable
    private fun @receiver:DrawableRes Int.getImageVector(): ImageVector = ImageVector.vectorResource(id = this)
    @Composable
    private fun @receiver:DrawableRes Int.getPainterResource(): Painter = painterResource(id = this)

    object Common {
        val check = DrawableWithText(R.drawable.ic_check_24 , AppString.Description.Check)
        val favorite = DrawableWithText(R.drawable.nav_star_24 , AppString.Description.Favorite)
        val ArrowDropDown = DrawableWithText(R.drawable.arrow_drop_down_24 , AppString.Description.Favorite)
        val TouchApp = DrawableWithText(R.drawable.touch_app_24 , AppString.Description.Favorite)
        val ContentCopy = DrawableWithText(R.drawable.content_copy_24 , AppString.Description.Favorite)
    }

    object Nav {
        val NavHome24 = DrawableWithText(R.drawable.nav_home_24, AppString.Description.Home)
        val NavStar24 = DrawableWithText(R.drawable.nav_star_24, AppString.Description.Favorite)
    }

}