package com.example.ethernetprac.presentation.numberFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ethernetprac.R
import com.example.ethernetprac.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.numberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length == 10) {
                    binding.numberEditText.error = getString(R.string.error_text_for_max_length)
                } else {
                    binding.numberEditText.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.getNumberBtn.setOnClickListener {
            binding.numberFactView.text = "abra cadabra"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}