package kr.kro.fatcats.mycodestyle

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.kro.fatcats.mycodestyle.room.dao.ExcuseDao
import kr.kro.fatcats.mycodestyle.room.util.InitData
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(){
    @Inject lateinit var initData: InitData
    @Inject lateinit var excuseDao: ExcuseDao

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            val isEmpty = excuseDao.getAll().isEmpty()
            if (isEmpty) {
                val data = initData.insertDefaultExcuses()
                excuseDao.insertAll(data)
                Log.d("App", "초기 데이터 삽입 완료")
            }
        }
    }
}