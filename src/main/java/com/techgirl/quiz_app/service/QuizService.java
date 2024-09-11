package com.techgirl.quiz_app.service;

import com.techgirl.quiz_app.model.Question;
import com.techgirl.quiz_app.model.Quiz;
import com.techgirl.quiz_app.model.User;
import com.techgirl.quiz_app.repository.QuestionRepository;
import com.techgirl.quiz_app.repository.QuizRepository;
import com.techgirl.quiz_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public Quiz createQuiz(Quiz quiz) {
        if (quiz.getQuestions() != null) {
            for (Question question : quiz.getQuestions()) {
                question.setQuiz(quiz);
            }
        }
        return quizRepository.save(quiz);
    }

    public Quiz getQuiz(Long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            List<Question> questions = questionRepository.findAllByQuizId(quizId);
            Quiz getQuiz = quiz.get();
            getQuiz.setQuestions(questions);
            return getQuiz;
        }
        return null;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }


    public Question addQuestion(Long quizId, Question question) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            question.setQuiz(quiz.get());
            return questionRepository.save(question);
        }
        return null;
    }


    public int submitQuiz(Long quizId, Map<Long, String> userAnswers, String userName) {
        List<Question> questions = questionRepository.findAllByQuizId(quizId);
        int score = 0;

        for (Question question : questions) {
            String correctAnswer = question.getCorrectAnswer();
            String userAnswer = userAnswers.get(question.getId());
            if (correctAnswer.equals(userAnswer)) {
                score++;
            }
        }

        User user = userRepository.findByNameAndQuizId(userName, quizId);
        if (user == null) {
            user = new User();
            user.setName(userName);
            user.setQuizId(quizId);
        }
        user.setScore(score);
        userRepository.save(user);

        return score;
    }
}
