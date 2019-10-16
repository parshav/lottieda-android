package com.pv.lottieda

import com.google.firebase.storage.FirebaseStorage
import com.jakewharton.rxrelay2.BehaviorRelay

class LottieSource {

    private val storage = FirebaseStorage.getInstance()
    private val ref = storage.reference

    val source = BehaviorRelay.create<String>()

    init {
        fetch()
    }

    private fun fetch() {
        ref.child("card_controls.json")
            .downloadUrl
            .addOnSuccessListener {
                source.accept(it.toString())
            }
    }
}
