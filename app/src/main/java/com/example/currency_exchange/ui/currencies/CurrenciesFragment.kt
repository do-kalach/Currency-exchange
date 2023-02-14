package com.example.currency_exchange.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_exchange.databinding.FragmentCurrenciesBinding
import com.example.domain.model.Currency
import com.example.currency_exchange.ui.adapter.CurrencyAdapter
import com.example.domain.ResultOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentCurrenciesBinding
    private val viewModels by viewModels<CurrenciesViewModel>()
    private var adapter: CurrencyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CurrencyAdapter(requireContext()) {
            Toast.makeText(requireContext(), it.base.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModels.getAllCurrencies()

        binding.recyclerView.adapter = adapter

        val touchHelper = ItemTouchHelper(touchHelper)
        touchHelper.attachToRecyclerView(binding.recyclerView)

        viewModels.data
            .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
            .onEach {
                when (it) {
                    is ResultOf.Success -> {
                        adapter?.submitList(it.data)
                        binding.progress.visibility = View.GONE
                    }
                    is ResultOf.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is ResultOf.Error -> {
                        binding.progress.visibility = View.GONE
                    }
                }
            }
            .launchIn(lifecycleScope)
    }


    val touchHelper =
        object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                recyclerView.adapter?.notifyItemMoved(viewHolder.position, target.position)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val currency = viewHolder.itemView.tag as Currency?
                val oldList = adapter?.currentList?.toMutableList()!!
                val position = adapter?.currentList?.indexOf(currency)
                val newList = oldList.apply { remove(currency) }
                //adapter?.notifyItemRemoved(position!!)
                adapter?.submitList(newList)
            }

        }
}