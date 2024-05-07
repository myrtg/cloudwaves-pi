import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quizs } from 'src/app/models/Quiz';


@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private  httpClient:HttpClient) { }

  getAllData(): Observable<Quizs[]> {
    return this.httpClient.get<Quizs[]> ('http://localhost:8080/Quiz/allQuizs');
  }

  createQuiz(category: string, numQ: number, title: string) {
    return this.httpClient.post<any>(`http://localhost:8080/Quiz/create?category=${category}&numQ=${numQ}&title=${title}`,{});
  }
}