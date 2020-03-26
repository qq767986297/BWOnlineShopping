package com.bawei.bwonlineshopping.bean;

/**
 * Time: 2020/3/6
 * Author: 王冠华
 * Description:
 */
public class LoginBean {

    private String message;
    private String status;
    private LGBean result;

    public LoginBean(String message, String status, LGBean result) {
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LGBean getResult() {
        return result;
    }

    public void setResult(LGBean result) {
        this.result = result;
    }

    public class LGBean{
        private int userId;
        private String sessionId;
        private String nickName;
        private String phone;
        private String headPic;
        private int sex;

        public LGBean(int userId, String sessionId, String nickName, String phone, String headPic, int sex) {
            this.userId = userId;
            this.sessionId = sessionId;
            this.nickName = nickName;
            this.phone = phone;
            this.headPic = headPic;
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
