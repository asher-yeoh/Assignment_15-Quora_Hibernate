package com.example.quorahibernate.entities;

/**
 * QuestionDTO (Data Transfer Object)
 */
public class QuestionDTO {

    private String description;

    public QuestionDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}