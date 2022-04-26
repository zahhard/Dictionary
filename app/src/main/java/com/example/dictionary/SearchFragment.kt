package com.example.dictionary

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dictionary.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    lateinit var ppreferences: SharedPreferences
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
        ppreferences =  requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        binding.button2.setOnClickListener {
            search()
        }

        binding.btnEdit.setOnClickListener {
            val editor: SharedPreferences.Editor = ppreferences.edit()
            editor.putBoolean("isRemember", true)
            editor.putString("word", binding.tvWord.text.toString())
            editor.putString("meaning", binding.tvMeaning.text.toString())
            editor.putString("example", binding.tvExample.text.toString())
            editor.putString("synonym", binding.tvSynonym.text.toString())
            editor.apply()
            findNavController().navigate(R.id.action_searchFragment_to_addNewWordFragment)
        }

        val countObserver = Observer<WordEntity> { word ->
            binding.tvWord.text = word.word.toString()
            binding.tvMeaning.text = word.meaning.toString()
            binding.tvExample.text = word.example.toString()
            binding.tvSynonym.text = word.synonym.toString()
        }

        // viewModel.search(searchText)?.observe(viewLifecycleOwner, countObserver)
    }

    private fun search() {
        searchText = binding.editTextTextPersonName.text.toString()
        when {
            viewModel.search(searchText)?.word != null -> {
                binding.tvWord.text = viewModel.search(searchText)?.word.toString()
                binding.tvMeaning.text = viewModel.search(searchText)?.meaning.toString()
                binding.tvExample.text = viewModel.search(searchText)?.example.toString()
                binding.tvSynonym.text = viewModel.search(searchText)?.synonym.toString()
               // binding.tvUrl.text = viewModel.search(searchText)?.url.toString()
            }
            viewModel.searchInPersian(searchText)?.word != null -> {
                binding.tvWord.text = viewModel.searchInPersian(searchText)?.word.toString()
                binding.tvMeaning.text = viewModel.searchInPersian(searchText)?.meaning.toString()
                binding.tvExample.text = viewModel.searchInPersian(searchText)?.example.toString()
                binding.tvSynonym.text = viewModel.searchInPersian(searchText)?.synonym.toString()
               // binding.tvUrl.text = viewModel.searchInPersian(searchText)?.url.toString()
            }
            else -> {
                binding.editTextTextPersonName.text = null
                AlertDialog.Builder(requireContext())
                    .setMessage("Not found !")
                    .setPositiveButton("ok") { _, _ -> }
                    .setCancelable(false)
                    .show()
            }
        }
    }

//    private fun nullCheck() {
//        if (viewModel.sea)
//    }
}