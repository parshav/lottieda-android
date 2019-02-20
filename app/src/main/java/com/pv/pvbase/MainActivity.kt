package com.pv.pvbase

import com.pv.base.BaseActivity
import com.pv.base.ui

/*
* This will not yet work as no baseFragment is defined.
* */
class MainActivity : BaseActivity() {
    override fun uiBuilder() = ui {}
}
