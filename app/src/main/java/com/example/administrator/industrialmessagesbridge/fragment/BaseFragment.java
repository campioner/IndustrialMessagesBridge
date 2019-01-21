package com.example.administrator.industrialmessagesbridge.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.lang.reflect.Type;

public class BaseFragment extends Fragment {
    public BaseFragment() {
        super();
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
