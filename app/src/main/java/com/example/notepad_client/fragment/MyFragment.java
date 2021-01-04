package com.example.notepad_client.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notepad_client.R;
import com.example.notepad_client.activity.AboutActivity;
import com.example.notepad_client.activity.LoginActivity;
import com.example.notepad_client.utils.SpUtils;

public class MyFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;
    private TextView tv_name,tv_time,tv_about,tv_logout;


    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_time = rootView.findViewById(R.id.tv_time);
        tv_about = rootView.findViewById(R.id.tv_about);
        tv_logout = rootView.findViewById(R.id.tv_logout);
        initData();
        return rootView;
    }


    public void initData() {
        String name = SpUtils.getString(mContext,"name");
        String updatetime = SpUtils.getString(mContext,"updatetime");
        tv_name.setText(name);
        tv_time.setText(updatetime);
        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AboutActivity.class));

            }
        });
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("确定退出登录吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SpUtils.remove(mContext,"name");
                                SpUtils.remove(mContext,"updatetime");
                                startActivity(new Intent(mContext, LoginActivity.class));
                                getActivity().finish();
                            }

                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
}



