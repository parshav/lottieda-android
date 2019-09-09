package com.pv.pvbase

import com.pv.base.BaseActivity
import com.pv.base.NavigatorTemplate
import com.pv.base.ui
import org.koin.android.ext.android.inject

/*
* This will not yet work as no baseFragment is defined.
* */
class MainActivity : BaseActivity() {

    private val navigatorTemplate: NavigatorTemplate by inject()

    override fun uiBuilder() = ui {
        layout = R.layout.activity_main
//        screen = LottieScreen()
    }

    override fun onStart() {
        super.onStart()

        navigatorTemplate.navigateTo(LottieScreen())
    }
}
