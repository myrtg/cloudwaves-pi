package com.projectpi.cloudwaves.Services;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.projectpi.cloudwaves.Repository.OffreStageRepository;
import com.projectpi.cloudwaves.Repository.QuestionRepository;
import com.projectpi.cloudwaves.Repository.QuizRepository;
import com.projectpi.cloudwaves.entites.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    OffreStageRepository offreStageRepository;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Questions> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        OffreStage offreStage = offreStageRepository.findByDomaine(Domaine.valueOf(category));

        // Associate the quiz with the internship offer
        if (offreStage != null) {
            offreStage.setQuiz(quiz);
            offreStageRepository.save(offreStage);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("No matching internship offer found for category: " + category, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestion(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Questions> questionsFromDB = quiz.get().getQuestions();
        List<QuestionsWrapper> questionForUser = new ArrayList<>();
        for(Questions q : questionsFromDB){
            QuestionsWrapper qw = new QuestionsWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }



    public ResponseEntity<Integer> calculateQuizScore(Long id, List<Responses> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int i = 0;
        int correct = 0;
        for(Responses response: responses){
            if(response.getResponse().equals(questions.get(i).getCorrectAnswer())){
                correct += 1;
            }
            i+=1;
        }
        return new ResponseEntity<>(correct,HttpStatus.OK);
    }
    public static byte[] generateQRCode(int correct, int width, int height) {
        Logger logger = LoggerFactory.getLogger(QuizService.class);
        try {
            String content = "Quiz Score: "+ correct +"/10. Thanks for passing this Quiz with us";
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();


            return imageBytes;
        } catch (IOException | WriterException e) {
            logger.error("Error generating QR code", e);
        }
        return null;
    }
}
