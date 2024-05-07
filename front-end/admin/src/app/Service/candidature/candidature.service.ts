import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidature } from 'src/app/models/candidature';

@Injectable({
  providedIn: 'root'
})
export class CandidatureService {

  constructor(private  httpClient:HttpClient) { }

  getAllData(): Observable<Candidature[]> {
    return this.httpClient.get<Candidature[]> ('http://localhost:8080/candidature/retrieve-all-candidature');
  }

  delete(id:any) {
    return this.httpClient.delete('http://localhost:8080/candidature/delete-candidature/'+id);
  }

  createCandidature(name: string, email: string, phoneNumber: string, cv: string) {
    console.log(cv);
    
    return this.httpClient.post<any>('http://localhost:8080/candidature/create', { name, email, phoneNumber, cv });
    
 }

}