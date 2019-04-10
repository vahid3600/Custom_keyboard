package com.example.talaie.custom_keyboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        newCustomKeyboard.initCustomKeyboard(object :KeyboardListener{
            override fun textChanged(string: String) {
                mEditText.setText(string)
            }

            override fun directionChanged() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }
}
