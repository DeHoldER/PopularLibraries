package com.geekbrains.popularlibraries.view.converter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.geekbrains.popularlibraries.app.GeekBrainsApp
import com.geekbrains.popularlibraries.core.OnBackPressedListener
import com.geekbrains.popularlibraries.databinding.FragmentConverterBinding
import com.geekbrains.popularlibraries.utils.REQUEST_CODE_GET_CONTENT
import com.geekbrains.popularlibraries.utils.ViewBindingFragment
import moxy.ktx.moxyPresenter

class ConverterFragment :
    ViewBindingFragment<FragmentConverterBinding>(FragmentConverterBinding::inflate),
    ConverterView, OnBackPressedListener {

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
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpg"))
        ActivityCompat.startActivityForResult(
            requireActivity(),
            Intent.createChooser(intent, "Select Picture"),
            REQUEST_CODE_GET_CONTENT, null
        )
//        presenter.imagePicked(path)
    }

}