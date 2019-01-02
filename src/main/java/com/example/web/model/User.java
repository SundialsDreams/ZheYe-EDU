package com.example.web.model;


public class User {
    private String user_email;
    private String user_password;
    private String user_name;
    private String user_gender;
    private String user_nickname;
    private String user_phone;
    private String user_country;
    private String user_city;
    private String user_intro;
    private String user_avatar;
    private int userattri;
    private String useryanzm;

    public User() {}

    public User(String user_email,String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public String getUser_email() {
        return this.user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_nickname() {
        return this.user_nickname;
    }
    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_password() {
        return this.user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password =user_password;
    }

    public String getUser_intro() {
        return this.user_intro;
    }
    public void setUser_intro(String user_intro) {
        this.user_intro = user_intro;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public int getUserattri() {
        return userattri;
    }

    public void setUserattri(int userattri) {
        this.userattri = userattri;
    }

    public String getUseryanzm() {
        return useryanzm;
    }

    public void setUseryanzm(String useryanzm) {
        this.useryanzm = useryanzm;
    }
}
