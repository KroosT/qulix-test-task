package by.kroos.gifsearch.Utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class ViewUtils {

    public static void showSoftKeyboard(final View view){
        if(view.requestFocus()){
            final InputMethodManager imm =(InputMethodManager) view.getContext().
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideSoftKeyboard(final View view){
        final InputMethodManager imm =(InputMethodManager) view.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
