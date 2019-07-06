package com.example.quorahibernate.response_formats;

import java.util.List;

import com.example.quorahibernate.entities.AnswerDTO;
import com.example.quorahibernate.entities.QuestionDTO;

/**
 * UserShowJson
 */
public class UserShowJson {

    //Get all questions for the user with id.
    public List<QuestionDTO> questions;
    
    //Get all answers for the user with id.
    public List<AnswerDTO> answers;
    
}