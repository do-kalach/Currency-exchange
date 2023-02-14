package com.example.currency_exchange.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources.NotFoundException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_exchange.R
import com.example.currency_exchange.databinding.ItemLayoutBinding
import com.example.domain.model.Currency
import com.example.currency_exchange.ui.adapter.CurrencyAdapter.ViewHolder
import kotlin.math.roundToInt

typealias OnCLick = (Currency) -> Unit

class CurrencyAdapter(private val context: Context, private val onClick: OnCLick) :
    ListAdapter<Currency, ViewHolder>(diffCallBack) {

    private lateinit var bindin: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        bindin = ItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(bindin)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = getItem(position)
        val pos = getItemId(position)
        holder.bind(currency, pos.toInt())
    }

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "DiscouragedApi")
        fun bind(currency: Currency, position: Int) {
            binding.root.tag = position
            binding.root.setOnLongClickListener() {
                onClick.invoke(currency)
                return@setOnLongClickListener true
            }
            binding.root.tag = currency
            binding.base.text = currency.base
            val draw = context.resources.getIdentifier(
                currency.flag,
                "drawable",
                "com.example.currency_exchange"
            )
            try {
                binding.imageFlag.setImageDrawable(
                    ContextCompat.getDrawable(context, draw)
                )
            }catch (e:NotFoundException){
                binding.imageFlag.setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.ic_aud_flag)
                )
            }
            binding.rate.text = "0.${(currency.rate * 100).roundToInt()}"
        }
    }

    private companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(
                oldItem: Currency,
                newItem: Currency
            ): Boolean {
                return oldItem.base == newItem.base
            }

            override fun areContentsTheSame(
                oldItem: Currency,
                newItem: Currency
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}