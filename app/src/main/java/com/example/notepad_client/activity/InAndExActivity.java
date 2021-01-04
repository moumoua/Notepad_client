package com.example.notepad_client.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.notepad_client.R;
import com.example.notepad_client.utils.SpUtils;
import com.example.notepad_client.utils.ToastUtil;
import com.example.notepad_client.view.ClearEditText;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InAndExActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_zhchu)
    TextView tvZhchu;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.tv_zhanghu)
    TextView tvZhanghu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.edt_beizhu)
    ClearEditText edtBeizhu;
    @BindView(R.id.img_gou)
    ImageView imgGou;
    @BindView(R.id.edt_jine)
    ClearEditText edtJine;

    private TimePickerView pvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inandex);
        ButterKnife.bind(this);
        initTimePicker();
        SimpleDateFormat shijian = new SimpleDateFormat("yyyy-MM-dd");
        String date = shijian.format(new Date());
        tvTime.setText(date);

    }

    @OnClick({R.id.tv_zhchu, R.id.img_gou, R.id.tv_fenlei, R.id.tv_zhanghu, R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhchu:
                zhichu();
                break;
            case R.id.tv_fenlei:
                fenlei();
                break;
            case R.id.tv_zhanghu:
                zhanghu();
                break;
            case R.id.img_gou:
                String stime = tvTime.getText().toString();
                String jine = edtJine.getText().toString();
                String zhanghu = tvZhanghu.getText().toString();
                String zhtype = SpUtils.getString(this,zhanghu);
                String shouru = tvZhchu.getText().toString();
                Log.e("TAG","shouru--"+shouru);

                if(!zhanghu.equals("账户")){
                    Log.e("TAG","zhanghu---"+zhanghu);
                    if (!jine.isEmpty()){
                        String total = SpUtils.getString(this,"total");
                        if (!total.isEmpty()){
                            double t = Double.parseDouble(total)+Double.parseDouble(jine);
                            SpUtils.saveString(this,"total",String.valueOf(t));
                        }else {
                            SpUtils.saveString(this,"total",jine);
                        }
                        if (zhanghu.equals("支付宝")){
                            String zfbj = SpUtils.getString(this,"zfbj");
                            if (!zfbj.isEmpty()){
                                double t = Double.parseDouble(zfbj)+Double.parseDouble(jine);
                                SpUtils.saveString(this,"zfbj",String.valueOf(t));
                            }else {
                                SpUtils.saveString(this,"zfbj",jine);
                            }
                        }else if (zhanghu.equals("银行卡")){
                            String yhkj = SpUtils.getString(this,"yhkj");
                            if (!yhkj.isEmpty()){
                                double t = Double.parseDouble(yhkj)+Double.parseDouble(jine);
                                SpUtils.saveString(this,"yhkj",String.valueOf(t));
                            }else {
                                SpUtils.saveString(this,"yhkj",jine);
                            }
                        }else if (zhanghu.equals("微信")){
                            String wxj = SpUtils.getString(this,"wxj");
                            if (!wxj.isEmpty()){
                                double t = Double.parseDouble(wxj)+Double.parseDouble(jine);
                                SpUtils.saveString(this,"wxj",String.valueOf(t));
                            }else {
                                SpUtils.saveString(this,"wxj",jine);
                            }
                        }

                        String date = stime;
                        String daterq = stime.substring(0,7);
                        String datenian = stime.substring(0,4);
                        String sptime = SpUtils.getString(this,date);
                        String sptimerq = SpUtils.getString(this,daterq);
                        String sptimenian = SpUtils.getString(this,datenian);

                        if (!sptime.isEmpty()){//如果今天的数据不为空
                                double jj = Double.parseDouble(jine)+Double.parseDouble(sptime);
                                SpUtils.saveString(this,date,String.valueOf(jj));

                        }else {
                            SpUtils.saveString(this,date,jine);
                        }
                        if (!sptimerq.isEmpty()){//如果这个月的数据不为空
                            double jj = Double.parseDouble(jine)+Double.parseDouble(sptimerq);
                            SpUtils.saveString(this,daterq,String.valueOf(jj));

                        }else {
                            SpUtils.saveString(this,daterq,jine);
                        }
                        if (!sptimenian.isEmpty()){//如果今年的数据不为空
                            double jj = Double.parseDouble(jine)+Double.parseDouble(sptimenian);
                            SpUtils.saveString(this,datenian,String.valueOf(jj));

                        }else {
                            SpUtils.saveString(this,datenian,jine);
                        }

                        if (!zhtype.isEmpty()){
                            double acount = Double.parseDouble(zhtype)+Double.parseDouble(jine);
                            SpUtils.saveString(this,shouru+zhanghu,String.valueOf(acount));
                        }else {
                            SpUtils.saveString(this,shouru+zhanghu,jine);
                        }
                        finish();
                        ToastUtil.showToast("保存成功");
                    }else {
                        ToastUtil.showToast("请输入金额");
                    }
                }else {
                    ToastUtil.showToast("请选择账户类型");
                }

                break;
            case R.id.tv_time:
                pvTime.show();
                break;

        }
    }

    private void zhanghu() {
        String zfb = SpUtils.getString(this, "zfb");
        String yhk = SpUtils.getString(this, "yhk");
        String wx = SpUtils.getString(this, "wx");
        ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
        if (zfb.equals("1")) {
            mMenuItems.add(new DialogMenuItem("支付宝", R.mipmap.icon_zfb));
        }
        if (yhk.equals("1")) {
            mMenuItems.add(new DialogMenuItem("银行卡", R.mipmap.icon_zfb));
        }
        if (wx.equals("1")) {
            mMenuItems.add(new DialogMenuItem("微信", R.mipmap.icon_zfb));
        }
        if (mMenuItems.size() > 0) {
            final NormalListDialog dialog = new NormalListDialog(this, mMenuItems);
            dialog.title("请选择")//
                    .isTitleShow(false)//
                    .itemPressColor(Color.parseColor("#85D3EF"))//
                    .itemTextColor(Color.parseColor("#303030"))//
                    .itemTextSize(15)//
                    .cornerRadius(2)//
                    .widthScale(0.75f)//
                    .show();

            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tvZhanghu.setText(mMenuItems.get(position).mOperName);
                    dialog.dismiss();
                }
            });
        } else {
            ToastUtil.showToast("请先添加账户");
        }

    }

    private void fenlei() {
        String[] mMenuItems = {"衣服饰品", "医疗保健", "食品酒水", "居家物业", "行车交通", "人情往来",
                "休闲娱乐", "学习进修", "其他杂项"};
        final NormalListDialog dialog = new NormalListDialog(this, mMenuItems);
        dialog.title("请选择")//
                .isTitleShow(false)//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(15)//
                .cornerRadius(2)//
                .widthScale(0.75f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvFenlei.setText(mMenuItems[position]);
                dialog.dismiss();
            }
        });
    }

    private void zhichu() {
        String[] mMenuItems2 = {"支出", "收入"};
        final NormalListDialog dialog2 = new NormalListDialog(this, mMenuItems2);
        dialog2.title("请选择")//
                .isTitleShow(false)//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(15)//
                .cornerRadius(2)//
                .widthScale(0.75f)//
                .show();

        dialog2.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvZhchu.setText(mMenuItems2[position]);
                dialog2.dismiss();
            }
        });
    }

    private void initTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.YEAR, -1);//增加一年
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 1);//增加一年
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(final Date date, final View v) {//选中事件回调
                tvTime.setText(getTime(date));
            }
        })

                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 0, 0, 0)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


}
