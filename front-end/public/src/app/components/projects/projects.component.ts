import { Component, OnInit } from '@angular/core';
import { Categorie, Projet } from 'src/app/classes/Classes';
import { ProjectService } from 'src/app/components/projects.service';
import { MatDialog } from '@angular/material/dialog';
import { ViewMoreProjectComponent } from '../view-more-project/view-more-project.component';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css'],
})
export class ProjectsComponent implements OnInit {
  projects: Projet[] = [];
  categories: Categorie[] = [];
  filteredProjects: Projet[] = [];
  searchQuery: string = '';
  selectedCategory: any = null;

  constructor(private projectService: ProjectService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getAllProjects();
    this.getAllCategories();
  }

  getAllProjects() {
    this.projectService.getAllProjects().subscribe(
      (data: Projet[]) => {
        this.projects = data;
        this.filteredProjects = data;
      },
      (error: any) => {
        console.log('Error: ', error);
      }
    );
  }

  getAllCategories() {
    this.projectService.getAllCategories().subscribe(
      (data: Categorie[]) => {
        this.categories = data;
      },
      (error: any) => {
        console.log('Error: ', error);
      }
    );
  }

  filterProjects() {
    let filteredProjects = this.projects;

    // Filter by search query
    if (this.searchQuery) {
      filteredProjects = filteredProjects.filter((project: any) =>
        project.titre.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }

    // Filter by selected category
    if (this.selectedCategory) {
      this.categories.filter((category) => {
        if (category.idCategorie == this.selectedCategory) {
          filteredProjects = category.sousCategories
            .flatMap((subCategory) => {
              return subCategory.projets;
            });
        }
      });
    }

    this.filteredProjects = filteredProjects;
  }

  openViewMoreDialog(project: Projet): void {
    this.dialog.open(ViewMoreProjectComponent, {
      width: '450px',
      data: project
    });
  }
}