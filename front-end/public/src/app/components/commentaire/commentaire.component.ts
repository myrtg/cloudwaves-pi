
import { CommentaireService } from 'src/app/services/commentaire.service';
import Swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';


interface Commentaire {
  idCommentaire: number;
  commentaire: string;
  likes: number;
  dislikes: number;
}
@Component({
  selector: 'app-commentaire',
  templateUrl: './commentaire.component.html',
  styleUrls: ['./commentaire.component.css']
})
export class CommentaireComponent implements OnInit{

  listCommentaires: Commentaire[] = [];
  newCommentaire: Commentaire = { idCommentaire: 0, commentaire: '', likes: 0, dislikes: 0 };
  
  constructor(private commentaireService: CommentaireService) {}

  ngOnInit() {
    this.loadCommentaires();
  }
  loadCommentaires() {
    this.commentaireService.getAllCommentaires().subscribe(
      (data: any[]) => {
        this.listCommentaires = data;
        this.displayCommentairesInSweetAlert(data);
      },
      (error) => {
        console.error('Error fetching commentaires:', error);
      }
    );
  }

  displayCommentairesInSweetAlert(commentaires: any[]) {
    let htmlContent = '';
    commentaires.forEach(commentaire => {
      htmlContent += `<p>${commentaire.titre} - ${commentaire.message}</p>`;
    });
    Swal.fire({
      title: 'Liste des commentaires',
      html: htmlContent,
      showConfirmButton: false,
      width: '600px'
    });
  }

  /**loadCommentaires() {
    // Chargez les commentaires depuis le service
    this.commentaireService.getAllCommentaires().subscribe(
      (data: Commentaire[]) => {
        this.listCommentaires = data;
      },
      (error) => {
        console.error('Error fetching commentaires:', error);
      }
    );
  }**/
 

  updateCommentaire(commentaire: Commentaire) {
    // Afficher une boîte de dialogue pour permettre la modification du commentaire
    Swal.fire({
      title: 'Update Commentaire',
      input: 'text',
      inputValue: commentaire.commentaire,
      showCancelButton: true,
      confirmButtonText: 'Update',
      cancelButtonText: 'Cancel',
      inputValidator: (value) => {
        if (!value) {
          return 'Please enter new commentaire!';
        }
        return undefined;
      }
    }).then((result) => {
      if (result.isConfirmed) {
        const updatedCommentaire = result.value;
        commentaire.commentaire = updatedCommentaire;
        // Appel du service pour mettre à jour le commentaire
        this.commentaireService.updateCommentaire(commentaire).subscribe(
          (response) => {
            console.log('Commentaire updated successfully:', response);
            this.loadCommentaires();
          },
          (error) => {
            console.error('Error updating commentaire:', error);
          }
        );
      }
    });
  }

  deleteCommentaire(commentaire: Commentaire) {
    // Supprimer le commentaire après confirmation
    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to delete this commentaire?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        // Appel du service pour supprimer le commentaire
        this.commentaireService.deleteCommentaire(commentaire.idCommentaire).subscribe(
          () => {
            console.log('Commentaire deleted successfully');
            this.loadCommentaires();
          },
          (error) => {
            console.error('Error deleting commentaire:', error);
          }
        );
      }
    });
  }

  addCommentaire() {
    // Ajouter un nouveau commentaire
    this.commentaireService.addCommentaire(this.newCommentaire).subscribe(
      (response) => {
        console.log('Commentaire added successfully:', response);
        this.loadCommentaires();
        this.newCommentaire = { idCommentaire: 0, commentaire: '', likes: 0, dislikes: 0 };
      },
      (error) => {
        console.error('Error adding commentaire:', error);
      }
    );
  }

  likeCommentaire(id: number) {
    // Liker un commentaire
    this.commentaireService.likeCommentaire(id).subscribe(
      () => {
        console.log('Commentaire liked successfully');
        this.loadCommentaires();
      },
      (error) => {
        console.error('Error liking commentaire:', error);
      }
    );
  }

  dislikeCommentaire(id: number) {
    // Disliker un commentaire
    this.commentaireService.dislikeCommentaire(id).subscribe(
      () => {
        console.log('Commentaire disliked successfully');
        this.loadCommentaires();
      },
      (error) => {
        console.error('Error disliking commentaire:', error);
      }
    );
  }

}