package kr.kro.fatcats.mycodestyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.kro.fatcats.adhelper.AdManager
import kr.kro.fatcats.mycodestyle.main.App
import kr.kro.fatcats.mycodestyle.main.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adManager: AdManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        adManager = AdManager(this)
//        adManager.startObserve(lifecycle)
        setContent {
            App(viewModel)
        }
    }
}
