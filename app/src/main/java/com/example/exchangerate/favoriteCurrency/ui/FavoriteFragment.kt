package com.example.exchangerate.favoriteCurrency.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteFragmentViewModel by viewModel()

    private val adapter: CurrencyFavoriteAdapter by lazy { CurrencyFavoriteAdapter{index ->  viewModel.processUiEvent(UiEvent.OnCurrencyClicked(index))} }
    private val rvCurrency: RecyclerView by lazy { requireActivity().findViewById(R.id.rvCurrencyRateFavorite) }
    private val btnSort: ImageView by lazy { requireActivity().findViewById(R.id.ivSortFavorite) }
    private val itemSort: CardView by lazy { requireActivity().findViewById(R.id.itemSortFavorite) }
    private val sortRadioGroup: RadioGroup by lazy { requireActivity().findViewById(R.id.rgSortFavorite) }
    private val tvDate: TextView by lazy { requireActivity().findViewById(R.id.tvDateFavorite) }
    private val etText: EditText by lazy { requireActivity().findViewById(R.id.etSearchCurrencyFavorite) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        rvCurrency.adapter = adapter

        btnSort.setOnClickListener { viewModel.processUiEvent(UiEvent.OnSortButtonClicked) }

        sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSortZAFavorite -> {
                    viewModel.processUiEvent(UiEvent.SortZA)
                }
                R.id.rbSortAZFavorite -> {
                    viewModel.processUiEvent(UiEvent.SortAZ)
                }
                R.id.rbSortMaxMinFavorite -> {
                    viewModel.processUiEvent(UiEvent.SortMaxMin)
                }
                R.id.rbSortMinMaxFavorite -> {
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
        adapter.setData(viewState.currencyFavoriteShownList)
        if (viewState.sortVisible == true) {
            itemSort.visibility = CardView.VISIBLE
        } else {
            itemSort.visibility = CardView.GONE
        }
        tvDate.text = viewState.date

    }

}