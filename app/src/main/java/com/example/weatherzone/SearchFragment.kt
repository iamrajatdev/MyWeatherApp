package com.example.weatherzone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.SearchFragmentBinding
import com.example.weatherzone.utils.Resource


class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false);
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.fetchCityByName(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchViewModel.mutableLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.temp.text = it.data?.temp.toString()
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Error -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }
}