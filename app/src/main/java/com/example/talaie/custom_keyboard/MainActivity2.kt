package com.example.talaie.custom_keyboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity1.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity2)

        customKeyboard.editTextInit(pin_edit_text)
        customKeyboard.editTextInit(edit_text)

    }
}
