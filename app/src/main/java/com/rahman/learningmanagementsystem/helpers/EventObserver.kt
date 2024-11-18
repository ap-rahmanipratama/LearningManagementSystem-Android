package com.rahman.learningmanagementsystem.helpers

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnhandledContent: (EventData<T>) -> Unit) : Observer<EventData<T>> {

    override fun onChanged(value: EventData<T>) {
        value.getContentIfNotHandled().let {
            if (it) {
                onEventUnhandledContent(value)
            }
        }
    }
}