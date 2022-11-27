package com.example.exchangerate.maincurrency.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.UiEvent
import com.example.ViewState
import com.example.exchangerate.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()

    private val adapter: CurrencyAdapter by lazy {
        CurrencyAdapter { index ->
            viewModel.processUiEvent(
                UiEvent.OnCurrencyClicked(index)
            )
        }
    }
    private val rvCurrency: RecyclerView by lazy { requireActivity().findViewById(R.id.rvCurrencyRate) }
    private val btnSort: ImageView by lazy { requireActivity().findViewById(R.id.ivSort) }
    private val itemSort: CardView by lazy { requireActivity().findViewById(R.id.itemSort) }
    private val sortRadioGroup: RadioGroup by lazy { requireActivity().findViewById(R.id.rgSort) }
    private val tvDate: TextView by lazy { requireActivity().findViewById(R.id.tvDate) }
    private val etText: EditText by lazy { requireActivity().findViewById(R.id.etSearchCurrency) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        rvCurrency.adapter = adapter

        btnSort.setOnClickListener { viewModel.processUiEvent(UiEvent.OnSortButtonClicked) }

        sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSortZA -> {
                    viewModel.processUiEvent(UiEvent.SortZA)
                }
                R.id.rbSortAZ -> {
                    viewModel.processUiEvent(UiEvent.SortAZ)
                }
                R.id.rbSortMaxMin -> {
                    viewModel.processUiEvent(UiEvent.SortMaxMin)
                }
                R.id.rbSortMinMax -> {
                    viewModel.processUiEvent(UiEvent.SortMinMax)
                }
            }
        }
        etText.doAfterTextChanged { text ->
            viewModel.processUiEvent(UiEvent.OnSearchEdit(text.toString()))
            etText.requestFocus()
        }


    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.currencyShownList)
        if (viewState.sortVisible == true) {
            itemSort.visibility = CardView.VISIBLE
        } else {
            itemSort.visibility = CardView.GONE
        }
        tvDate.text = viewState.date

    }

}