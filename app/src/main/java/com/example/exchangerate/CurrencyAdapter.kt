package com.example.exchangerate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.math.RoundingMode

class CurrencyAdapter(): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var currencyData :List<CurrencyModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvCurrency:TextView
            val tvRate:TextView
            val tvDiff:TextView
            val tvCurrencyCode:TextView
            val ivDirect:ImageView
            val ivFavorite:ImageView

            init{
                tvCurrency = view.findViewById(R.id.tvCurrencyName)
                tvRate = view.findViewById(R.id.tvRateOfCurrency)
                tvDiff = view.findViewById(R.id.tvDiffrent)
                tvCurrencyCode = view.findViewById(R.id.tvCurrencyCode)
                ivDirect=view.findViewById(R.id.ivDirectValue)
                ivFavorite = view.findViewById(R.id.ivFavorite)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_value, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCurrency.text = currencyData[position].currency
        holder.tvRate.text = currencyData[position].value.toBigDecimal().setScale(4, RoundingMode.HALF_UP).toString()
        holder.tvDiff.text = (currencyData[position].value.toDouble() -currencyData[position].previous.toDouble()).toBigDecimal().setScale(4, RoundingMode.HALF_UP).toString()
        if((currencyData[position].value.toFloat()-currencyData[position].previous.toFloat())<=0) {
            holder.ivDirect.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
        }
        holder.tvCurrencyCode.text = currencyData[position].code
        if(currencyData[position].favoriteCurrency==true) {
            holder.ivFavorite.setImageResource(R.drawable.ic_baseline_star_24)
        }

    }

    override fun getItemCount(): Int {
        return currencyData.size
    }

    fun setData (currency :List <CurrencyModel>) {
        currencyData = currency
        notifyDataSetChanged()
    }
}