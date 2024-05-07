import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OffreStage } from 'src/app/models/OffreStage';

@Injectable({
  providedIn: 'root'
})
export class OffreStageService {

  constructor(private  httpClient:HttpClient) { }

  getAllData(): Observable<OffreStage[]> {
    return this.httpClient.get<OffreStage[]> ('http://localhost:8080/OffreStage/allOffers');
  }

  addOffre(offreStage: any) {
    return this.httpClient.post('http://localhost:8080/OffreStage/add', offreStage);
  }

  deleteOffre(id:any) {
    return this.httpClient.delete('http://localhost:8080/OffreStage/delete/'+id);
  }

}