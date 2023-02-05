package com.example.littlelemon

interface Destinations {
    val route: String
}

object Onboard : Destinations {
    override val route = "Onboard"
}
