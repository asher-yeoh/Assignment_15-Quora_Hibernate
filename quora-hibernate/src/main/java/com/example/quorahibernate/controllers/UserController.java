package com.example.quorahibernate.controllers;

import java.util.List;

import com.example.quorahibernate.entities.Answer;
import com.example.quorahibernate.entities.AnswerDTO;
import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.entities.QuestionDTO;
import com.example.quorahibernate.entities.User;
import com.example.quorahibernate.repositories.AnswerRepository;
import com.example.quorahibernate.repositories.QuestionRepository;
import com.example.quorahibernate.repositories.UserRepository;
import com.example.quorahibernate.response_formats.UserShowJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    // Display all users.
    // localhost:8080/api/users/all
    @GetMapping(value = "/users/all", produces = "application/json")
    public List<User> displayUsers() {
        return userRepository.findAll();
    }

    // Get all questions and answers for the user with id.
    // localhost:8080/api/users/1/submissions
    @GetMapping(value = "/users/{id}/submissions", produces = "application/json")
    public UserShowJson displayQuestionsAndAnswersForUser(@PathVariable Long id) {
        UserShowJson json = new UserShowJson();

        List<QuestionDTO> questions = questionRepository.findQuestionsForUser(id);

        List<AnswerDTO> answers = answerRepository.findAnswersForUser(id);

        json.questions = questions;
        json.answers = answers;

        return json;
    }

    //Creates a user.
    //localhost:8080/api/users
    @PostMapping(value="/users")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    //Deletes all answers for the user
    //Deletes all questions for the user
    //Deletes the user
    //localhost:8080/api/users/6
    @DeleteMapping(value="/users/{id}")
    public void deleteUser(@PathVariable("id") long id){

        User existingUser = userRepository.findById(id).orElse(new User());
        if(existingUser.getId() != null){
            List<Answer> answers =  answerRepository.findAllByUserId(existingUser.getId());
            answerRepository.deleteAll(answers);

            List<Question> questions =  questionRepository.findAllByUserId(existingUser.getId());
            questionRepository.deleteAll(questions);

            userRepository.delete(existingUser);
        }
    }

}