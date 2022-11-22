package com.example.exchangerate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val adapter: CurrencyAdapter by lazy {CurrencyAdapter()}
    private val viewModel: MainViewModel by viewModel()

    private val rvCurrency:RecyclerView by lazy {requireActivity().findViewById(R.id.rvCurrencyRate)}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, :: render)
        rvCurrency.adapter=adapter


    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.list)
    }

}