import { Component, OnInit } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Categorie, SousCategorie, Projet } from "src/app/classes/Classes";
import { ProjectServiceService } from "src/app/project-service.service"

@Component({
  selector: "app-categorie",
  templateUrl: "./categorie.component.html",
  styleUrls: ["./categorie.component.css"],
})
export class CategorieComponent implements OnInit {
  categories: Categorie[] = [];
  categoryName: string = "";
  subCategoryName: any;
  projectTitle: any;
  projectDescription: any;
  selectedFile: any;
  fileUrl: any;

  constructor(private projectService: ProjectServiceService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.loadAllCategories();
  }

  async loadAllCategories(): Promise<void> {
    try {
      const categories = await this.projectService.getAllCategories().toPromise();
      this.categories = categories ?? [];
      console.log(this.categories);
    } catch (error) {
      console.error("Failed to load categories:", error);
    }
  }

  addCategory() {
    // Ensure category name is not empty
    if (!this.categoryName.trim()) {
      console.error("Category name cannot be empty");
      return;
    }

    // Create a new category object
    const newCategory: Categorie = {
      idCategorie: 0, // ID will be assigned by the backend
      nomCategorie: this.categoryName.trim(),
      sousCategories: [], // Initialize with an empty array
    };

    // Call the service to add the category
    try {
      this.projectService.addCategory(newCategory).subscribe((response: any) => {
        console.log("Category added successfully:", response);
        // Reload categories after addition
        this.loadAllCategories();
        // Reset category name input
        this.categoryName = "";
      });
    } catch (error) {
      console.error("Failed to add category:", error);
    }
  }

  deleteCategory(id: number) {
    try {
      this.projectService.deleteCategory(id).subscribe(() => {
        this.loadAllCategories();
      });
    } catch (error) {
      console.error("Failed to delete category:", error);
    }
  }

  deleteSubCategory(id: any) {
    try {
      this.projectService.deleteSubCategory(id).subscribe(() => {
        this.loadAllCategories();
      });
    } catch (error) {
      console.error("Failed to delete subcategory:", error);
    }
  }

  deleteProject(id: any) {
    try {
      this.projectService.deleteProject(id).subscribe((data: any) => {
        this.loadAllCategories();
        console.log(data);
      });
    } catch (error) {
      console.error("Failed to delete project:", error);
    }
  }

  addSubCategory(id: number) {
    if (!this.subCategoryName.trim()) {
      console.error("Subcategory name cannot be empty");
      return;
    }

    const newSubCategory: any = {
      idSousCategorie: 0,
      nomSousCategorie: this.subCategoryName.trim(),
      categorie: { idCategorie: id, nomCategorie: "", sousCategories: [] },
      projets: [],
      categoryId: id,
      id_categorie: id
    };

    try {
      this.projectService.addSubCategory(newSubCategory).subscribe((response: any) => {
        console.log("Subcategory added successfully:", response);
        this.loadAllCategories();
        this.subCategoryName = "";
      });
    } catch (error) {
      console.error("Failed to add subcategory:", error);
    }
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  addProject(id: number) {
    const formData = new FormData();
    formData.append("file", this.selectedFile);
    console.log("Selected file:", this.selectedFile);
    formData.append(
      "projet",
      JSON.stringify({
        titre: this.projectTitle,
        description: this.projectDescription,
        subCategoryId: id,
      })
    );

    this.projectService.addProject(formData).subscribe((response: any) => {
      console.log("Project added successfully:", response);
      this.loadAllCategories();
      this.projectTitle = "";
      this.projectDescription = "";
      this.selectedFile = null;
    });
  }

  downloadReport(projectId: number, projectTitle: string) {
    this.projectService.downloadFile(projectId).subscribe(response => {
      const blob = new Blob([response], { type: 'application/octet-stream' });
      const downloadLink = document.createElement('a');
      downloadLink.href = window.URL.createObjectURL(blob);
      downloadLink.download = `${projectTitle}_Report.txt`;
      downloadLink.click();
    });
  }
  

  editCategory(category: Categorie, event: any) {
    if (event.target.innerText.trim() !== category.nomCategorie) {
      category.nomCategorie = event.target.innerText.trim();
      try {
        this.projectService.editCategory(category).subscribe((response: any) => {
          console.log("Category edited successfully:", response);
          this.loadAllCategories();
        });
      } catch (error) {
        console.error("Failed to edit category:", error);
      }
    }
  }

  editSubCategory(subCategory: SousCategorie, event: any) {
    if (event.target.innerText.trim() !== subCategory.nomSousCategorie) {
      subCategory.nomSousCategorie = event.target.innerText.trim();
      try {
        this.projectService.editSubCategory(subCategory).subscribe((response: any) => {
          console.log("Subcategory edited successfully:", response);
          this.loadAllCategories();
        });
      } catch (error) {
        console.error("Failed to edit subcategory:", error);
      }
    }
  }

  editProjectTitle(project: Projet, event: any) {
    const newTitle = event.target.innerText.trim();
    if (newTitle !== project.titre) {
      project.titre = newTitle;
      this.saveProjectChanges(project);
    }
  }
  
  editProjectDescription(project: Projet, event: any) {
    const newDescription = event.target.innerText.trim();
    if (newDescription !== project.description) {
      project.description = newDescription;
      this.saveProjectChanges(project);
    }
  }
  
  saveProjectChanges(project: Projet) {
    try {
      this.projectService.editProject(project).subscribe((response: any) => {
        console.log("Project edited successfully:", response);
        this.loadAllCategories();
      });
    } catch (error) {
      console.error("Failed to edit project:", error);
    }
  }
}
