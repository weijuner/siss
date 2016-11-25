package com.srba.siss.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.srba.siss.R;
import com.srba.siss.base.BaseMvpActivity;
import com.srba.siss.greendao.GreenDaoManager;
import com.srba.siss.greendao.dao.UserDao;
import com.srba.siss.greendao.entity.User;
import com.srba.siss.mvp.login.LoginContract;
import com.srba.siss.mvp.login.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/22 10:27
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/22       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        User user = new User(null, "zengwj");
        userDao.insert(user);
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }


    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //请求接口
                mPresenter.login("430521199207103331", "103331");
                break;
        }
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(mActivity, MainActivity.class));
    }
}
