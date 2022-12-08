package com.geekbrains.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

        with(binding) {
            btnNumber1.setOnClickListener {
                presenter.onBtnOneClicked()
            }
            btnNumber2.setOnClickListener {
                presenter.onBtnTwoClicked()
            }
            btnNumber3.setOnClickListener {
                presenter.onBtnThreeClicked()
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun btnOneSetText(counter: String) {
        binding.tvText1.text = counter
    }

    override fun btnTwoSetText(counter: String) {
        binding.tvText2.text = counter
    }

    override fun btnThreeSetText(counter: String) {
        binding.tvText3.text = counter
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

}