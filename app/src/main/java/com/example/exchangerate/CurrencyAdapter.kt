package com.example.exchangerate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var currencyData :List<CurrencyModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvCurrency:TextView
            val tvRate:TextView

            init{
                tvCurrency = view.findViewById(R.id.tvCurrencyName)
                tvRate = view.findViewById(R.id.tvRateOfCurrency)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_value, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCurrency.text = currencyData[position].currency
        holder.tvRate.text = currencyData[position].value
    }

    override fun getItemCount(): Int {
        return currencyData.size
    }

    fun setData (currency :List <CurrencyModel>) {
        currencyData = currency
        notifyDataSetChanged()
    }
}