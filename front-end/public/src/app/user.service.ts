import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/v1'; 

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/user.addUser`, user);
  }

  disconnectUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/user.disconnectUser`, user);
  }

  getConnectedUsers(): Observable<User[]> {
    var users=this.http.get<User[]>(`${this.baseUrl}/users`);
    console.log("userss",users )
    return users;
  }
}
