package com.example.notepad_client.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notepad_client.R;
import com.example.notepad_client.activity.InAndExActivity;
import com.example.notepad_client.utils.SpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;
    private Button add_srzc;
    private TextView tv_srze,tv_zcze,tv_hj,tv_today,tv_thismonth,tv_year;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        add_srzc = rootView.findViewById(R.id.add_srzc);
        tv_srze = rootView.findViewById(R.id.tv_srze);
        tv_zcze = rootView.findViewById(R.id.tv_zcze);
        tv_hj = rootView.findViewById(R.id.tv_hj);
        tv_today = rootView.findViewById(R.id.tv_today);
        tv_thismonth = rootView.findViewById(R.id.tv_thismonth);
        tv_year = rootView.findViewById(R.id.tv_year);
        add_srzc.setOnClickListener(this);
        return rootView;
    }

    public void initData() {
        String zfb_zc = SpUtils.getString(mContext,"支出支付宝");
        String yhk_zc = SpUtils.getString(mContext,"支出银行卡");
        String wx_zc = SpUtils.getString(mContext,"支出微信");

        String zfb_sr = SpUtils.getString(mContext,"收入支付宝");
        String yhk_sr = SpUtils.getString(mContext,"收入银行卡");
        String wx_sr = SpUtils.getString(mContext,"收入微信");
        Log.e("TAG","zfb_zc-"+zfb_zc+","+yhk_zc+","+wx_zc);
        Log.e("TAG","zfb_sr-"+zfb_sr+","+yhk_sr+","+wx_sr);
        double zfbzc = 0,wxzc = 0,yhkzc = 0,zfbsr = 0,wxsr = 0,yhksr = 0;
        if(!zfb_zc.isEmpty()){
            zfbzc = Double.parseDouble(zfb_zc);
        }
        if(!yhk_zc.isEmpty()){
            yhkzc = Double.parseDouble(yhk_zc);
        }
        if(!wx_zc.isEmpty()){
            wxzc = Double.parseDouble(wx_zc);
        }

        if(!zfb_sr.isEmpty()){
            zfbsr = Double.parseDouble(zfb_sr);
        }
        if(!yhk_sr.isEmpty()){
            yhksr = Double.parseDouble(yhk_sr);
        }
        if(!wx_sr.isEmpty()){
            wxsr = Double.parseDouble(wx_sr);
        }
        Log.e("TAG","收入总额-"+zfbzc+yhkzc+wxzc);
        String zcze = String.valueOf(zfbzc+yhkzc+wxzc);
        String srze = String.valueOf(zfbsr+yhksr+wxsr);
        String hj = String.valueOf(zfbzc+yhkzc+wxzc+zfbsr+yhksr+wxsr);
        tv_srze.setText("收入总额："+srze);
        tv_zcze.setText("支出总额："+zcze);
        tv_hj.setText("收支合计："+hj);

        SimpleDateFormat shijian = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat nian = new SimpleDateFormat("yyyy");
        String date = shijian.format(new Date());
        String daterq = riqi.format(new Date());
        String datenian = nian.format(new Date());
        String sptime = SpUtils.getString(mContext,date);
        String sptimerq = SpUtils.getString(mContext,daterq);
        String sptimenian = SpUtils.getString(mContext,datenian);
        tv_today.setText("今天合计："+sptime);
        tv_thismonth.setText("本月合计："+sptimerq);
        tv_year.setText("本年合计："+sptimenian);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_srzc:
                startActivity(new Intent(mContext, InAndExActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}

