package com.techgirl.quiz_app.repository;

import com.techgirl.quiz_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndQuizId(String name, Long quizId);
}
