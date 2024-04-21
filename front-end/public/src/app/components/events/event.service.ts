import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from './event.model';
@Injectable({
    providedIn: "root"
})
export class EventService {
    private apiUrl = 'http://localhost:8083/cloudwaves/api/evenements';

    constructor(private http: HttpClient) { }

    addEvent(eventData: any): Observable<any> {
        const url = `${this.apiUrl}/add`; // Assuming your API endpoint for adding users is /users
        return this.http.post(url, eventData);
    }
    public findAll(): Observable<Event[]> {
        const url = `${this.apiUrl}/getAll`; // Assuming your API endpoint for adding users is /users
        return this.http.get<Event[]>(url);
    }
    generateQRCode(id:number): void {
        const url = `http://localhost:8083/cloudwaves/api/evenements/generateQRCode?id=${id}`;
    
        // Send GET request to the server
        this.http.get(url, { responseType: 'blob' }).subscribe(
          (qrCode: Blob) => {
            // Handle the response here
            const blobUrl = URL.createObjectURL(qrCode);
            window.open(blobUrl, '_blank');
          },
          error => {
            console.error('Error generating QR code:', error);
          }
        );
      }


}
