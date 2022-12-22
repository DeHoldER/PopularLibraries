package com.geekbrains.popularlibraries.view.converter

import android.content.Intent
import android.graphics.Bitmap
import com.geekbrains.popularlibraries.utils.ImageConverter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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

    fun onBtnConvertClicked(bitmap: Bitmap, pathImagePicked: String?) {
        if (pathImagePicked != null) {
            ImageConverter.convertJpgToPng(bitmap, pathImagePicked)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.initConvertedImage(it)
                    },
                    {}
                )
        }
    }

}