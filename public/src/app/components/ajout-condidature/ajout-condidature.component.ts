import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CandidatureService } from 'src/app/Service/candidature/candidature.service';

@Component({
  selector: 'app-ajout-condidature',
  templateUrl: './ajout-condidature.component.html',
  styleUrls: ['./ajout-condidature.component.css']
})
export class AjoutCondidatureComponent {
  constructor(
    private es: CandidatureService,
    private router: Router,
    private httpClient: HttpClient
  ) { }
  
  createCandidature(name: string, email: string, phoneNumber: string, cv: FileList | null) {
    if (!cv) {
      console.error('No CV file selected');
      return;
    }

    const selectedFile: File = cv[0];
    
    // Create and prepare form data 
    const formData: FormData = new FormData();
    formData.append('name', name);
    formData.append('email', email);
    formData.append('phoneNumber', phoneNumber);
    formData.append('cv', selectedFile, selectedFile.name);

    
    this.httpClient.post<any>('http://localhost:8083/candidature/create', formData)
      .subscribe(
        () => {
          console.log('Candidature added successfully');
          this.router.navigate(['internship']);
        },
        (error: HttpErrorResponse) => {
          console.error('Error adding candidature:', error);
          // Handle error
          this.router.navigate(['internship']);
        }
      );
  }
}
