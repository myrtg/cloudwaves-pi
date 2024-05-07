import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/Service/quiz/quiz.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent {
  idQuiz!: number;
  quiz!: any;
  selectedAnswers: any = {};

  constructor(private http: HttpClient, private actRoute: ActivatedRoute, private es: QuizService,private router: Router) { }

  ngOnInit(): void {
    this.getParam();
    this.es.getQuiz(this.idQuiz).subscribe((data) => {
      console.log('Quiz Data:', data); // Log the quiz data received from the API
      this.quiz = data;
      console.log('Assigned Quiz:', this.quiz); // Log the assigned quiz data
    });
  }

  getParam() {
    this.actRoute.paramMap.subscribe(data => this.idQuiz = Number(data.get('param')));
  }

  submitQuiz() {
    console.log(this.selectedAnswers); // Log selected answers
    // Ensure this.quiz is defined and contains questions
    if (!this.quiz || this.quiz.length === 0) {
      console.error('Error: Quiz data is missing or incomplete.');
      console.log('Quiz:', this.quiz);
      return; // Exit the function early if quiz data is missing or incomplete
    }

    const answersArray: string[] = []; // Explicitly define the type of answersArray as an array of strings

    // Iterate over questions and map selected answers in the correct order
    this.quiz.forEach((question: any) => {
      const answer = this.selectedAnswers[question.id.toString()];
      answersArray.push(answer);
    });

    console.log(answersArray); // Log ordered selected answers array

    // Send ordered selected answers to backend
    this.http.post<any>(`http://localhost:8083/Quiz/submit/${this.idQuiz}`, answersArray)
      .subscribe(
        (data) => {
          // Handle successful response from backend
          const answer = data
          console.log(data);
          
          this.http.post<any>(`http://localhost:8083/Quiz/send/mouhanedakermi@gmail.com/${answer}`, answersArray)
            .subscribe(
              (data) => {
                // Handle successful response from backend
                console.log(data);
                // Optionally, you can reset the form or redirect the user
              },
              (error) => {
                // Handle error response from backend
                console.error('Error:', error);
              }
            );
        },
        (error) => {
          // Handle error response from backend
          console.error('Error:', error);
        }
      );
      this.router.navigate(['addCondidature']);



  }





}
