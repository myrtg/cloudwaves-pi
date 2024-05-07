import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/Service/quiz/quiz.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent {
  quiz: any ;

  constructor(private es: QuizService, private ac:ActivatedRoute, private router:Router){};
  
  ngOnInit(): void {
    this.es.getAllData().subscribe((response) => {this.quiz = response})

  }
}