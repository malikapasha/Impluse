package com.feedback.impluse.textfields;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class TextViewMyriadProSemiBold extends AppCompatTextView
{
    private Context context;

    public TextViewMyriadProSemiBold(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        this.context=context;
        init();
    }

    private void init()
    {
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "Myriad_Pro_Semibold.ttf");
        setTypeface(typeface);
    }
}
