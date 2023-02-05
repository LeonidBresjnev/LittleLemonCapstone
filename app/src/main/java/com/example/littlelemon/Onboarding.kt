package com.example.littlelemon

import android.content.ContentValues
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

@Composable
fun Onboarding(navController: NavController, profile: SharedPreferences, login: () -> Unit) {

    Log.d(ContentValues.TAG, "Onboard")
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var isclicked by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }

    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.green))
        ) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                color = colorResource(id = R.color.highlight1),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.letsgo)
            )
        }

        Text(
            fontWeight = FontWeight.Bold,
            text = "Personal information"
        )
        OutlinedTextField(
            modifier = Modifier
                .align(alignment = CenterHorizontally)
                .fillMaxWidth(0.75f),
            value = firstname,
            onValueChange = { firstname = it },
            placeholder = { Text(stringResource(R.string.adam)) },
            label = { Text(stringResource(R.string.firstname)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .align(alignment = CenterHorizontally)
                .fillMaxWidth(0.75f),
            value = lastname,
            onValueChange = { lastname = it },
            placeholder = { Text(stringResource(R.string.smith)) },
            label = { Text(stringResource(R.string.lastname)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .align(alignment = CenterHorizontally)
                .fillMaxWidth(0.75f),
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("$firstname@$lastname.com") },
            label = { Text(stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )

        Button(
            modifier = Modifier
                .align(alignment = CenterHorizontally)
                .fillMaxWidth(0.75f),
            onClick =
            {
                isclicked = true
                success = !(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty())

                if (success) {
                    login()

                    profile.edit()
                        .putString("firstname", firstname)
                        .putString("lastname", lastname)
                        .putString("email", email)
                        .apply()
                    navController.navigate(Home.route)
                }
            },
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.yellow)
            )
        ) {
            Text(
                text = "Register",
                color = colorResource(id = R.color.highlight1)
            )
        }

        if (isclicked) {
            Text(
                color = colorResource(id = if (success) R.color.green else R.color.purple_500),
                text = if (success) "Registration successful!" else "Registration unsuccessful. Please enter all data."
            )
        }
/*
        if (success) {
            login.invoke()
            success=false
            }*/

    }

}
