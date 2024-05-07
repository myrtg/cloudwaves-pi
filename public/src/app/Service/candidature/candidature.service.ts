import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidature } from 'src/app/Models/Candidature';

@Injectable({
  providedIn: 'root'
})
export class CandidatureService {

  constructor(private  httpClient:HttpClient) { }


  createCandidature(name: string, email: string, phoneNumber: string, cv: string) {
    console.log(cv);
    
    return this.httpClient.post<any>('http://localhost:8083/candidature/create', { name, email, phoneNumber, cv });
    
 }

}
