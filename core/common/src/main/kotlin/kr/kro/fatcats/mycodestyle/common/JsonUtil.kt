package kr.kro.fatcats.mycodestyle.common

import android.content.Context
import android.content.res.AssetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonUtil @Inject constructor(
    @ApplicationContext private val context: Context
){

    private fun AssetManager.readJson(fileName: String): String {
        return open("json/$fileName").bufferedReader().readText()
    }

    fun getJson(fileName: String): String {
        return context.assets.readJson("$fileName.json")
    }

}