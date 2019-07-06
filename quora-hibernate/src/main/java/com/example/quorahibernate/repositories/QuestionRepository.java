package com.example.quorahibernate.repositories;

import java.util.List;

import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.entities.QuestionDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * QuestionRepository
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByDescriptionContainingIgnoreCase (String description);

    List<Question> findAllByUserId(long id);

    @Query("SELECT new com.example.quorahibernate.entities.QuestionDTO(q.description) FROM Question q WHERE q.userId =?1")
    List<QuestionDTO> findQuestionsForUser (long id);

}