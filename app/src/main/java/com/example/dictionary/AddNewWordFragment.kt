package com.example.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.dictionary.databinding.FragmentAddNewWordBinding
import java.util.*

class AddNewWordFragment : Fragment() {
    private lateinit var binding: FragmentAddNewWordBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewWordBinding.inflate(inflater, container, false)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            if (checkValidate())
                Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show()
                 addToDatabase(binding.tfWord.text.toString(), binding.tfMeaning.text.toString(), binding.tfSynonym.text.toString(), binding.tfMeaning.text.toString())
        }

        observeAll()
    }

    private fun observeAll() {
        val countObserver = Observer<Int> { count ->
            binding.textView3.text = count.toString()
        }

        viewModel.countLiveData?.observe(viewLifecycleOwner, countObserver)
    }

    private fun addToDatabase(word: String, meaning: String, example: String, synonym: String) {
        viewModel.addAccountToDatabase(word, meaning, example, synonym)
    }

    private fun checkValidate(): Boolean {
        if (binding.tfWord.text.isNullOrBlank()){
            binding.tfWord.error = ""
            return false}
        if (binding.tfMeaning.text.isNullOrBlank()){
            binding.tfMeaning.error = ""
             return false}
        if (binding.tfExample.text.isNullOrBlank()){
            binding.tfExample.error = ""
            return false}
        if (binding.tfSynonym.text.isNullOrBlank()){
            binding.tfSynonym.error = ""
            return false}
        return true
    }

}