package com.example.batmanmovies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment(layout : Int) : Fragment(layout) {

    protected lateinit var customToolbar: CustomToolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(view)
    }

    protected open fun bind(view: View) {
        customToolbar.setToolbarTitle("batman movies")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        customToolbar = context as CustomToolbar
    }

    interface CustomToolbar {
        fun setToolbarTitle(title: String)
    }
}