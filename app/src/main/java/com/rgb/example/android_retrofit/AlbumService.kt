package com.rgb.example.android_retrofit

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getUserAlbums(@Query("userId") id: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") albumId: Int): Response<AlbumsItem>

    @POST("/albums")
    suspend fun addAlbum(@Body album : AlbumsItem) : Response<AlbumsItem>
}