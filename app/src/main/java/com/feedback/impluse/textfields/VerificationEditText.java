package com.feedback.impluse.textfields;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.appcompat.widget.AppCompatEditText;


public class VerificationEditText extends AppCompatEditText {
    private Context context;
    private KeyImeChange keyImeChangeListener;
    private HandleDismissingKeyboard handleDismissingKeyboard;

    public VerificationEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Myriad_Pro_Regular.ttf");
        setTypeface(typeface);
    }

    public void setKeyImeChangeListener(KeyImeChange listener) {
        keyImeChangeListener = listener;
    }

    public void setHandleDismissingKeyboard(HandleDismissingKeyboard handleDismissingKeyboard) {
        this.handleDismissingKeyboard = handleDismissingKeyboard;
    }

    public interface HandleDismissingKeyboard {
        public void dismissKeyboard();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        try {

            if (keyImeChangeListener != null) {
                keyImeChangeListener.onKeyIme(keyCode, event);
            }
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                handleDismissingKeyboard.dismissKeyboard();
                return true;
            }
            return super.dispatchKeyEvent(event);
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    public interface KeyImeChange {

        void onKeyIme(int keyCode, KeyEvent event);
    }
}
