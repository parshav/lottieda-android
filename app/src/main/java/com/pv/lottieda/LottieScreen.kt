package com.pv.lottieda

import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.pv.base.BaseScreen
import com.pv.base.screen
import com.pv.base.temp
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.screen_lottie.view.*

class LottieScreen : BaseScreen() {

    private lateinit var lottieView: LottieAnimationView
    private lateinit var settingsButton: Button

    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {
        lottieView = view.lottie_view
        settingsButton = view.btn_settings

        settingsButton.setOnClickListener {

        }

        lottieView.setAnimationFromUrl("http://x.x.x.x:7777")
        lottieView.playAnimation()
    }
}
