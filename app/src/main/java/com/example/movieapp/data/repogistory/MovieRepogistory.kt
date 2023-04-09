package com.example.movieapp.data.repogistory

import android.content.ContentValues.TAG
import android.util.Log
import com.example.movieapp.data.models.NetworkResponse
import com.example.movieapp.data.models.PostDataItem
import com.example.movieapp.data.network_layer.RetrofitConnection
import kotlinx.coroutines.flow.MutableStateFlow

class MovieRepogistory() {
    val mutableMovieDataState: MutableStateFlow<NetworkResponse<List<PostDataItem>>> = MutableStateFlow(NetworkResponse.Loading())
    suspend fun getAllMovieList(){
        mutableMovieDataState.emit(NetworkResponse.Loading())
        val response = RetrofitConnection.getInstance().getAllMovie()
        if (response.code()==200){
            Log.d(TAG, "Api response : onSuccess(), ${response.body()}")
            mutableMovieDataState.emit(NetworkResponse.Success(response.body()))

        }else{
            mutableMovieDataState.emit(NetworkResponse.Error(null,
                response.errorBody().toString()
            ))
            Log.d(TAG, "Api response : onFailure(), ${response.errorBody()}")
        }
    }
}