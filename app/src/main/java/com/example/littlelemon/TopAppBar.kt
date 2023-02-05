package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopAppBar(navController: NavController, loggedin: Boolean, logout: ()-> Unit) {

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        {
            Image(alignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(1f),
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.logoDesc)
            )
            if (loggedin) {
                Image(modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        navController.navigate(Profile.route) },
                    painter = painterResource(R.drawable.profile),
                    contentDescription = stringResource(R.string.profileDesc)
                )
            }
        }
    }
}