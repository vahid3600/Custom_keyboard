package com.example.talaie.custom_keyboard

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.EditText
import android.widget.TextView
import android.text.InputType
import kotlinx.android.synthetic.main.custom_keyboard.view.*


class CustomKeyboard : LinearLayout, View.OnClickListener {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context, attributeSet, defStyleAttr, defStyleRes
    )

    val view: View
    lateinit var editTextList: ArrayList<EditText>

    companion object {
        val TAG = "CustomKeyboard"
    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_keyboard, null)
        view.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        initViews()

        editTextList = ArrayList()
        Log.e("CustomKeyboard", "init " + super.getChildCount() + " " + view.height)
        super.addView(view)

        val textview = TextView(context)
        textview.text = "asdasdsad"

        super.addView(textview)

        Log.e("CustomKeyboard", "init " + super.getChildCount())

    }

    private fun initViews() {
        view.one.setOnClickListener(this)
        view.two.setOnClickListener(this)
        view.three.setOnClickListener(this)
        view.four.setOnClickListener(this)
        view.five.setOnClickListener(this)
        view.six.setOnClickListener(this)
        view.seven.setOnClickListener(this)
        view.eight.setOnClickListener(this)
        view.nine.setOnClickListener(this)
        view.zero.setOnClickListener(this)
        view.delete.setOnClickListener(this)
    }

    fun editTextInit(editText: EditText) {
        editTextList.add(editText)

        disableSoftInputFromAppearing(editText)
//        editText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
//            Log.e(TAG, "onFocusChange")
//        }
//
//        editText.setOnClickListener(object : OnClickListener {
//            override fun onClick(v: View?) {
//                Log.e(TAG, "OnClickListener")
//            }
//
//        })
    }

    override fun onClick(v: View?) {

        Log.e("CustomKeyboard", "onClick " + view.id + " " + one.id)
        when (v?.id) {
            one.id -> writeText(resources.getString(R.string.one))
            two.id -> writeText(resources.getString(R.string.two))
            three.id -> writeText(resources.getString(R.string.three))
            four.id -> writeText(resources.getString(R.string.four))
            five.id -> writeText(resources.getString(R.string.five))
            six.id -> writeText(resources.getString(R.string.six))
            seven.id -> writeText(resources.getString(R.string.seven))
            eight.id -> writeText(resources.getString(R.string.eight))
            nine.id -> writeText(resources.getString(R.string.nine))
            zero.id -> writeText(resources.getString(R.string.zero))
            delete.id -> deleteText()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun writeText(string: String) {

        for (editText: EditText in editTextList) {
            if (editText.hasFocus()) {

                val cursorPosition = editText?.selectionStart
                val beforeCursorString = editText?.selectionEnd?.let { editText?.text?.substring(0, it) }
                val afterCursorString = editText?.selectionEnd?.let {
                    editText?.text?.length?.let { it1 -> editText?.text?.substring(it, it1) }
                }

                editText?.setText(
                    beforeCursorString +
                            string +
                            afterCursorString
                )
                cursorPosition?.let { editText?.setSelection(it + 1) }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun deleteText() {

        for (editText: EditText in editTextList) {
            if (editText.hasFocus()) {

                val cursorPosition = editText?.selectionStart
                var beforeCursorString: String? = ""
                var afterCursorString: String? = ""
                if (cursorPosition != 0)
                    beforeCursorString = editText?.selectionEnd?.let { editText?.text?.substring(0, it - 1) }
                if (cursorPosition != 0)
                    afterCursorString = editText?.selectionEnd?.let {
                        editText?.text?.length?.let { it1 -> editText?.text?.substring(it, it1) }
                    }

                editText?.setText(
                    beforeCursorString +
                            afterCursorString
                )
                if (cursorPosition != 0)
                    cursorPosition?.let { editText?.setSelection(it - 1) }
            }
        }
    }

    private fun disableSoftInputFromAppearing(editText: EditText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
            editText.setTextIsSelectable(true)
        } else {
            editText.setRawInputType(InputType.TYPE_NULL)
            editText.isFocusable = true
        }
    }

}