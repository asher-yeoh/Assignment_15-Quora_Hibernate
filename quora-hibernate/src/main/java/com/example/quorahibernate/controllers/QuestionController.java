package com.example.quorahibernate.controllers;

import java.util.List;

import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    //Display all questions.
    //localhost:8080/api/questions/all
    @GetMapping(value="/questions/all", produces="application/json")
    public List<Question> displayQuestions() {
        return questionRepository.findAll();
    }

    //If description present: only show questions that contain the description text (doesn't have to be exact match).
    //Else: gets a list of all questions in DB.
    //localhost:8080/api/questions?description=what
    //localhost:8080/api/questions?description=none
    @GetMapping(value="/questions", produces="application/json")
    public List<Question> displayQuestionsByDescription(@RequestParam String description) {

        List<Question> existingQuestion = questionRepository.findAllByDescriptionContainingIgnoreCase(description);

        if(existingQuestion.isEmpty()) {
            return questionRepository.findAll();
        }
        else {
            return questionRepository.findAllByDescriptionContainingIgnoreCase(description);
        }
    
    }

    //Creates a question for the user.
    //localhost:8080/api/questions
    @PostMapping(value="/questions")
    public void addQuestion(@RequestBody Question question){
        questionRepository.save(question);
    }

}