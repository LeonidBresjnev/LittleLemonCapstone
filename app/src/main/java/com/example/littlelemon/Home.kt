package com.example.littlelemon

import android.content.ContentValues
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Home(profile: SharedPreferences, isloggedin: Boolean = false) {
    Log.d(ContentValues.TAG, "Home")
    Hero()

}