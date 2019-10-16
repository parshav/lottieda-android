package com.pv.lottieda

import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.storage.FirebaseStorage
import com.pv.base.BaseScreen
import com.pv.base.screen
import com.pv.base.temp
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.screen_lottie.view.*

class LottieScreen : BaseScreen() {

    private lateinit var lottieView: LottieAnimationView
    private lateinit var settingsButton: Button

    val lottieSource = LottieSource()

    private lateinit var disposable: Disposable

    override fun ui() = screen {
        layout = R.layout.screen_lottie
    }

    override fun onViewLoaded(view: View) {
        lottieView = view.lottie_view
        settingsButton = view.btn_settings

        settingsButton.setOnClickListener {

        }

        disposable = lottieSource.source
            .doAfterNext { lottieView.playAnimation() }
            .subscribe(lottieView::setAnimationFromUrl)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.dispose()
    }
}
