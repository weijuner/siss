package com.srba.siss.bean.result;

import java.util.List;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/12
 * 描述:  返回结果模板
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/12         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class BaseResult<T> {



    private int result;
    private String msg;
    private List<T> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
