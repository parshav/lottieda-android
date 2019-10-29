package com.pv.lottieda

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxrelay2.BehaviorRelay
import com.pv.base.BaseScreen
import com.pv.base.screen
import kotlinx.android.synthetic.main.screen_lottie.view.*
import java.net.MalformedURLException
import kotlin.Exception

class LottieScreen : BaseScreen() {

    private lateinit var lottieView: LottieAnimationView
    private lateinit var refreshButton: ImageView
    private lateinit var sourceText: EditText

    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {
        lottieView = view.lottie_view
        refreshButton = view.btn_refresh
        sourceText = view.et_source

        baseDisposable += refreshButton
            .clicks()
            .map { sourceText.text.toString() }
            .subscribe(::tryPlay)
    }

    private fun tryPlay(source: String) {

        // Potentially use this for error handling
/*        LottieCompositionFactory.fromUrl(context, source).addFailureListener {
                Toast.makeText(
                    context,
                    "Error in playing URL ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }*/

        lottieView.setAnimationFromUrl(
            if (source.isNotEmpty() && source.first() == 'h') source
            else "http://${source}"
        )
        lottieView.playAnimation()

    }
}

