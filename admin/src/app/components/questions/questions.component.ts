import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionsService } from 'src/app/Service/questions/questions.service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent {
  question: any ;

  constructor(private es: QuestionsService, private ac:ActivatedRoute, private router:Router){};
  
  ngOnInit(): void {
    this.es.getAllData().subscribe((response) => {this.question = response})
    this.es.deleteQuestion(this.ac.snapshot.params['id']).subscribe(
      () =>
        this.router.navigate(['questions'])
    )
  }

  delete(id: any) {
    this.es.deleteQuestion(id).subscribe(() => {
      this.question = this.question.filter((question: any) => question.id != id)
    }, error => {
      console.log(error);
    });
  }
}
