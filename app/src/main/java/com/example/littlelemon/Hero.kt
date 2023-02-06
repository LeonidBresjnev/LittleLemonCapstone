package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Hero(searchstring : String, changesearchstring : (String) -> Unit) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Little Lemon", color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h1
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier.fillMaxWidth(0.5f)
                ) {

                    Text(
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onPrimary,
                        text = "Chigago"
                    )
                    Text(
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1,
                        text = """We are a family-owned Mediterranean restaurant,
focused on traditional recipes served with a modern twist"""
                    )
                }
                Image(
                    modifier = Modifier
                        .scale(0.8f)
                        .clip(RoundedCornerShape(10.dp))
                        .aspectRatio(1f)
                        .fillMaxHeight(0.2f)
                        .fillMaxWidth(0.3f),
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = stringResource(R.string.profileDesc)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            TextField(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onPrimary),
                placeholder= { Text(text=stringResource(R.string.entersearchphrase)) },
                value = searchstring,
                enabled=true,
                readOnly = false,
                colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colors.onSecondary,
                placeholderColor = MaterialTheme.colors.secondary),
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                /*leadingIcon = {
                    Image(
                        contentDescription = "magnifier",
                        painter = painterResource(id = R.drawable.baseline_search_black_24)
                    )
                },*/
                onValueChange = changesearchstring,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
        }
}