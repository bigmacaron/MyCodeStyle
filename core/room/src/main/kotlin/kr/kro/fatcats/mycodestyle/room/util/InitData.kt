package kr.kro.fatcats.mycodestyle.room.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kr.kro.fatcats.mycodestyle.common.JsonUtil
import kr.kro.fatcats.mycodestyle.room.entitys.ExcuseEntity
import javax.inject.Inject

class InitData @Inject constructor(
    private val jsonUtil: JsonUtil
) {
    val TAG = "InitData"
    fun insertDefaultExcuses(): List<ExcuseEntity> {
        val json = jsonUtil.getJson("excuses_data")
        val type = object : TypeToken<List<ExcuseEntity>>() {}.type
        val list: List<ExcuseEntity> = Gson().fromJson(json, type)
        return list.also { Log.d(TAG, "insertDefaultExcuses: $it ") }
    }
}