import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {
  private forgotPasswordUrl ='http://localhost:8080/api/v1/auth/forgot-password'; // Replace with your API endpoint

  constructor(private http: HttpClient) { }

  forgotPassword(email: string) {
    return this.http.post<any>(this.forgotPasswordUrl, { email });
  }
}
