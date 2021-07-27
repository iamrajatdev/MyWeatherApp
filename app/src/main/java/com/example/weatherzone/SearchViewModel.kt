package com.example.weatherzone

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherzone.data.CityRepository
import com.example.weatherzone.model.Coord
import com.example.weatherzone.model.Main
import com.example.weatherzone.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel : ViewModel() {

    private val repository = CityRepository()
    val mutableLiveData = MutableLiveData<Resource<Main?>>()

    fun fetchCityByName(result: String?) {
        Log.d("TAG", "came inside")
        mutableLiveData.postValue(Resource.Loading())
        viewModelScope.launch {

            val getRepo = repository.getCity(result, "f96ed9798fb91167fc28886821cea342")
            getRepo.let {
                if (getRepo.isSuccessful) {
                    mutableLiveData.postValue(Resource.Success(getRepo.body()))
                }else{
                    mutableLiveData.postValue(Resource.Error("Invalid response"))
                }
            }
        }
    }
}