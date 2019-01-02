package com.example.web.model;

public class Course {
    private int course_ID;
    private String course_name;
    private String course_intro;
    private String course_addr;

    public Course() {

    }

    public Course( int course_ID,String course_name, String course_intro, String course_addr) {
        this.course_ID = course_ID;
        this.course_name = course_name;
        this.course_intro = course_intro;
        this.course_addr = course_addr;
    }

    public int getCourse_ID() {
        return this.course_ID;
    }
    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public String getCourse_name() {
        return this.course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_intro() {
        return this.course_intro;
    }
    public void setCourse_intro(String course_intro) {
        this.course_intro = course_intro;
    }


    public String getCourse_addr() {
        return course_addr;
    }

    public void setCourse_addr(String course_addr) {
        this.course_addr = course_addr;
    }
}
