package com.example.talaie.custom_keyboard

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.text.Editable
import android.util.AttributeSet
import android.widget.EditText


/**
 * c
 */
class PinEntryEditText : EditText {

    internal var mSpace = 24f //24 dp by default
    internal var mCharSize = 0f
    internal var mNumChars = 5f
    internal var mLineSpacing = 8f //8dp by default

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context, attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context, attrs: AttributeSet,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        setBackgroundResource(0)

        val multi = context.resources.displayMetrics.density
        mSpace = multi * mSpace //convert to pixels for our density
        mLineSpacing = multi * mLineSpacing //convert to pixels
        val mMaxLength = attrs.getAttributeIntValue(
            " ", "maxLength", 5
        ).toFloat()
        mNumChars = mMaxLength

    }

    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas);


        val availableWidth = width - paddingRight - paddingLeft
        if (mSpace < 0) {
            mCharSize = availableWidth / (mNumChars * 2 - 1)
        } else {
            mCharSize = (availableWidth - mSpace * (mNumChars - 1)) / mNumChars
        }

        var startX = paddingLeft
        val bottom = height - paddingBottom

        //Text Width
        val text = text

        val textLength = text.length

        val textWidths = FloatArray(textLength)

        paint.getTextWidths(getText(), 0, textLength, textWidths)

        var i = 0
        while (i < mNumChars) {

            canvas.drawLine(
                startX.toFloat(), bottom.toFloat(), startX + mCharSize, bottom.toFloat(), paint
            )

            if (getText().length > i) {
                val middle = startX + mCharSize / 2
                canvas.drawText(
                    text,
                    i,
                    i + 1,
                    middle - textWidths[0] / 2,
                    bottom - mLineSpacing,
                    paint
                )
            }

            if (mSpace < 0) {
                startX += (mCharSize * 2).toInt()
            } else {
                startX += (mCharSize + mSpace).toInt()
            }
            i++
        }
    }
}
