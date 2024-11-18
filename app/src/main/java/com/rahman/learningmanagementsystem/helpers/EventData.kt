package com.rahman.learningmanagementsystem.helpers

open class EventData<out T>(val content: T? = null,
                            val message: String = "OK") {

    private var hasBeenHandled = false
    fun getContentIfNotHandled(): Boolean {
        return if (hasBeenHandled) {
            false
        } else {
            hasBeenHandled = true
            true
        }
    }
}