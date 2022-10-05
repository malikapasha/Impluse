package com.feedback.impluse.textfields;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class TextViewMyriadProBold extends AppCompatTextView
{
    private Context context;

    public TextViewMyriadProBold(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        this.context=context;
        init();
    }

    private void init()
    {
        Typeface typeface= Typeface.createFromAsset(context.getAssets(), "Myriad_Pro_Bold.ttf");
        setTypeface(typeface);
    }
}
