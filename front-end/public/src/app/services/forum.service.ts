import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ForumService {
  
  BASE_URL = 'http://localhost:8080';
//   http://localhost:8080/forums/add-forum

  constructor(private http: HttpClient) {}

  getAllForums(): Observable<any> {
    return this.http.get(`${this.BASE_URL}/forums/getAllForum`).pipe(
      catchError(error => {
        console.error('Error fetching all forums:', error);
        return throwError('Error fetching all forums');
      })
    );
  }

  addForum(forum: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/forums/add-forum`, forum).pipe(
      catchError(error => {
        console.error('Error adding forum:', error);
        return throwError('Error adding forum');
      })
    );
  }

  updateForum(forum: any): Observable<any> {
    return this.http.put(`${this.BASE_URL}/forums/update-forum`, forum).pipe(
      catchError(error => {
        console.error('Error updating forum:', error);
        return throwError('Error updating forum');
      })
    );
  }


  deleteForum(id: number): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}/forums/delete-forum/${id}`);
  }

  
  likeForum(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/forums/forums/${id}/like`, null);
  }
  
  dislikeForum(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/forums/forums/${id}/dislike`, null).pipe(
      catchError(error => {
        console.error('Error disliking forum:', error);
        return throwError('Error disliking forum');
      })
    );
  }

  getTranslatedForum(forumId: number): Observable<string> {
    return this.http.get(`${this.BASE_URL}/forums/forums/${forumId}/translate`, { responseType: 'text' });
  }


  tweetForum(forumId: number): Observable<string> {
    return this.http.get(`${this.BASE_URL}/forums/forum/${forumId}`, { responseType: 'arraybuffer' }).pipe(
      map((buffer: ArrayBuffer) => new TextDecoder().decode(buffer)), // Convertit ArrayBuffer en string
      catchError((error) => {
        console.error('Erreur lors du partage du forum sur Twitter:', error);
        return throwError('Erreur lors du partage du forum sur Twitter');
      })
    );
  }
  uploadImage(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file, file.name);
    return this.http.post('URL_SERVICE_TIERS', formData).pipe(
      catchError(error => {
        console.error('Error uploading image:', error);
        return throwError('Error uploading image');
      })
    );
  }
  
}
 
  
  