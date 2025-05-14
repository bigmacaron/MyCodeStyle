package kr.kro.fatcats.adhelper

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.MobileAds
import kr.kro.fatcats.adhelper.model.AdType

class AdManager(
    private val activity: ComponentActivity
) : DefaultLifecycleObserver {

    val TAG = "AdManager"

    private var skipNextOnStart = false

    private val adLauncher: ActivityResultLauncher<Intent> = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == AdShowActivity.RESULT_SHOWED){
            skipNextOnStart = false
        }else{
            showAd()
        }
    }

    fun startObserve(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "onCreate: $owner")
        super.onCreate(owner)
        MobileAds.initialize(activity)
    }

    override fun onStart(owner: LifecycleOwner) {
        if(!skipNextOnStart) showAd()
    }

    private fun showAd() = runCatching {
        Log.d(TAG, "showAd")
        skipNextOnStart = true
        adLauncher.launch(AdShowActivity.start(activity, AdType.APP_OPEN))
    }.onFailure {
        skipNextOnStart = false
    }
}
