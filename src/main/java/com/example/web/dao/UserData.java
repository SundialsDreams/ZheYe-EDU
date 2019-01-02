package com.example.web.dao;

import com.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserData {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void     registerUser(User user) {
        jdbcTemplate.update("INSERT INTO User(user_email,user_password,user_avatar,useryanzm,userattri) VALUES(?,?,?,?,?)",
                user.getUser_email(), user.getUser_password(), "images/t1.jpg", user.getUseryanzm(),0);
    }

    public List<User> findUser(String user_email) {
        return jdbcTemplate.query("SELECT * FROM User WHERE user_email=?", new Object[] { user_email },
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public void updateUser(String user_email,String user_name,String user_gender,
                           String user_nickname,String user_phone,String user_country,String user_city) {
        this.jdbcTemplate.update(
                "update User set user_name = ? where user_email = ?",
                user_name, user_email);
        this.jdbcTemplate.update(
                "update User set user_gender = ? where user_email = ?",
                user_gender, user_email);
        this.jdbcTemplate.update(
                "update User set user_nickname = ? where user_email = ?",
                user_nickname, user_email);
        this.jdbcTemplate.update(
                "update User set user_phone = ? where user_email = ?",
                user_phone, user_email);
        this.jdbcTemplate.update(
                "update User set user_country = ? where user_email = ?",
                user_country, user_email);
        this.jdbcTemplate.update(
                "update User set user_city = ? where user_email = ?",
                user_city, user_email);
    }

    public void updateUserIntro(String user_email,String user_intro){
        this.jdbcTemplate.update(
                "update User set user_intro = ? where user_email = ?",
                user_intro, user_email);
    }

    public void uploadAvatar(String user_email,String user_avatar){
        this.jdbcTemplate.update(
                "update User set user_avatar = ? where user_email = ?",
                user_avatar, user_email);
    }

    public void deleteUser(String user_email){
        this.jdbcTemplate.update("DELETE from User where user_email= ?",
                user_email);
    }

    public List<User> findUserYanz(String user_email,String useryanzm) {
        return jdbcTemplate.query("SELECT * FROM User WHERE user_email=? and useryanzm=?", new Object[] { user_email ,useryanzm},
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public void yanz(User user) {
        jdbcTemplate.update("UPDATE User set USERATTRI = 1 where USER_EMAIL = ?", user.getUser_email());
    }
}
