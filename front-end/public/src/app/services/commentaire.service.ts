import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {
  BASE_URL = 'http://localhost:8088/forum/commentaires';

  constructor(private http: HttpClient) {}
  getAllCommentaires(): Observable<any> {
    return this.http.get(`${this.BASE_URL}/getAllCommentaire`).pipe(
      catchError(error => {
        console.error('Error fetching all commentaires:', error);
        return throwError('Error fetching all commentaires');
      })
    );
  }
  addCommentaire(commentaire: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/add-commentaire`, commentaire).pipe(
      catchError(error => {
        console.error('Error adding commentaire:', error);
        return throwError('Error adding commentaire');
      })
    );
  }
  updateCommentaire(commentaire: any): Observable<any> {
    return this.http.put(`${this.BASE_URL}/commentaires/${commentaire.idCommentaire}`, commentaire).pipe(
      catchError(error => {
        console.error('Error updating commentaire:', error);
        return throwError('Error updating commentaire');
      })
    );
  }
  deleteCommentaire(id: number): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}/commentaires/${id}`).pipe(
      catchError(error => {
        console.error('Error deleting commentaire:', error);
        return throwError('Error deleting commentaire');
      })
    );
  }
  likeCommentaire(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/commentaires/${id}/like`, null).pipe(
      catchError(error => {
        console.error('Error liking commentaire:', error);
        return throwError('Error liking commentaire');
      })
    );
  }

  dislikeCommentaire(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/commentaires/${id}/dislike`, null).pipe(
      catchError(error => {
        console.error('Error disliking commentaire:', error);
        return throwError('Error disliking commentaire');
      })
    );
  }
  
}
