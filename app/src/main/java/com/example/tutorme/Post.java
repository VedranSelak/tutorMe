package com.example.tutorme;

public class Post {
    private long id;
    private String fullNameOfTutor;
    private String emailOfTutor;
    private String fieldName;

    public Post(String fullNameOfTutor, String emailOfTutor, String fieldName) {
        this.fullNameOfTutor = fullNameOfTutor;
        this.emailOfTutor = emailOfTutor;
        this.fieldName = fieldName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullNameOfTutor() {
        return fullNameOfTutor;
    }

    public void setFullNameOfTutor(String fullNameOfTutor) {
        this.fullNameOfTutor = fullNameOfTutor;
    }

    public String getEmailOfTutor() {
        return emailOfTutor;
    }

    public void setEmailOfTutor(String emailOfTutor) {
        this.emailOfTutor = emailOfTutor;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
