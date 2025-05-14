package kr.kro.fatcats.adhelper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kr.kro.fatcats.adhelper.model.AdType

class AdShowActivity : ComponentActivity() {

    companion object {
        const val RESULT_SHOWED = RESULT_OK
        const val RESULT_FAILED = RESULT_CANCELED

        private const val KEY_AD_TYPE = "ad_type"
        private const val INTERSTITIAL_ID = BuildConfig.INTERSTITIAL_ID
        private const val REWARDED_ID = BuildConfig.REWARDED_ID
        private const val APP_OPEN_AD_UNIT_ID = BuildConfig.APP_OPEN_AD_UNIT_ID


        fun start(context: Context, adType: AdType): Intent {
            return Intent(context, AdShowActivity::class.java).apply {
                putExtra(KEY_AD_TYPE, adType)
            }
        }
    }

    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null
    private var appOpenAd: AppOpenAd? = null


    private val adType: AdType by lazy {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent?.getSerializableExtra(KEY_AD_TYPE, AdType::class.java) ?: AdType.INTERSTITIAL
        } else {
            @Suppress("DEPRECATION")
            intent?.getSerializableExtra(KEY_AD_TYPE) as? AdType ?: AdType.INTERSTITIAL
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_show)
    }

    override fun onStart() {
        super.onStart()
        when (adType) {
            AdType.INTERSTITIAL -> loadInterstitialAd()
            AdType.REWARDED -> loadRewardedAd()
            AdType.APP_OPEN -> loadAppOpenAd()
        }
    }

    private fun loadAppOpenAd() {
        AppOpenAd.load(
            this,
            APP_OPEN_AD_UNIT_ID,
            AdRequest.Builder().build(),
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    showAppOpenAd()
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.e("showAppOpenAd", "광고 로드 실패: ${error.message} , $APP_OPEN_AD_UNIT_ID")
                    setResult(RESULT_SHOWED)
                    finish()
                }
            }
        )
    }

    private fun loadInterstitialAd() {
        InterstitialAd.load(
            this,
            INTERSTITIAL_ID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    showInterstitialAd()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    setResult(RESULT_SHOWED)
                    finish()
                }
            }
        )
    }

    private fun loadRewardedAd() {
        RewardedAd.load(
            this,
            REWARDED_ID,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                    showRewardedAd()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    setResult(RESULT_SHOWED)
                    finish()
                }
            }
        )
    }

    private fun showAppOpenAd() {
        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.e("showAppOpenAd", "onAdDismissedFullScreenContent: ", )
                setResult(RESULT_SHOWED)
                finish()
            }
            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                Log.e("showAppOpenAd", "onAdFailedToShowFullScreenContent: ", )
                setResult(RESULT_FAILED)
                finish()
            }
        }
        appOpenAd?.show(this) ?: run {
            Log.e("showAppOpenAd", "appOpenAd: RESULT_FAILED", )
            setResult(RESULT_FAILED)
            finish()
        }
    }

    private fun showInterstitialAd() {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                setResult(RESULT_SHOWED)
                finish()
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                setResult(RESULT_FAILED)
                finish()
            }
        }
        interstitialAd?.show(this) ?: run {
            setResult(RESULT_FAILED)
            finish()
        }
    }

    private fun showRewardedAd() {
        rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                setResult(RESULT_FAILED)
                finish()
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                setResult(RESULT_FAILED)
                finish()
            }
        }
        rewardedAd?.show(this) { rewardItem ->
            setResult(RESULT_SHOWED)
            finish()
        } ?: run {
            setResult(RESULT_FAILED)
            finish()
        }
    }

}
