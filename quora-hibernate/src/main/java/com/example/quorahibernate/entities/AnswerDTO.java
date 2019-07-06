package com.example.quorahibernate.entities;

/**
 * AnswerDTO (Data Transfer Object)
 */
public class AnswerDTO {

    private String text;

    public AnswerDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}