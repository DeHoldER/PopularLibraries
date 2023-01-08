package com.geekbrains.popularlibraries.view.converter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentConverterBinding
import com.geekbrains.popularlibraries.utils.ImageConverter.getPathFromUri
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import moxy.ktx.moxyPresenter

class ConverterFragment :
    ViewBindingFragment<FragmentConverterBinding>(FragmentConverterBinding::inflate),
    ConverterView, OnBackPressedListener {

    private var pathImagePicked: String? = null
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                presenter.onImagePicked(result.data)
            }
        }

    companion object {
        fun getInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }

    private val presenter: ConverterPresenter by moxyPresenter {
        ConverterPresenter(GeekBrainsApp.instance.router)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imagePicked.setOnClickListener { presenter.onPickImageClicked() }
        binding.btnConvert.setOnClickListener {
            if (pathImagePicked != null) {
                presenter.onBtnConvertClicked(
                    (binding.imagePicked.drawable as BitmapDrawable).bitmap,
                    pathImagePicked
                )
            }
        }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun pickImage() {
        resultLauncher.launch(presenter.prepareActivityForResult())
    }

    override fun initImageToConvert(data: Intent) {
        val imagePickedUri = data.data
        if (imagePickedUri != null) {
            with(binding) {
                imagePicked.background = null
                imagePicked.setImageURI(imagePickedUri)
                pathImagePicked = getPathFromUri(imagePickedUri)
                textPathImagePicked.text = pathImagePicked
            }
        }
    }

    override fun initConvertedImage(result: Pair<String, Bitmap>) {
        with(binding) {
            textPathImageConverted.text = result.first
            imageConverted.setImageBitmap(result.second)
            imageConverted.background = null
        }
        Toast.makeText(requireContext(), "${result.first} converted to png.", Toast.LENGTH_SHORT)
            .show()
    }

}