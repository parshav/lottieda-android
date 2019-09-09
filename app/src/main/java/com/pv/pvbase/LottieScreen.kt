package com.pv.pvbase

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.storage.FirebaseStorage
import com.pv.base.BaseScreen
import com.pv.base.NavigatorTemplate
import com.pv.base.screen
import com.pv.base.temp
import kotlinx.android.synthetic.main.screen_lottie.view.*
import org.koin.android.ext.android.inject

class LottieScreen : BaseScreen() {

    private val navigator by inject<NavigatorTemplate>()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultFilename: String

    private lateinit var lottieView: LottieAnimationView
    private lateinit var refresh: Button
    private lateinit var filenameText: EditText

    private val storage = FirebaseStorage.getInstance()
    private val ref = storage.reference


    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {
        lottieView = view.lottie_view
        filenameText = view.et_filename
        refresh = view.btn_refresh

        filenameText.clearFocus()

        sharedPreferences = context!!.getSharedPreferences("pvLottie", Context.MODE_PRIVATE)

        defaultFilename = sharedPreferences.getString("lottieFileName", "card_controls.json")!!

        filenameText.setText(defaultFilename)

        update()

        filenameText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                sharedPreferences.edit {
                    putString("lottieFileName", s.toString())
                }
            }
        })

        refresh.setOnClickListener {
            //            update()
            navigator.pop()

            Handler().postDelayed({
                //doSomethingHere()
                navigator.navigateTo(LottieScreen())
            }, 1000)
        }
    }

    private fun update() {
        lottieView.cancelAnimation()
        ref.child("card_controls.json")
            .downloadUrl
            .addOnSuccessListener {
                lottieView.setAnimationFromUrl(it.toString())
            }
    }

    override fun bindings(): Array<temp> = emptyArray()

}