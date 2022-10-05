package com.feedback.impluse.textfields;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;


public class EditTextMyriadProRegular extends AppCompatEditText
{
    private Context context;

    public EditTextMyriadProRegular(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        this.context=context;
        init();
    }

    private void init()
    {
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "Myriad_Pro_Regular.ttf");
        setTypeface(typeface);
    }

}