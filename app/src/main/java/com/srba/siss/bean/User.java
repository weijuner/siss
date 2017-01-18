package com.srba.siss.bean;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2017/1/12
 * 描述:
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2017/1/12         zwj               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class User {

    /**
     * userName : 2163B484D3A9430EBE6326D9D6E51B88
     * token : c31d067fdd7e6f7ef46985cef967557df6066d967221b7176d526d23f672ab93
     * id : 82458
     * name : 金中环分公司
     * code : 2163B484D3A9430EBE6326D9D6E51B88
     * tradeType : 0
     * type : 2
     */

    private String userName;
    private String token;
    private int id;
    private String name;
    private String code;
    private int tradeType;
    private int type;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
