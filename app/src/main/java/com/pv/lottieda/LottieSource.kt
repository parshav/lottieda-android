package com.pv.lottieda

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jakewharton.rxrelay2.BehaviorRelay

class LottieSource {

    private val ref: StorageReference by lazy { FirebaseStorage.getInstance().reference }

    val source = BehaviorRelay.create<String>()

    init {
        fetchLocal()
    }

    private fun fetchFirebase() {

        ref.child("card_controls.json")
            .downloadUrl
            .addOnSuccessListener {
                source.accept(it.toString())
            }
    }

    private fun fetchLocal() {
        "http://127.0.0.1:7777".httpGet().response { request, response, result ->

            result.component2()?.let {
                Log.d("pvc", "Error, ${it.errorData}")


            } ?: run {
                Log.d("pvc", "Successful : ${response.body().asString(null)}")
            }
        }
    }
}
