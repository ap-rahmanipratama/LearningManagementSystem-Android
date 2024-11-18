package com.rahman.learningmanagementsystem.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.databinding.ToolbarComponentBinding

internal class ToolbarComponent(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var binding: ToolbarComponentBinding

    private var onBackPressed: (() -> Unit)? = null


    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.ToolbarComponent, 0, 0
        )
        val title = a.getString(R.styleable.ToolbarComponent_title) ?: ""

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ToolbarComponentBinding.inflate(inflater)
        binding.labelToolbarTitle.text = if (title.isEmpty()) "Title" else title
        binding.btnBack.setOnClickListener {
            onBackPressed?.invoke()
        }

        addView(binding.root)

    }
    fun setTitle(title: String) {
        binding.labelToolbarTitle.text = title
    }

    fun onBackButtonClick(callback: () -> Unit) {
        binding.btnBack.setOnClickListener {
            callback.invoke()
        }
    }
}