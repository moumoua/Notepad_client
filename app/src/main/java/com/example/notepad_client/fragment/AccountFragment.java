package com.example.notepad_client.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notepad_client.R;
import com.example.notepad_client.activity.LoginActivity;
import com.example.notepad_client.utils.SpUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

public class AccountFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SHOW_TEXT = "text";

    private String mContentText;
    private ImageView img_addtype;
    private TextView tv_zfb,tv_yhk,tv_wx,tv_total;


    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static AccountFragment newInstance(String param1) {
        AccountFragment fragment = new AccountFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        img_addtype = rootView.findViewById(R.id.img_addtype);
        tv_zfb = rootView.findViewById(R.id.tv_zfb);
        tv_yhk = rootView.findViewById(R.id.tv_yhk);
        tv_wx = rootView.findViewById(R.id.tv_wx);
        tv_total = rootView.findViewById(R.id.tv_total);
        String zfb = SpUtils.getString(mContext,"zfb");
        String yhk = SpUtils.getString(mContext,"yhk");
        String wx = SpUtils.getString(mContext,"wx");
        if(zfb.equals("1")){
            tv_zfb.setVisibility(View.VISIBLE);
        }else {
            tv_zfb.setVisibility(View.GONE);
        }
        if(yhk.equals("1")){
            tv_yhk.setVisibility(View.VISIBLE);
        }else {
            tv_yhk.setVisibility(View.GONE);
        }
        if(wx.equals("1")){
            tv_wx.setVisibility(View.VISIBLE);
        }else {
            tv_wx.setVisibility(View.GONE);
        }
        img_addtype.setOnClickListener(this);
        tv_zfb.setOnLongClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_addtype:
                String[] mMenuItems = {"支付宝", "银行卡", "微信"};
                final NormalListDialog dialog = new NormalListDialog(mContext, mMenuItems);
                dialog.title("请选择")//
                        .titleTextSize_SP(18)//
                        .titleBgColor(Color.parseColor("#FACC2E"))//
                        .itemPressColor(Color.parseColor("#85D3EF"))//
                        .itemTextColor(Color.parseColor("#303030"))//
                        .itemTextSize(14)//
                        .cornerRadius(0)//
                        .widthScale(0.8f)//
                        .show();

                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mMenuItems[position].equals("支付宝")){
                            tv_zfb.setVisibility(View.VISIBLE);
                            SpUtils.saveString(mContext,"zfb","1");
                        }else if (mMenuItems[position].equals("银行卡")){
                            tv_yhk.setVisibility(View.VISIBLE);
                            SpUtils.saveString(mContext,"yhk","1");
                        }else if (mMenuItems[position].equals("微信")){
                            tv_wx.setVisibility(View.VISIBLE);
                            SpUtils.saveString(mContext,"wx","1");
                        }
                        dialog.dismiss();
                    }
                });

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.tv_zfb:
                new AlertDialog.Builder(mContext)
                        .setTitle("确定删除支付宝账户吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_zfb.setVisibility(View.GONE);
                                SpUtils.saveString(mContext,"zfb","0");
                            }

                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_yhk:
                new AlertDialog.Builder(mContext)
                        .setTitle("确定删除银行卡账户吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_zfb.setVisibility(View.GONE);
                                SpUtils.saveString(mContext,"yhk","0");
                            }

                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.tv_wx:
                new AlertDialog.Builder(mContext)
                        .setTitle("确定删除微信账户吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_zfb.setVisibility(View.GONE);
                                SpUtils.saveString(mContext,"wx","0");
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        String zfbj = SpUtils.getString(mContext,"zfbj");
        String yhkj = SpUtils.getString(mContext,"yhkj");
        String wxj = SpUtils.getString(mContext,"wxj");
        String total = SpUtils.getString(mContext,"total");
        tv_total.setText("总额:      "+total);
        tv_zfb.setText("支付宝:      "+zfbj);
        tv_yhk.setText("银行卡:      "+yhkj);
        tv_wx.setText("微信:      "+wxj);
    }
}


