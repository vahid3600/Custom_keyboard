package com.example.talaie.custom_keyboard

interface KeyboardListener {
    fun textChanged(string: String)

    fun directionChanged()
}