package com.enjoy.plugin;

/**
 * Created by zch on 2022-04-23.
 */
public class JiaguBean {

    // 360 账号
    private String userName;
    // 360 密码
    private String userPwd;
    // 签名路径
    private String keyStorePath;
    // 签名密码
    private String keyStorePass;
    // 签名别名
    private String keyStoreKeyAlias;
    // 签名别名密码
    private String keyStoreKeyAliasPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getKeyStorePass() {
        return keyStorePass;
    }

    public void setKeyStorePass(String keyStorePass) {
        this.keyStorePass = keyStorePass;
    }

    public String getKeyStoreKeyAlias() {
        return keyStoreKeyAlias;
    }

    public void setKeyStoreKeyAlias(String keyStoreKeyAlias) {
        this.keyStoreKeyAlias = keyStoreKeyAlias;
    }

    public String getKeyStoreKeyAliasPwd() {
        return keyStoreKeyAliasPwd;
    }

    public void setKeyStoreKeyAliasPwd(String keyStoreKeyAliasPwd) {
        this.keyStoreKeyAliasPwd = keyStoreKeyAliasPwd;
    }

    @Override
    public String toString() {
        return "JiaguBean{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", keyStorePath='" + keyStorePath + '\'' +
                ", keyStorePass='" + keyStorePass + '\'' +
                ", keyStoreKeyAlias='" + keyStoreKeyAlias + '\'' +
                ", keyStoreKeyAliasPwd='" + keyStoreKeyAliasPwd + '\'' +
                '}';
    }
}
