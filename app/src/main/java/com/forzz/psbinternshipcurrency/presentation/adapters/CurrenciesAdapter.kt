package com.forzz.psbinternshipcurrency.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.forzz.psbinternshipcurrency.R
import com.forzz.psbinternshipcurrency.databinding.CurrencyItemBinding
import com.forzz.psbinternshipcurrency.domain.model.Currency
import com.forzz.psbinternshipcurrency.utils.Extensions.Companion.roundWithZeros
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

class CurrenciesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currencies: MutableList<Currency> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater, parent, false)

        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Currency = currencies[position]

    fun updateData(list: List<Currency>) {
        currencies.clear()
        currencies.addAll(list)
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(currency: Currency) {
            val value = currency.value.roundWithZeros(4) + '\u20BD'
            val currencyChangeValue = currency.value - currency.previous

            binding.currency = currency
            binding.changeValue = currencyChangeValue.roundWithZeros(4)
            if (currencyChangeValue >= 0) {
                binding.changeIv.setImageResource(R.drawable.currency_change_up)
            } else {
                binding.changeIv.setImageResource(R.drawable.currency_change_down)
            }
            binding.currentValue = value
        }
    }

    private fun convertDateToDayMonth(date: Date): String {
        val dateFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
        return dateFormat.format(date)
    }
}