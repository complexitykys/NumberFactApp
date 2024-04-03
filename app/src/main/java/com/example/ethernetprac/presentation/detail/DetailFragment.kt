package com.example.ethernetprac.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.ethernetprac.R
import com.example.ethernetprac.databinding.FragmentDetailsBinding
import com.example.ethernetprac.presentation.base.observeNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNumber(args.number)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeNavigation(viewLifecycleOwner, this)
        _binding = FragmentDetailsBinding.bind(view)
        binding.backButton.setOnClickListener {
            viewModel.onBackPressed()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.numberDetail.collect { numberDetail ->
                with(binding) {
                    numberTextView.text = numberDetail.number
                    factTextView.text = numberDetail.fact
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.backButton.setOnClickListener(null)
        _binding = null
    }

}