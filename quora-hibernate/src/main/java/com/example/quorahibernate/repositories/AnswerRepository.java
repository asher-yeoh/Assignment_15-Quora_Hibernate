package com.example.quorahibernate.repositories;

import java.util.List;

import com.example.quorahibernate.entities.Answer;
import com.example.quorahibernate.entities.AnswerDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * AnswerRepository
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId (long id);

    List<Answer> findAllByUserId(long id);

    @Query("SELECT new com.example.quorahibernate.entities.AnswerDTO(a.text) FROM Answer a WHERE a.userId = ?1")
    List<AnswerDTO> findAnswersForUser (long id);
}