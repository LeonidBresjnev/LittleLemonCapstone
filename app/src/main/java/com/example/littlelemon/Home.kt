package com.example.littlelemon

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Home(databaseitems: List<MenuItemRoom>) {

    var searchstring by remember { mutableStateOf("") }
    Log.d(ContentValues.TAG, "Home")

    Column(modifier = Modifier.fillMaxSize()) {
        Hero(searchstring = searchstring ,
            changesearchstring = { searchstring = it })

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = databaseitems,
                itemContent = { menuItem ->
                   //val glide = Glide.with(this)
                    Column {
                        Text(text = menuItem.title,
                        style = MaterialTheme.typography.h3,
                        color= MaterialTheme.colors.onSecondary)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                            Text(modifier = Modifier.fillMaxWidth(0.7f),
                                text=menuItem.description,
                                style = MaterialTheme.typography.body1)
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.body1,
                                text = "\$%.2f".format(menuItem.price.toDouble())
                            )


                            }
                            GlideImage(
                                model = menuItem.image,
                                contentDescription = "food picture"
                            )
                        }
                    }
                }
            )
        }
    }

}

