package com.rgb.example.android_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.rgb.example.android_retrofit.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private var albumService: AlbumService =
        RetrofitInstance.getInstance().create(AlbumService::class.java)
    private lateinit var mViewModel: RetrofitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this).get(RetrofitViewModel::class.java)

//        val responseLiveData: LiveData<Response<Albums>> = liveData {
//            val response = albumService.getAlbums()
//            emit(response)
//        }

//        responseLiveData.observe(this, Observer {
//            val albumsList = it.body()?.listIterator()
//            if(albumsList!=null){
//                while (albumsList.hasNext()){
//                    val albumsItem = albumsList.next()
//                    val result =" "+"Album Title : ${albumsItem.title}"+"\n"+
//                            " "+"Album id : ${albumsItem.id}"+"\n"+
//                            " "+"User id : ${albumsItem.userId}"+"\n\n\n"
//                    mBinding.albumsText.append(result)
//                }
//            }
//        })

//        mViewModel.getAlbums()
        mViewModel.getUserAlbums(2)
        mViewModel.getAlbum(3)

        mViewModel.readableAlbums.observe(this, Observer {
            if (it != null) {
                for (album in it) {
                    val result = " " + "Album Title : ${album.title}" + "\n" +
                            " " + "Album id : ${album.id}" + "\n" +
                            " " + "User id : ${album.userId}" + "\n\n\n"
                    mBinding.albumsText.append(result)
                }
            }
        })

        mViewModel.readableAlbumDetail.observe(this, Observer {
            val result = " " + "Album Title : ${it.title}" + "\n" +
                    " " + "Album id : ${it.id}" + "\n" +
                    " " + "User id : ${it.userId}" + "\n\n\n"
            Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()
        })


        val postResponse : LiveData<AlbumsItem> = liveData {
            kotlinx.coroutines.delay(6000)
            val albumAdded = albumService.addAlbum(AlbumsItem(0,"My new Title", 56)).body()
            emit(albumAdded!!)
        }

        postResponse.observe(this, Observer {
            val result = "New Album " + "Album Title : ${it.title}" + "\n" +
                    " " + "Album id : ${it.id}" + "\n" +
                    " " + "User id : ${it.userId}" + "\n\n\n"
            Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()
        })

    }
}