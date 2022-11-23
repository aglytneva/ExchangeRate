package com.example.exchangerate

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.DataEvent
import com.example.UiEvent
import com.example.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val adapter: CurrencyAdapter by lazy { CurrencyAdapter() }
    private val viewModel: MainViewModel by viewModel()

    private val rvCurrency: RecyclerView by lazy { requireActivity().findViewById(R.id.rvCurrencyRate) }
    private val btnSort: ImageView by lazy { requireActivity().findViewById(R.id.ivSort) }
    private val itemSort: CardView by lazy { requireActivity().findViewById(R.id.itemSort) }
    private val sortRadioGroup:RadioGroup by lazy { requireActivity().findViewById(R.id.rgSort) }
    private val tvDate: TextView by lazy { requireActivity().findViewById(R.id.tvDate) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvCurrency.adapter = adapter
        viewModel.processDataEvent(DataEvent.OnRatesLoaded)

        btnSort.setOnClickListener { viewModel.processUiEvent(UiEvent.OnSortButtonClicked) }


        sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            (R.id.rbSortZA).apply {
                viewModel.processUiEvent(UiEvent.SortZA)
            }
            (R.id.rbSortAZ).apply {
                viewModel.processUiEvent(UiEvent.SortAZ)
            }
            (R.id.rbSortMaxMin).apply {
                viewModel.processUiEvent(UiEvent.SortMaxMin)
            }
            (R.id.rbSortMinMax).apply {
                viewModel.processUiEvent(UiEvent.SortMinMax)
            }
        }
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.list)
        if (viewState.sortVisible == true) {
            itemSort.visibility = CardView.VISIBLE
        } else {
            itemSort.visibility = CardView.GONE
        }
        tvDate.text = viewState.date

    }

}