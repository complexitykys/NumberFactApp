package com.example.ethernetprac.presentation.main

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.ethernetprac.R
import com.example.ethernetprac.databinding.FragmentMainBinding
import com.example.ethernetprac.domain.model.NumberData
import com.example.ethernetprac.presentation.base.observeNavigation
import com.example.ethernetprac.presentation.main.adapter.NumberFactAdapter
import com.example.ethernetprac.presentation.model.NumberFactUi
import com.example.ethernetprac.utils.Failure
import com.example.ethernetprac.utils.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null

    val viewModel: MainViewModel by viewModels()

    private var textWatcher: TextWatcher? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeNavigation(viewLifecycleOwner, this)
        _binding = FragmentMainBinding.bind(view)

        val adapter = NumberFactAdapter(object : NumberFactAdapter.OnNumberClickListener {
            override fun onNumberClick(number: NumberFactUi) {
                viewModel.onNumberFactClicked(number)
            }
        })

        binding.factHistoryList.adapter = adapter

        binding.getNumberBtn.isEnabled = false

        textWatcher = binding.numberEditText.doOnTextChanged { s, _, _, _ ->
            if (s?.length == 10) {
                binding.numberEditText.error = getString(R.string.error_text_for_max_length)
            } else {
                binding.numberEditText.error = null
            }
            binding.getNumberBtn.isEnabled = !s.isNullOrBlank()
        }

        binding.getNumberBtn.setOnClickListener {
            val number = binding.numberEditText.text.toString().toLong()

            binding.progressBar.visibility = View.VISIBLE
            binding.numberFactView.visibility = View.INVISIBLE

            viewLifecycleOwner.lifecycleScope.launch {
                delay(2000)
                binding.progressBar.visibility = View.GONE
                binding.numberFactView.visibility = View.VISIBLE
                viewModel.fetchNumberFact(number)
            }
        }

        binding.getRandomNumberBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.numberFactView.visibility = View.INVISIBLE

            viewLifecycleOwner.lifecycleScope.launch {
                delay(2000)
                binding.progressBar.visibility = View.GONE
                binding.numberFactView.visibility = View.VISIBLE
                viewModel.fetchRandomNumberFact()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.numberFact.collect { result ->
                if (result == null) return@collect

                binding.numberFactView.text = when (result) {
                    is Success<NumberData> -> result.result.fact
                    is Failure<String> -> result.error
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.facts.collectLatest { list ->
                val pagingData = PagingData.from(list)
                adapter.submitData(pagingData)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher?.let { binding.numberEditText.removeTextChangedListener(it) }
        binding.getNumberBtn.setOnClickListener(null)
        _binding = null
    }
}
