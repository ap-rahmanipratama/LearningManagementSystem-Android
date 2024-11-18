package com.rahman.learningmanagementsystem.helpers

import com.rahman.learningmanagementsystem.ui.viewdata.ContentViewData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataConvert @Inject constructor() {
    inline fun <reified T> toData(data: String) : T? {
        if ( data.isEmpty()) {
            return null
        }
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return adapter.fromJson(data)
    }

    inline fun <reified T> toListContentViewData(data: String) : List<ContentViewData>? {
        if ( data.isEmpty()) {
            return null
        }
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, ContentViewData::class.java)
        val adapter: JsonAdapter<List<ContentViewData>> = moshi.adapter(type)
        return adapter.fromJson(data)
    }


    inline fun <reified T> toJson(data: T) : String? {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        return adapter.toJson(data)
    }

}