package com.example.littlelemon

import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get(
                "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
            .menu
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "lemonsmenu").build()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val databaseMenuItems by database
                    .menuItemDao()
                    .getAll()
                    .observeAsState(emptyList())

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppScreen(sharedPreferences , databaseMenuItems)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val mymenu = fetchMenu()
                Log.d(ContentValues.TAG,"data fetchet!")
                Log.d(ContentValues.TAG,"${mymenu.size}")
                if (database.menuItemDao().isEmpty()) {
                    Log.d(ContentValues.TAG,"database empty!")
                    val menuItemsRoom = mymenu.map { it.toMenuItemRoom() }
                    Log.d(ContentValues.TAG,"data mapped!")
                    database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
                    Log.d(ContentValues.TAG,"data inserted!")

                }
            }
            catch (e: Exception) {
                Log.d(ContentValues.TAG,e.message?:"")
                //exitProcess(0)
            }
        }
    }
}

@Composable
private fun AppScreen(profile: SharedPreferences , databaseitems: List<MenuItemRoom>) {
    val navController = rememberNavController()
    var isloggedin by remember{ mutableStateOf(value=false) }

    val logout : () -> Unit = {isloggedin = false}
    val login : () -> Unit = { isloggedin = true }

        Scaffold(
        topBar = {
            TopAppBar(navController, isloggedin)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Navigation(navController=navController,
                profile=profile, login=login,
                logout=logout,
                isloggedin = isloggedin ,
                databaseitems=databaseitems
            )
        }
    }
}


