package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, profile: SharedPreferences, isloggedin: Boolean, logout: ()-> Unit, login:  ()-> Unit,
               databaseitems: List<MenuItemRoom>) {


    NavHost(
        navController = navController,
        startDestination =  Onboarding.route
    ) {
        composable(Home.route) {
            Home(databaseitems = databaseitems)
        }

        composable(Onboarding.route) {
            Onboarding(navController=navController, profile = profile, login = login)
        }

        composable(Profile.route  ) {
            Profile(navController=navController, profile = profile, logout = logout)
        }

    }
}