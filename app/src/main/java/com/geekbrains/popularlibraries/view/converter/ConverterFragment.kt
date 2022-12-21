package com.geekbrains.popularlibraries.view.converter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
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

}