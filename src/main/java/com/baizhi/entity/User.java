package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
private String id;
private String headPic;
private String Dharma_name;
private String username;
private String password;
private String gender;
private String home;
private String phoneNum;
private String sign;
private String status;
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@JSONField(format = "yyyy-MM-dd HH:mm:ss")
private Date register_time;
private String salt;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", headPic='" + headPic + '\'' +
                ", Dharma_name='" + Dharma_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", home='" + home + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", register_time=" + register_time +
                ", salt='" + salt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDharma_name() {
        return Dharma_name;
    }

    public void setDharma_name(String dharma_name) {
        Dharma_name = dharma_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User(String id, String headPic, String dharma_name, String username, String password, String gender, String home, String phoneNum, String sign, String status, Date register_time, String salt) {
        this.id = id;
        this.headPic = headPic;
        Dharma_name = dharma_name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.home = home;
        this.phoneNum = phoneNum;
        this.sign = sign;
        this.status = status;
        this.register_time = register_time;
        this.salt = salt;
    }

    public User() {
    }
}
