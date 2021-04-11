package com.rgb.example.android_retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class RetrofitViewModel : ViewModel() {

    private var albumService : AlbumService = RetrofitInstance.getInstance().create(AlbumService::class.java)
    private val albumsList = MutableLiveData<Albums>()

    val readableAlbums : LiveData<Albums>
        get() = albumsList

    private val albumDetail = MutableLiveData<AlbumsItem>()

    val readableAlbumDetail : LiveData<AlbumsItem>
        get() = albumDetail

    fun getAlbums(){
        viewModelScope.launch {
            delay(5000)
            val response = albumService.getAlbums()
            albumsList.value = response.body()
            Log.i("RETRO_D", "exit coroutine")
        }

    }

    fun getUserAlbums(id: Int){
        viewModelScope.launch {
            delay(5000)
            val response = albumService.getUserAlbums(id)
            albumsList.value = response.body()
            Log.i("RETRO_D", "exit coroutine")
        }

    }

    fun getAlbum(id: Int){
        viewModelScope.launch {
            val response = albumService.getAlbum(id)
            albumDetail.value = response.body()
            Log.i("RETRO_D", "exit coroutine 2")
        }

    }

}