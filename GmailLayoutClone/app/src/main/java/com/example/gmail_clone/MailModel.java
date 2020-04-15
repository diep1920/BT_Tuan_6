package com.example.gmail_clone;

public class MailModel {
    private String name;
    private String subject;
    private String content;
    private String time;
    boolean isFavorited;

    public MailModel(String name, String content, String time) {
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public MailModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public MailModel(String name, String subject, String content, String time) {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
