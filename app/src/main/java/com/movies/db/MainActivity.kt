package com.movies.db

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.movies.db.app.navigation.NavGraphApp
import com.movies.db.movies.domain.events.DomainEvent
import com.movies.db.movies.domain.events.DomainEventListener
import com.movies.db.movies.domain.events.EventManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), DomainEventListener {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventManager.addListener(this)

        setContent {
            val navController = rememberNavController()
            NavGraphApp(navController = navController)
        }
    }

    override fun onEventOccurred(event: DomainEvent) {

        event.execute();
//        when (event.eventType) {
//            "GetNowPlayingUseCase" -> {
//                val eventData = event.eventData
//                // Manejar el evento
//                // Por ejemplo, actualizar la interfaz de usuario con los datos del evento
//
//                Log.i("onEventOccurred", "${eventData}")
//            }
//        }
    }
}


