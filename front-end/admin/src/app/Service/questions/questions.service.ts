import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Questions } from 'src/app/models/Questions';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(private  httpClient:HttpClient) { }

  getAllData(): Observable<Questions[]> {
    return this.httpClient.get<Questions[]> ('http://localhost:8080/Question/allQuestions');
  }

  addQuestion(questions: any) {
    return this.httpClient.post('http://localhost:8080/Question/add', questions);
  }
  deleteQuestion(id:any) {
    return this.httpClient.delete('http://localhost:8080/Question/delete/'+id);
  }
}