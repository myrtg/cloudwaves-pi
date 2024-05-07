package tn.pfeconnect.pfeconnect.controllers;

import jakarta.websocket.server.PathParam;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.Questions;
import tn.pfeconnect.pfeconnect.servicesImpl.QuestionService;

import java.util.List;
@RestController
@RequestMapping("Question")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @DeleteMapping("delete/{id}")
    public void remove(@PathVariable long id) {
        questionService.deleteById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody Questions question) {
        return questionService.addQuestions(question);
    }
}