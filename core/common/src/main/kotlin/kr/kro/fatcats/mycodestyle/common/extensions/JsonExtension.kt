package kr.kro.fatcats.mycodestyle.common.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> parseJsonList(json: String): List<T> {
    val type = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(json, type)
}