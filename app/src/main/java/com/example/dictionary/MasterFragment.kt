package com.example.dictionary

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dictionary.databinding.FragmentMasterBinding
import com.example.dictionary.databinding.FragmentSearchBinding

class MasterFragment : Fragment() {

    private lateinit var binding: FragmentMasterBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CustomAdapter(viewModel.listAccount)
        binding.recyclerview.adapter = adapter


        viewModel.list?.observe(requireActivity()){
            it.forEach {
                //خب اینجا تو حلقه لیست اکانت هارو بهت میده
                //binding.tvAccountNum.text=it.accountNumber.toString()
                viewModel.listAccount.add(it)
            }
        }
    }
}