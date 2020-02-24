package com.dabao.retrofitmvpdemo.bean;

/**
 * Created by zzt on 2018/9/18.
 */

public class Login_Bean {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"token":"qingseller-login-2-8f5215fdba8795c8bd3bc2dfcd452ad6c4ea4eeee5121a4292c00529a66d8223","unionid":null,"openid":null,"nickname":null,"headimgurl":null,"os":null,"xiaomiId":null}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : qingseller-login-2-8f5215fdba8795c8bd3bc2dfcd452ad6c4ea4eeee5121a4292c00529a66d8223
         * unionid : null
         * openid : null
         * nickname : null
         * headimgurl : null
         * os : null
         * xiaomiId : null
         */

        private String token;
        private Object unionid;
        private Object openid;
        private Object nickname;
        private Object headimgurl;
        private Object os;
        private Object xiaomiId;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getUnionid() {
            return unionid;
        }

        public void setUnionid(Object unionid) {
            this.unionid = unionid;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public Object getOs() {
            return os;
        }

        public void setOs(Object os) {
            this.os = os;
        }

        public Object getXiaomiId() {
            return xiaomiId;
        }

        public void setXiaomiId(Object xiaomiId) {
            this.xiaomiId = xiaomiId;
        }
    }
}
