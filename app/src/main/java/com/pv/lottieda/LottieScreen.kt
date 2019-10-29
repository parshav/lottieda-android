package com.pv.lottieda

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.edit
import com.airbnb.lottie.LottieAnimationView
import com.jakewharton.rxbinding3.view.clicks
import com.pv.base.BaseScreen
import com.pv.base.hideKeyboard
import com.pv.base.screen
import kotlinx.android.synthetic.main.screen_lottie.view.*

class LottieScreen : BaseScreen() {

    private lateinit var lottieView: LottieAnimationView
    private lateinit var refreshButton: ImageView
    private lateinit var sourceText: EditText

    private val preferences by lazy { activity!!.getSharedPreferences("default" ,Context.MODE_PRIVATE) }

    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {

        lottieView = view.lottie_view
        refreshButton = view.btn_refresh
        sourceText = view.et_source

        sourceText.setText(preferences.getString(SOURCE_KEY, ""))

        baseDisposable += refreshButton
            .clicks()
            .map { sourceText.text.toString() }
            .doOnNext {
                sourceText.hideKeyboard()
                saveSource(it)
            }
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

    private fun saveSource(value: String) = preferences.edit {
            putString(SOURCE_KEY, value)
        }

    companion object {
        private const val SOURCE_KEY = "sourceKey"
    }
}
