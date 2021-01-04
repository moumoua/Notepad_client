package com.example.notepad_client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public Context mContext;

    private boolean mIsAddedView;
    private View mNightView = null;
    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mNightViewParam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
//        StateAppBar.setStatusBarColor(this.getActivity(),
//                ContextCompat.getColor(mContext,
//                        R.color.grayccc));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public final Gson getGson(){
        return GsonSingleton.gson;
    }

    public static class GsonSingleton{
        private static final Gson gson = new Gson();
    }

}
