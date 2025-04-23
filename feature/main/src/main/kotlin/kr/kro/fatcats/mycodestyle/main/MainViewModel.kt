package kr.kro.fatcats.mycodestyle.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.kro.fatcats.mycodestyle.domain.model.ExcuseUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val excuseUseCases: ExcuseUseCases
) : ViewModel() {
    init {
//        excuseUseCases
    }
}