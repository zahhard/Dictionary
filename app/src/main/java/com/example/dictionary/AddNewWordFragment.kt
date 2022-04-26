package com.example.dictionary

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dictionary.databinding.FragmentAddNewWordBinding

class AddNewWordFragment : Fragment() {
    private lateinit var binding: FragmentAddNewWordBinding
    lateinit var sharedPreferences: SharedPreferences
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences =
            requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_addNewWordFragment_to_searchFragment)
        }
        binding.button.setOnClickListener {
            if (checkValidate() && !sharedPreferences.getBoolean("isRemember", false)) {
                addToDatabase(
                    binding.tfWord.text.toString(),
                    binding.tfMeaning.text.toString(),
                    binding.tfSynonym.text.toString(),
                    binding.tfMeaning.text.toString(),
                  //  binding.tfUrl.text.toString()
                )
            }
            if (sharedPreferences.getBoolean("isRemember", false)){
                Toast.makeText(requireContext(), "dd", Toast.LENGTH_SHORT).show()
                viewModel.update(WordEntity(
                    binding.tfWord.text.toString(),
                    binding.tfMeaning.text.toString(),
                    binding.tfExample.text.toString()
                    ,binding.tfSynonym.text.toString()
                ))
                findNavController().navigate(R.id.action_addNewWordFragment_to_searchFragment)
            }
        }

        observeAll()

        checkToEdit()
    }

    private fun checkToEdit() {
        if (sharedPreferences.getBoolean("isRemember", false)) {
            if (sharedPreferences.contains("word"))
                binding.tfWord.setText(sharedPreferences.getString("word", ""))
            if (sharedPreferences.contains("meaning"))
                binding.tfMeaning.setText(sharedPreferences.getString("meaning", ""))
            if (sharedPreferences.contains("example"))
                binding.tfExample.setText(sharedPreferences.getString("example", ""))
            if (sharedPreferences.contains("synonym"))
                binding.tfSynonym.setText(sharedPreferences.getString("synonym", ""))
        }
    }

    private fun observeAll() {
        val countObserver = Observer<Int> { count ->
            binding.tvCount.text = count.toString()
        }

        viewModel.countLiveData?.observe(viewLifecycleOwner, countObserver)
    }

    private fun addToDatabase(word: String, meaning: String, example: String, synonym: String//, url : String
    ) {
        viewModel.addAccountToDatabase(word, meaning, example, synonym//, url
        )
    }

    private fun checkValidate(): Boolean {
        if (binding.tfWord.text.isNullOrBlank()) {
            binding.tfWord.error = ""
            return false
        }
        if (binding.tfMeaning.text.isNullOrBlank()) {
            binding.tfMeaning.error = ""
            return false
        }
        if (binding.tfExample.text.isNullOrBlank()) {
            binding.tfExample.error = ""
            return false
        }
        if (binding.tfSynonym.text.isNullOrBlank()) {
            binding.tfSynonym.error = ""
            return false
        }
        return true
    }

}