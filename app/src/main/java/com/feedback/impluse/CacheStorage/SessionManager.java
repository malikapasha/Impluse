package com.feedback.impluse.CacheStorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.feedback.impluse.UserInfoClassData;
import com.google.gson.Gson;


public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "tracker";

    private static final String ACCESS_TOKEN = "accesstoken";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_SELECTED_ITEM = "selecteditem";

    private static final String VERIFYING_CONTACT = "vcontact";

    private static final String BIG_USER_INFO = "userinfo";



    private static final String ALL_NOTIFICATIONS_DATA_LAST = "allnotifications";




    private static final String Key_LANGUAGE_ENGLISH = "languageenglish";




    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setKey_LANGUAGE_ENGLISH(boolean isenglish) {
        editor.putBoolean(Key_LANGUAGE_ENGLISH, isenglish);
        // commit changes
        editor.commit();

    }

    public boolean iskeylanguageenglish(){
        return pref.getBoolean(Key_LANGUAGE_ENGLISH, true);
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setAccessToken(String token)
    {
        editor.putString(ACCESS_TOKEN, token);
        // commit changes
        editor.commit();
        Log.d(TAG, "Contact "+token);
    }

    public String getAccessToken()
    {
        return pref.getString(ACCESS_TOKEN,"+92000-0000000");
    }

    public void setVerifyingContact(String contact)
    {
        editor.putString(VERIFYING_CONTACT, contact);
        // commit changes
        editor.commit();
        Log.d(TAG, "Contact "+contact);
    }

    public String getVerifyingContact()
    {
        return pref.getString(VERIFYING_CONTACT,"0");
    }

    public void setkeyBigUserinfo(UserInfoClassData userdata) {

        editor.remove(BIG_USER_INFO);

        Gson gson = new Gson();
        String json = gson.toJson(userdata);
        editor.putString(BIG_USER_INFO, json);
        editor.commit();

        Log.d("Check: ", "ed! "+userdata.getCell());
    }

    public UserInfoClassData getkeybiguserinfo() {
        Gson gson = new Gson();
        String json = pref.getString(BIG_USER_INFO, "null");

        UserInfoClassData teamData = gson.fromJson(json, UserInfoClassData.class);

        return teamData;
    }




}