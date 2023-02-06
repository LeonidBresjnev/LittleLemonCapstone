package com.example.littlelemon

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController


@Composable
fun Profile(navController: NavController, profile: SharedPreferences, logout: ()->Unit) {

    Log.d(TAG, "Profile")

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement= Arrangement.SpaceEvenly
     )
    {
        Text(
            fontWeight = FontWeight.Bold,
            text = "Personal information"
        )

        OutlinedTextField(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(0.75f),
            readOnly = true,
            value = profile.getString("firstname","")?:"",
            onValueChange = {  },
            label = { Text(stringResource(R.string.firstname)) }
        )
        OutlinedTextField(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(0.75f),
            readOnly = true,
            value = profile.getString("lastname","")?:"",
            onValueChange = {  },
            label = { Text(stringResource(R.string.lastname)) }
        )
        OutlinedTextField(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(0.75f),
            readOnly = true,
            value = profile.getString("email","")?:"",
            onValueChange = {  },
            label = { Text(stringResource(R.string.email)) }
        )
        Button(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .fillMaxWidth(0.75f),
            onClick =  {logout.invoke()
            profile.edit().putBoolean("loggedin", false).apply()
                navController.navigate(Onboarding.route)},
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.yellow)
            )
        ) {
            Text(
                text = "Log out",
                color = colorResource(id = R.color.highlight1)
            )
        }
    }
}