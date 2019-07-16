package com.example.wx.entity;

public class Token {
    private String token;
    private long expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    /**
     * 把相应的token存起来
     * @param token
     * @param expirein
     */
    public Token(String token, String expirein) {
        this.token = token;
        this.expire = System.currentTimeMillis()+Integer.parseInt(expirein)*1000;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", expire=" + expire +
                '}';
    }

    /**
     * 判断accesstoken是否过期
     * @return
     */
    public boolean isexpired(){
        return System.currentTimeMillis()>this.expire;
    }
}
