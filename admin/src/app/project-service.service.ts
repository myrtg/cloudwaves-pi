import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categorie, SousCategorie, Projet } from './classes/Classes';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(private http: HttpClient) { }

  // Categories

  getAllCategories(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>('http://localhost:9090/categorie');
  }

  addCategory(category: Categorie): Observable<any> {
    return this.http.post('http://localhost:9090/categorie/add', category);
  }

  editCategory(category: Categorie): Observable<any> {
    return this.http.put(`http://localhost:9090/categorie/updatecategorie/${category.idCategorie}`, category);
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.delete(`http://localhost:9090/categorie/remove/${id}`);
  }

  // Subcategories

  addSubCategory(subCategory: SousCategorie): Observable<any> {
    return this.http.post('http://localhost:9090/souscategorie/add', subCategory);
  }

  editSubCategory(subCategory: SousCategorie): Observable<any> {
    return this.http.put(`http://localhost:9090/souscategorie/updatesouscategorie/${subCategory.idSousCategorie}`, subCategory);
  }

  deleteSubCategory(id: number): Observable<any> {
    return this.http.delete(`http://localhost:9090/souscategorie/remove/${id}`);
  }

  // Projects

  addProject(project: FormData): Observable<any> {
    return this.http.post('http://localhost:9090/projet/add', project);
  }

  editProject(project: Projet): Observable<any> {
    return this.http.put(`http://localhost:9090/projet/update/${project.idProjet}`, project);
  }

  deleteProject(id: number): Observable<any> {
    return this.http.delete(`http://localhost:9090/projet/remove/${id}`);
  }

  downloadFile(projectId: number): Observable<Blob> {
    return this.http.get(`http://localhost:9090/projet/download/${projectId}`, { responseType: 'blob' });
  }
}
