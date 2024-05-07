package tn.pfeconnect.pfeconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.pfeconnect.pfeconnect.entities.QuestionsWrapper;
import tn.pfeconnect.pfeconnect.entities.Quiz;
import tn.pfeconnect.pfeconnect.servicesImpl.EmailServiceImpl;
import tn.pfeconnect.pfeconnect.servicesImpl.QuizService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("Quiz")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    EmailServiceImpl emailService;
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