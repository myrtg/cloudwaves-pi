import { Component, OnInit } from '@angular/core';
import { ForumService } from 'src/app/services/forum.service';
import Swal from 'sweetalert2';

@Component({
  
  selector: 'app-forum-add',
  templateUrl: './forum-add.component.html',
  styleUrls: ['./forum-add.component.css']
})

export class ForumAddComponent implements OnInit {
  newForumContent: string = '';
  newForumTitle: string = '';
  newForumEmail: string = '';



  constructor(private forumAddService: ForumService) {}

  ngOnInit(): void {}

  // Méthode pour ajouter un nouveau forum
  addForum() {
    const newForum = {
      content: this.newForumContent,
      title: this.newForumTitle,
      email: this.newForumEmail
    };

    this.forumAddService.addForum(newForum).subscribe(
      (response: any) => {
        console.log('Forum added successfully:', response);
        // Réinitialiser les valeurs des champs après l'ajout réussi
        this.newForumContent = '';
        this.newForumTitle = '';
        this.newForumEmail = '';
        // Actualiser la liste des forums après l'ajout réussi
        // Rediriger vers la liste des forums ou afficher un message de succès
      },
      (error) => {
        console.error('Error adding forum:', error);
      }
    );
  }
}