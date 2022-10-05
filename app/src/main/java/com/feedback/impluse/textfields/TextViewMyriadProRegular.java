package com.feedback.impluse.textfields;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by ANMOL on 21-03-2018.
 */

public class TextViewMyriadProRegular extends AppCompatTextView
{
    private Context context;

    public TextViewMyriadProRegular(Context context, AttributeSet attrs)
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