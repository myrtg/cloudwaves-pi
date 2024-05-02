import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ForumService } from 'src/app/services/forum.service';


interface Forum {
  idForum: number;
  titre: string;
  message: string;
  email: string;
  likes: number;
  dislikes: number;
  translatedMessage?: string; // Ajout de la propriété translatedMessage
}

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {

  
 
    listForums: Forum[] = [];
  newForum: Forum = { idForum: 0, titre: '', message: '', email: '', likes: 0, dislikes: 0 };
  
  constructor(private forumService: ForumService) {}

  ngOnInit() {
    this.loadForums();
  }

  loadForums() {
    this.forumService.getAllForums().subscribe(
      (data: Forum[]) => {
        this.listForums = data;
        // Appel de la méthode pour récupérer la traduction pour chaque forum
        this.listForums.forEach(forum => {
          this.getTranslatedForumMessage(forum.idForum);
        });
      },
      (error) => {
        console.error('Error fetching forums:', error);
      }
    );
  }

  updateForum(forum: Forum) {
    Swal.fire({
      title: 'Update Forum',
      input: 'text',
      inputValue: forum.titre,
      showCancelButton: true,
      confirmButtonText: 'Update',
      cancelButtonText: 'Cancel',
      inputValidator: (value) => {
        if (!value) {
          return 'Please enter new title!';
        }
        return undefined;
      }
    }).then((result) => {
      if (result.isConfirmed) {
        const updatedTitre = result.value;
        forum.titre = updatedTitre;
        this.forumService.updateForum(forum).subscribe(
          (response) => {
            console.log('Forum updated successfully:', response);
            this.loadForums();
          },
          (error) => {
            console.error('Error updating forum:', error);
          }
        );
      }
    });
  }


  
  deleteForum(forum: any) {
    this.forumService.deleteForum(forum.idForum).subscribe(
      () => {
        console.log('Forum deleted successfully');
        this.loadForums();
  
      },
      (error) => {
        console.error('Error deleting forum:', error);
      }
    );
  }
  
  
  addForum() {
    this.forumService.addForum(this.newForum).subscribe(
      (response) => {
        console.log('Forum added successfully:', response);
        this.loadForums();
        this.newForum = { idForum: 0, titre: '', message: '', email: '', likes: 0, dislikes: 0 };
      },
      (error) => {
        console.error('Error adding forum:', error);
      }
    );
  }

  likeForum(id: number): void {
    this.forumService.likeForum(id).subscribe(
      () => {
        // Success: Like alert
        Swal.fire({
          icon: 'success',
          title: 'Forum Liked!',
          showConfirmButton: false,
          timer: 3000 // Auto close after 3 seconds
        }).then(() => {
          // Reload the forums after the alert is closed
          this.loadForums();
        });
      },
      (error) => {
        console.error("Error liking forum:", error);
        Swal.fire({
          icon: 'success',
          title: 'Forum Liked!',
          showConfirmButton: false,
          timer: 3000 // Auto close after 3 seconds
        }).then(() => {
          // Reload the forums after the alert is closed
          this.loadForums();
        });
      }
    );
  }
  
  

  dislikeForum(id: number): void {
    this.forumService.dislikeForum(id).subscribe(
      () => {
        // Success: Dislike alert
        Swal.fire({
          icon: 'success',
          title: 'dislike!',
          showConfirmButton: false,
          timer: 3000 // Auto close after 3 seconds
        }).then(() => {
          // Reload the forums after the alert is closed
          this.loadForums();
        });
      },
      (error) => {
        console.error("Error disliking forum:", error);
        Swal.fire({
          icon: 'success',
          title: 'dislike !',
          showConfirmButton: false,
          timer: 3000 // Auto close after 3 seconds
        }).then(() => {
          // Reload the forums after the alert is closed
          this.loadForums();
        });
      }
    );
  }
  

getTranslatedForumMessage(forumId: number) {
  this.forumService.getTranslatedForum(forumId).subscribe(
    (response: any) => {
      const forumIndex = this.listForums.findIndex(forum => forum.idForum === forumId);
      if (forumIndex !== -1) {
        this.listForums[forumIndex].translatedMessage = response;
      }
    },
    (error) => {
      console.error("Error fetching translated forum:", error);
    }
  );
}

}
