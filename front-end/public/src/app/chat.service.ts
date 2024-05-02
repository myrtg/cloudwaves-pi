// chat.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChatMessage } from './models/chat-message.model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private baseUrl = 'http://localhost:8080/api/v1'; 

  constructor(private http: HttpClient) {}

  getChatMessages(senderId: string, recipientId: string): Observable<ChatMessage[]> {
    console.log('senderId ', senderId, "recipientId ", recipientId)
    return this.http.get<ChatMessage[]>(`${this.baseUrl}/messages/${senderId}/${recipientId}`);
  }
}
