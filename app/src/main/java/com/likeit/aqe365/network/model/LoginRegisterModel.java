package com.likeit.aqe365.network.model;

import java.io.Serializable;

public class LoginRegisterModel implements Serializable{

    /**
     * token : ODY1NzQyNG1SY05xUkpWM1JhVnRQaGQzZEFpR2o5QXlHYnd2eUxuRmFlNkZ0d2dHZmJsdE1zaFJsNGJIYVFBSTl5Yw==
     * expire : 2592000
     * member : {"id":"2228","mobile":"13680260576","salt":"DAataAToaw1O84JM","nickname":"136xxxx0576","avatar":"","openid":"wap_user_1_13680260576"}
     */

    private String token;
    private int expire;
    private MemberBean member;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public static class MemberBean implements Serializable{
        /**
         * id : 2228
         * mobile : 13680260576
         * salt : DAataAToaw1O84JM
         * nickname : 136xxxx0576
         * avatar :
         * openid : wap_user_1_13680260576
         */

        private String id;
        private String mobile;
        private String salt;
        private String nickname;
        private String avatar;
        private String openid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }
}
