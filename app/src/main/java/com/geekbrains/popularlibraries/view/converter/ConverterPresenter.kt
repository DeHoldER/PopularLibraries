package com.geekbrains.popularlibraries.view.converter

import android.content.Intent
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class ConverterPresenter(
    private val router: Router
) : MvpPresenter<ConverterView>() {

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun onPickImageClicked() {
        viewState.pickImage()
    }

    fun onImagePicked(data: Intent?) {
        data?.let { viewState.initImageToConvert(it) }
    }

    fun prepareActivityForResult(): Intent? {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpg"))
        return Intent.createChooser(intent, "Select Picture")
    }

}