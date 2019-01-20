package com.yara.customkeyboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.example.talaie.custom_keyboard.BasicOnKeyboardActionListener
import com.example.talaie.custom_keyboard.R
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        editText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (!editText.hasFocus())
                    editText.requestFocus()
                return false
            }
        })

        pin_edit_text

        pin_edit_text.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (!pin_edit_text.hasFocus())
                    pin_edit_text.requestFocus()
                return true
            }
        })

        keyboard_view.setOnKeyboardActionListener(
            BasicOnKeyboardActionListener(this)
        )
    }
}