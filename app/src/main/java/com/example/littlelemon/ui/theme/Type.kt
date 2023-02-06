package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val markazi = FontFamily(
    Font(R.font.markazitextmedium)
)
val karla = FontFamily(
    Font(R.font.karlaregular)
)
// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = markazi,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    h2 = TextStyle(
        fontFamily = markazi,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
),
    h3 = TextStyle(
        fontFamily = karla,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    body1 = TextStyle(
        fontFamily = karla,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp),

        subtitle1 = TextStyle(
            fontFamily = karla,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
)
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)