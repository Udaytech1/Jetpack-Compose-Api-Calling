package com.example.movieapp.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.NetworkResponse
import com.example.movieapp.data.models.PostDataItem
import com.example.movieapp.data.repogistory.MovieRepogistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val movieRepogistory: MovieRepogistory): ViewModel() {

    init {
        callMovieApi()
    }

     fun callMovieApi() {
        viewModelScope.launch {
            movieRepogistory.getAllMovieList()
        }
    }


    val movieResponse :MutableStateFlow<NetworkResponse<List<PostDataItem>>>get() = movieRepogistory.mutableMovieDataState
}