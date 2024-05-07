package com.projectpi.cloudwaves.Controllers;

import com.projectpi.cloudwaves.Services.EmailServiceImp;
import com.projectpi.cloudwaves.Services.QuizService;
import com.projectpi.cloudwaves.entites.Questions;
import com.projectpi.cloudwaves.entites.QuestionsWrapper;
import com.projectpi.cloudwaves.entites.Quiz;
import com.projectpi.cloudwaves.entites.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("Quiz")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    EmailServiceImp emailService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable Long id){
        return quizService.getQuizQuestion(id);
    }

    @DeleteMapping("delete/{id}")
    public void remove(@PathVariable long id) {
        quizService.deleteById(id);
    }

    @GetMapping("allQuizs")
    public ResponseEntity<List<Quiz>> getAllQuizs() {
        return quizService.getAllQuizs();
    }


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<String> responses){
        return  quizService.calculateQuizScore(id, responses);
    }


    @PostMapping("/send/{to}/{correct}")
    public String sendMail(@PathVariable String to,@PathVariable int correct) {
        byte[] qrCodeBytes = QuizService.generateQRCode(correct, 400, 400);  // Not used anymore
        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<body>" +
                "<img src=\"data:image/png;base64," + qrCodeBytes + "\" alt=\"QR Code\">\n" +  // Use the Base64 encoded string directly
                "</body>" +
                "</html>";
        emailService.sendMail(to, "Quiz Result", "Quiz Result", htmlContent,qrCodeBytes);
        return "done";
    }
}
