package com.example.web.controller;

import com.example.web.model.Intro;
import com.example.web.service.FileStorageService;
import com.example.web.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping
public class IntroController {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserService service;


    @PostMapping("/saveIntro")
    public void uploadFile(@RequestPart("json") String intro,@RequestPart("file") MultipartFile file,
                            HttpServletRequest request) throws IOException {

        Cookie cookie[]=request.getCookies();
        String userEmail;
        userEmail = getEmail(cookie);

        Intro jsonIntro = new ObjectMapper().readValue(intro, Intro.class);
        String fileName = fileStorageService.storeFile(file,userEmail);

        String fileUri = "images/" + "avatar/" + userEmail +"/" + fileName;

        System.out.println(fileUri);

        service.updateUserIntro(userEmail,jsonIntro.getPropertyText());
        service.uploadAvatar(userEmail,fileUri);

    }

    private String getEmail(Cookie[] cookie) {
        Cookie cook;
        String userEmail = null;
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                cook = cookie[i];
                if(cook.getName().equalsIgnoreCase("user_email"))
                    userEmail=cook.getValue();
            }
        }
        return userEmail;
    }
}
