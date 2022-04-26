package com.example.dictionary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.dictionary.databinding.FragmentAddNewWordBinding
import com.example.dictionary.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: MainViewModel by viewModels()
    var searchText = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            searchText = binding.editTextTextPersonName.text.toString()
            binding.tvWord.text = viewModel.search(searchText)?.word.toString()
            binding.tvMeaning.text = viewModel.search(searchText)?.meaning.toString()
            binding.tvExample.text = viewModel.search(searchText)?.example.toString()
            binding.tvSynonym.text = viewModel.search(searchText)?.synonym.toString()
        }

        val countObserver = Observer<WordEntity> { word ->
            binding.tvWord.text = word.word.toString()
            binding.tvMeaning.text = word.meaning.toString()
            binding.tvExample.text = word.example.toString()
            binding.tvSynonym.text = word.synonym.toString()
        }

       // viewModel.search(searchText)?.observe(viewLifecycleOwner, countObserver)
    }
}