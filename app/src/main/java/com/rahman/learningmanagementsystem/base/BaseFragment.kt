package com.rahman.learningmanagementsystem.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initEventListener()
        initObserver()
        loadData()
    }

    fun navigate(id: NavDirections) {
        findNavController().navigate(id)
    }

    fun navigate(id: NavDirections, option: NavOptions) {
        findNavController().navigate(id, option)
    }

    open fun initComponent(){}

    open fun initEventListener(){}

    open fun initObserver(){}

    open fun loadData(){}

}