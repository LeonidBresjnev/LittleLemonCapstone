package com.example.littlelemon

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Home(databaseitems: List<MenuItemRoom>) {

    var searchstring by remember { mutableStateOf("") }
    Log.d(ContentValues.TAG, "Home")
    var menuItems = if (searchstring.isNotEmpty()) {
        databaseitems.filter{ it.title.contains(searchstring,ignoreCase=true) }
    } else {
    databaseitems
   }

    //if category is 0, then show all,
    // if category is not 0, then show specific category
    var category by remember { mutableStateOf(value=0) }
    if (category != 0) {
        menuItems=menuItems.filter {it.category == when (category) {
            1 -> "starters"
            2 -> "mains"
            3 -> "desserts"
            4 -> "drinks"
            else -> ""
        } }
    }

    Column {
        Hero(searchstring = searchstring ,
            changesearchstring = { searchstring = it })

        Column {
            Text(modifier=Modifier
                .align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h3,
                text = stringResource(R.string.orderfordelivery))

            Row(modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {

                Button(colors = ButtonDefaults.buttonColors(if (category == 1) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
                    onClick = { category = if (category != 1) 1 else 0  }) {
Text(modifier = Modifier.background(if (category == 1) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
    text="Starters")
                }
                Button(colors = ButtonDefaults.buttonColors(if (category == 2) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
                    onClick = { category = if (category != 2) 2 else 0 }) {
                    Text(modifier = Modifier.background(if (category == 2) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary), text="Mains")
                }
                Button(colors = ButtonDefaults.buttonColors(if (category == 3) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
                    onClick = { category = if (category != 3) 3 else 0 }) {
                    Text(modifier = Modifier.background(if (category ==3) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary), text="Dessert")
                }
                Button(colors = ButtonDefaults.buttonColors(if (category == 4) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary),
                    onClick = { category = if (category !=4) 4 else 0 }) {
                    Text(modifier = Modifier.background(if (category == 4) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary), text="Drinks")
                }
            }
        }

        Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp), contentPadding = PaddingValues(5.dp)
        ) {
            items(
                items = menuItems,
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

                        Divider(color = MaterialTheme.colors.onPrimary, thickness = 1.dp)
                    }
                }
            )
        }
    }

}
