import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OffreStageService } from 'src/app/Service/OffreStage/offre-stage.service';

@Component({
  selector: 'app-offre-stage',
  templateUrl: './offre-stage.component.html',
  styleUrls: ['./offre-stage.component.css']
})
export class OffreStageComponent {
  constructor(private es: OffreStageService, private router: Router) { }
  ngOnInit(): void {}


  addOffre(offre: any) {

    console.log("offre data :"+offre);
    this.es.addOffre(offre).subscribe(
      () => {
        // Handle a successful response (status code 200) here
        this.router.navigate(['internship']);
      },
      (error: HttpErrorResponse) => {
        // Handle the error here
        if (error.status === 200) {
          // Consider a status code of 200 as success and navigate
          this.router.navigate(['internship']);
        } else {
          console.error('Error adding offre:', error);
          // Handle the error as needed, e.g., display an error message to the user
        }
      }
    );
  }
}