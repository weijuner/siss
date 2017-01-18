package com.srba.siss.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.srba.siss.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/24 10:28
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/24       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BaseActivity extends AutoLayoutActivity {
    public Activity mActivity;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showToast(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public Dialog  progressDialog;

    public Dialog  showProgressDialog() {
        progressDialog = new Dialog(mActivity, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog_loading);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("正在登录...");
        progressDialog.show();
        return progressDialog;
    }


    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }
}
