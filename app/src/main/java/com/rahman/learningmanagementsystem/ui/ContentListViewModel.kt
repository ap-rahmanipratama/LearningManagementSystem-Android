package com.rahman.learningmanagementsystem.ui

import android.util.Log
import androidx.annotation.ContentView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import com.rahman.learningmanagementsystem.client.dto.toContentViewData
import com.rahman.learningmanagementsystem.helpers.EventData
import com.rahman.learningmanagementsystem.service.ContentService
import com.rahman.learningmanagementsystem.ui.viewdata.ContentViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentListViewModel @Inject constructor(
    private val service: ContentService,
) : ViewModel(){
    val liveError: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }

    protected val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        if ( ex.message?.isNotEmpty() == true ) {
            liveError.value = EventData(
                message = ex.message!!
            )
        }
    }

    private val _liveGetContentSuccess: MutableLiveData<EventData<List<ContentViewData>>> by lazy { MutableLiveData<EventData<List<ContentViewData>>>() }
    val liveGetContentSuccess: LiveData<EventData<List<ContentViewData>>> get() = _liveGetContentSuccess


    fun getList() {
        viewModelScope.launch(exceptionHandler) {

            val response = service.getListContent()
            val body = response.body()!!

            _liveGetContentSuccess.value = EventData(content = body.map { it.toContentViewData() })
        }
    }


}