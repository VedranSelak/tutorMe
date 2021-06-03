package com.example.tutorme.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class PostEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String fullNameOfTutor;

    private String emailOfTutor;

    private String fieldName;

    private String description;

    private double perHourCost;

    public PostEntity(String fullNameOfTutor, String emailOfTutor, String fieldName, String description, double perHourCost) {
        this.fullNameOfTutor = fullNameOfTutor;
        this.emailOfTutor = emailOfTutor;
        this.fieldName = fieldName;
        this.description = description;
        this.perHourCost = perHourCost;
    }

    public double getPerHourCost() {
        return perHourCost;
    }

    public void setPerHourCost(double perHourCost) {
        this.perHourCost = perHourCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
