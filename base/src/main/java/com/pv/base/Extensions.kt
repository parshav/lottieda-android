package com.pv.base

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun log(message: String, tag: String = "PV") = Log.d(tag, message)

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.hideSoftInputFromWindow(windowToken, 0)
}
