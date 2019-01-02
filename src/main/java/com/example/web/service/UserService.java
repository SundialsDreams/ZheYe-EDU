package com.example.web.service;

import com.example.web.dao.UserData;
import com.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    private UserData userData;

    public String register(User user) throws Exception {
        if (userData.findUser(user.getUser_email()).size() == 0) {
            user = testSimpleMail(user);
            userData.registerUser(user);
            return "注册成功";
        }
        else {
            List<User> dbUser = userData.findUser(user.getUser_email());
            System.out.println(dbUser);
            return "注册失败，该用户名已存在";
        }
    }

    public String login(User user) {
        List<User> dbUser = userData.findUser(user.getUser_email());
        int attri=0;
        if (dbUser.size() == 0) {
            return "登录失败，用户名或密码不正确";
        } else if (dbUser.contains(user)&&!dbUser.get(0).getUser_password().equals(user.getUser_password())) {
                return "登录失败，用户名或密码不正确";
        } else if(dbUser.get(0).getUserattri()==attri){
            return"未激活";
        }else {
            return "登陆成功";
        }
    }

    public void update(User user){
        //更新用户信息
        userData.updateUser(user.getUser_email(),user.getUser_name(),user.getUser_gender(),
                user.getUser_nickname(),user.getUser_phone(),user.getUser_country(),user.getUser_city());
    }

    public User display(String email){
        List<User> dbUser = userData.findUser(email);
        return dbUser.get(0);
    }

    public void updateUserIntro(String email,String intro){
        userData.updateUserIntro(email,intro);

    }

    public void uploadAvatar(String email,String avatar_path){
        userData.uploadAvatar(email,avatar_path);
    }

    public void deleteUser(String email){
        userData.deleteUser(email);
    }

    public String yanz(User user) throws Exception {
        if (userData.findUserYanz(user.getUser_email(),user.getUseryanzm()).size() != 0) {
            userData.yanz(user);
            return "验证成功";
        }
        else {
            return "验证失败";
        }
    }

    public User testSimpleMail(User user) throws Exception {
        MailService mailService = new MailService();

        Random random = new Random();
        String content="";
        for(int i=0;i<6;i++){
            content+=random.nextInt(10);
        }
        user.setUseryanzm(content);
        mailService.sendSimpleMail(user.getUser_email(),"验证邮件","验证码为："+content);

        return user;
    }
}
