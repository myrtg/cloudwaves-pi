import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categorie, Projet, SousCategorie } from 'src/app/classes/Classes';
import { ProjectService } from 'src/app/project.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-view-more-project',
  templateUrl: './view-more-project.component.html',
  styleUrls: ['./view-more-project.component.css'],
})
export class ViewMoreProjectComponent implements OnInit {
  categories: Categorie[] = [];
  subCategories: SousCategorie[] = [];
  foundCategory: Categorie = { idCategorie: 0, nomCategorie: '', sousCategories: [] };
  foundSubCategory: SousCategorie = { idSousCategorie: 0, nomSousCategorie: '', categorie: { idCategorie: 0, nomCategorie: '', sousCategories: [] }, projets: [] };
  fileUrl: SafeUrl | undefined;

  constructor(
    public dialogRef: MatDialogRef<ViewMoreProjectComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Projet,
    public projectService: ProjectService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.getAllCategories();
    this.getAllSubCategories();
    this.fileUrl = this.data.fichierRapport ? this.sanitizer.bypassSecurityTrustUrl(this.data.fichierRapport) : "Undefined";
  }

  onClose(): void {
    this.dialogRef.close();
  }

  getAllCategories() {
    this.projectService.getAllCategories().subscribe(
      (data: Categorie[]) => {
        this.categories = data;
        this.findCategory();
      },
      (error: any) => {
        console.log('Error: ', error);
      }
    );
  }

  findCategory() {
    this.categories.forEach(category => {
      category.sousCategories.forEach(subCategory => {
        subCategory.projets.forEach(project => {
          if (project.idProjet === this.data.idProjet) {
            this.foundCategory = category;
            console.log('Found category: ', this.foundCategory)
          }
        });
      });
    });
  }

  getAllSubCategories() {
    this.projectService.getSubCategories().subscribe(
      (data: SousCategorie[]) => {
        this.subCategories = data;
        this.findSubCategory();
      },
      (error: any) => {
        console.log('Error: ', error);
      }
    );
  }

  findSubCategory() {
    this.subCategories.forEach(subCategory => {
      subCategory.projets.forEach(project => {
        if (project.idProjet === this.data.idProjet) {
          this.foundSubCategory = subCategory;
          console.log('Found subcategory: ', this.foundSubCategory)
        }
      });
    });
  }

  downloadReport(projectId: number, projectTitle: string) {
    this.projectService.downloadFile(projectId).subscribe(response => {
      const data = response;
      const blob = new Blob([data], { type: 'application/octet-stream' });
      const url = window.URL.createObjectURL(blob);
      const fileName = projectTitle + '.txt';
      this.fileUrl = this.sanitizer.bypassSecurityTrustUrl(url);
      const link = document.createElement('a');
      link.href = url;
      link.download = fileName;
      link.click();
    });
  }
}
