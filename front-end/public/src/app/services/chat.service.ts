import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private baseUrl: string = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  markMessagesAsRead(roomId: number, receiverUserId: number): void {
    this.http.put<void>(`${this.baseUrl}/markAsRead/${roomId}/${receiverUserId}`, {}).subscribe(
      () => {
        console.log('Messages marked as read successfully.');
      },
      (error) => {
        console.error('Error marking messages as read:', error);
      }
    );
  }


}