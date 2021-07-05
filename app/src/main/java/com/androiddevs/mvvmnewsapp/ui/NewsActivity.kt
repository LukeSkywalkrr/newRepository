package com.androiddevs.mvvmnewsapp.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.ui.fragments.BreakingNewsFragment

import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    lateinit var notfin : NotificationManagerCompat
    lateinit var not : Notification
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelname"
    val NOTIFICATION_ID =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())



        createNot()
        not = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("sssd")
            .setContentText("sdddddd")
            .setSmallIcon(R.drawable.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

         notfin = NotificationManagerCompat.from(this)




        startUpdates()

    }
    val scope = MainScope() // could also use an other scope such as viewModelScope if available
    var job: Job? = null
    fun stopUpdates() {
        job?.cancel()
        job = null
    }
    fun startUpdates() {
        stopUpdates()
        job = scope.launch {
            while(viewModel.cont) {
                viewModel.test(viewModel.location,viewModel.dat)
                notfin.notify(NOTIFICATION_ID,not)// the function that should be ran every second
                delay(5000)
            }
        }

    }








    fun createNot()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel (channel)
    }}


}
