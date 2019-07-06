package com.example.quorahibernate.controllers;

import java.util.List;

import com.example.quorahibernate.entities.Answer;
import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.repositories.AnswerRepository;
import com.example.quorahibernate.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    //Display all answers.
    //localhost:8080/api/answers/all
    @GetMapping(value="/answers/all", produces="application/json")
    public List<Answer> displayAnswers() {
        return answerRepository.findAll();
    }

    //Get a list of all answers for question with id.
    //localhost:8080/api/questions/1/answers
    @GetMapping(value="/questions/{id}/answers", produces="application/json")
    public List<Answer> displayAnswersForQuestion(@PathVariable("id") long id) {
        return answerRepository.findAllByQuestionId(id);
    }

    //Creates an answer for the user for the question。
    //localhost:8080/api/questions/{id}/answers
    @PostMapping(value="/questions/{id}/answers")
    public void addAnswerForQuestion(@RequestBody Answer answer, @PathVariable("id") long id){
        Question question = questionRepository.findById(id).orElse(new Question());

        if(question.getId() != null){
            answer.setQuestionId(id);
            answerRepository.save(answer);
        }
    }

    //Updates the text of the answer。
    //localhost:8080/api/answers/1
    @PostMapping(value="/answers/{id}")
    public void updateAnswer(@RequestBody Answer answer, @PathVariable("id") long id){
        Answer existingAnswer = answerRepository.findById(id).orElse(new Answer());
        if(existingAnswer.getId() != null){
            answer.setId(id);
            answer.setUserId(existingAnswer.getUserId());
            answer.setQuestionId(existingAnswer.getQuestionId());

            answerRepository.save(answer);
        };
    }

}