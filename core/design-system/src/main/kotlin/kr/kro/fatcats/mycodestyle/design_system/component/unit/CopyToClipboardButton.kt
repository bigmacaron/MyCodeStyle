package kr.kro.fatcats.mycodestyle.design_system.component.unit

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import kr.kro.fatcats.mycodestyle.design_system.AppIcon
import kr.kro.fatcats.mycodestyle.design_system.component.base.BaseImage
import kr.kro.fatcats.mycodestyle.design_system.theme.AppColor
import kr.kro.fatcats.mycodestyle.model.ImageOptions

@Composable
fun CopyToClipboardButton(textToCopy: String) {
    val context = LocalContext.current

    BaseImage(
        modifier = Modifier.clickable {
            val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", textToCopy)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(context, "복사되었습니다", Toast.LENGTH_SHORT).show()
        },
        iconVector = AppIcon.Common.ContentCopy,
        imageOptions = ImageOptions(colorFilter = ColorFilter.tint(AppColor.Gray))
    )
}