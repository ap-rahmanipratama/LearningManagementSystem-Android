package com.rahman.learningmanagementsystem.base

import android.content.SharedPreferences
import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import com.rahman.learningmanagementsystem.helpers.DataConvert
import com.rahman.learningmanagementsystem.ui.viewdata.ContentViewData
import javax.inject.Inject

class Cache @Inject constructor(
    private val dataConvert: DataConvert,
    private val sharedPref: SharedPreferences
){
    enum class KeyName {
        contentList
    }

    var contentList: List<ContentViewData>
        get() = dataConvert.toListContentViewData<List<ContentViewData>>(sharedPref.getString(KeyName.contentList.name, "")!!)!!
        set(value) = sharedPref.edit().putString(KeyName.contentList.name, dataConvert.toJson(value)).apply()

}

