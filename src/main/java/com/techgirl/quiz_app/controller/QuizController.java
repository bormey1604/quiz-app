package com.techgirl.quiz_app.controller;

import com.techgirl.quiz_app.model.Question;
import com.techgirl.quiz_app.model.Quiz;
import com.techgirl.quiz_app.model.response.Response;
import com.techgirl.quiz_app.model.response.ResponseDetail;
import com.techgirl.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz) {
        Quiz createQuiz = quizService.createQuiz(quiz);

        Response data = new Response(
                "200",
                "Quiz id '"+createQuiz.getId()+"' created successfully",
                "The quiz '" + quiz.getTitle() + "' was created and saved to the database."
        );
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/{quizId}/get")
    public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
        Quiz getQuiz = quizService.getQuiz(quizId);

        if(getQuiz != null){
            return new ResponseEntity<>(new ResponseDetail(
                    "200",
                    "Success",
                    "Get quiz successfully",
                    getQuiz),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(
                "404",
                "Failure",
                "Quiz with id '"+quizId+"' not found."),
                HttpStatus.NOT_FOUND);

    }

    @PostMapping("{quizId}/add-question")
    public ResponseEntity<?> addQuestion(@PathVariable Long quizId, @RequestBody Question question) {

        Question createQuestion = quizService.addQuestion(quizId,question);

        if(createQuestion == null){
            return new ResponseEntity<>(new Response(
                    "404",
                    "Not found",
                    "Quiz with id '"+quizId+"' not found.")
                    ,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Response(
                "200",
                "Question added successfully",
                "The question was successfully added to the quiz id: '" + quizId + "'.")
                ,HttpStatus.OK);
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<?> submitQuiz(@PathVariable Long quizId,
                                             @RequestBody Map<Long, String> userAnswers,
                                             @RequestParam String userName) {
        int score = quizService.submitQuiz(quizId, userAnswers, userName);

        return new ResponseEntity<>(new Response(
                "200",
                "Quiz submitted successfully",
                "The quiz with ID '" + quizId + "' was submitted successfully by user '" + userName + "'. The score for the submission is " + score + "."
        ), HttpStatus.OK);
    }

}
