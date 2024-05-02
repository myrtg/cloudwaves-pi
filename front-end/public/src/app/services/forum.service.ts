import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  
  BASE_URL = 'http://localhost:8088';

  constructor(private http: HttpClient) {}

  getAllForums(): Observable<any> {
    return this.http.get(`${this.BASE_URL}/forum/forums/getAllForum`).pipe(
      catchError(error => {
        console.error('Error fetching all forums:', error);
        return throwError('Error fetching all forums');
      })
    );
  }

  addForum(forum: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/forum/forums/add-forum`, forum).pipe(
      catchError(error => {
        console.error('Error adding forum:', error);
        return throwError('Error adding forum');
      })
    );
  }

  updateForum(forum: any): Observable<any> {
    return this.http.put(`${this.BASE_URL}/forum/forums/update-forum`, forum).pipe(
      catchError(error => {
        console.error('Error updating forum:', error);
        return throwError('Error updating forum');
      })
    );
  }


  deleteForum(id: number): Observable<any> {
    return this.http.delete<any>(`${this.BASE_URL}/forum/forums/delete-forum/${id}`);
  }

  
  likeForum(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/forum/forums/forums/${id}/like`, null);
  }
  
  dislikeForum(id: number): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/forum/forums/forums/${id}/dislike`, null).pipe(
      catchError(error => {
        console.error('Error disliking forum:', error);
        return throwError('Error disliking forum');
      })
    );
  }

  getTranslatedForum(forumId: number): Observable<string> {
    return this.http.get(`${this.BASE_URL}/forum/forums/forums/${forumId}/translate`, { responseType: 'text' });
  }
  
}
