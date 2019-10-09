package com.pv.pvbase

import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.storage.FirebaseStorage
import com.pv.base.BaseScreen
import com.pv.base.screen
import kotlinx.android.synthetic.main.screen_lottie.view.*

class LottieScreen : BaseScreen() {

    private lateinit var lottieView: LottieAnimationView
    private lateinit var settingsButton: Button

    private val storage = FirebaseStorage.getInstance()
    private val ref = storage.reference

    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {
        lottieView = view.lottie_view
        settingsButton = view.btn_settings

        settingsButton.setOnClickListener {

        }

        update()
    }

    private fun update() {
        lottieView.cancelAnimation()
        ref.child("card_controls.json")
            .downloadUrl
            .addOnSuccessListener {
                lottieView.setAnimationFromUrl(it.toString())
            }
    }
}