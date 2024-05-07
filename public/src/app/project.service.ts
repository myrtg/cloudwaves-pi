import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categorie, Projet } from './classes/Classes';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>('http://localhost:9090/categorie');
  }

  getAllProjects(): Observable<Projet[]> {
    return this.http.get<Projet[]>('http://localhost:9090/projet');
  }

  getSubCategories(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:9090/souscategorie');
  }

  downloadFile(projectId: number): Observable<Blob> {
    return this.http.get(`http://localhost:9090/projet/download/${projectId}`, { responseType: 'blob' });
  }
}
