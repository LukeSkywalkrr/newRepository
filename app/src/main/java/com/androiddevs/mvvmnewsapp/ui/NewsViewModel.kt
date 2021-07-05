package com.androiddevs.mvvmnewsapp.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.Covid
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import retrofit2.Response
import java.time.Duration
import java.util.EnumSet.range
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var location ="512"
    var dat ="03-07-2021"
    var cont = true

    init {
       // getBreakingNews("us")

       // test("512","03-07-2021")
    }




    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        //breakingNews.postValue(handleBreakingNewsResponse(response))
    }
   fun test(dis :String , dte : String) =viewModelScope.launch {
       val res = newsRepository.getcov(dis,dte)

        val a = handleBreakingNewsResponse(res)
       for (i in a.data?.sessions!!)
       {
           if(i.available_capacity == 0)
           {
               Log.i("Zero Capacity",i.address)
           }
       }


       {

       }
//       val st= a.data?.sessions?.get(1)?.district_name
//       if(st=="Alwa")
//       {
//           Log.i("XXXXX",st)
//       }


   }

    private fun handleBreakingNewsResponse(response: Response<Covid>) : Resource<Covid> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }






}


