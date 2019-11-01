package dev.craftid.dicodingjetpack.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class EventUtils {

    private Activity activity;

    public EventUtils(Activity activity) {
        this.activity = activity;
    }

    public void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        }
    }

}
