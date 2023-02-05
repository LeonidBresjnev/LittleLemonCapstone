package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Hero() {
    var searchstring = ""
    Box(modifier = Modifier.background(color = colorResource(id = R.color.green))) {
        Column(modifier=Modifier.padding(start=8.dp, end= 8.dp,top=8.dp, bottom = 8.dp)) {
            Text(color = colorResource(id = R.color.yellow), text = "Little Lemon")

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    Modifier.fillMaxWidth(0.5f)) {

                    Text(color = colorResource(id = R.color.highlight1), text = "Chigago")
                    Text(
                        color = colorResource(id = R.color.highlight1),
                        text = """We are a family-owned Mediterranean restaurant,
             focused on traditional recipes served with a modern twist"""
                    )
                }
                Image(modifier = Modifier.fillMaxWidth(0.5f),
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = stringResource(R.string.profileDesc)
                )
            }
            TextField(modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.highlight1)),
                value = searchstring ,
                readOnly = false,
                leadingIcon = {Image(contentDescription="magnifier",painter= painterResource(id = R.drawable.baseline_search_black_24))},
                onValueChange = {searchstring = it})
        }
    }
}