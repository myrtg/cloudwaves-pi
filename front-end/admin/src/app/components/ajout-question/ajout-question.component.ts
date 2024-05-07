import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { QuestionsService } from 'src/app/Service/questions/questions.service';

@Component({
  selector: 'app-ajout-question',
  templateUrl: './ajout-question.component.html',
  styleUrls: ['./ajout-question.component.css']
})
export class AjoutQuestionComponent {
  constructor(private es: QuestionsService, private router: Router) { }
  ngOnInit(): void {}


  addQuestion(question: any) {

    console.log("question data :"+question);
    this.es.addQuestion(question).subscribe(
      () => {
        this.router.navigate(['questions']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 200) {
          this.router.navigate(['questions']);
        } else {
          console.error('Error adding question:', error);
        }
      }
    );
  }

}