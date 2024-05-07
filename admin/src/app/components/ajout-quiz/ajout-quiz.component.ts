import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { QuizService } from 'src/app/Service/quiz/quiz.service';

@Component({
  selector: 'app-ajout-quiz',
  templateUrl: './ajout-quiz.component.html',
  styleUrls: ['./ajout-quiz.component.css']
})
export class AjoutQuizComponent {
  constructor(private es: QuizService, private router: Router) { }
  ngOnInit(): void { }

  addQuiz(category: any, numQ: any, title: any) {
    this.es.createQuiz(category, numQ, title).subscribe(
      () => {
        console.log('Quiz added successfully');
        this.router.navigate(['quizz']);
      },
      (error: HttpErrorResponse) => {
        console.error('Error adding quiz:', error);
        this.router.navigate(['quizz']);
      }
    );
  }

}
