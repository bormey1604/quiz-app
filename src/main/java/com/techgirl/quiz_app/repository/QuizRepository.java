package com.techgirl.quiz_app.repository;

import com.techgirl.quiz_app.model.Quiz;
import com.techgirl.quiz_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
