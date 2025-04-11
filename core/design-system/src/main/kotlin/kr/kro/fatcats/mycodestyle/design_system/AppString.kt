package kr.kro.fatcats.mycodestyle.design_system

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

object AppString{

    data class TextWithResId(@StringRes val resId: Int) {
        @Composable
        operator fun invoke(): String = stringResource(id = resId)
    }

    object Common {
        val TypoText get() = TextWithResId(R.string.typo_text)
    }

    object Nav{
        val Home get() = TextWithResId(R.string.nav_home)
        val Text get() = TextWithResId(R.string.nav_text)
    }


    object Description{
        val Check get() = TextWithResId(R.string.description_check)
        val Home get() = TextWithResId(R.string.description_home)
        val Text get() = TextWithResId(R.string.description_text)
    }

}