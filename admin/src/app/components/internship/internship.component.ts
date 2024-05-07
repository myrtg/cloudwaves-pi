import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OffreStageService } from 'src/app/Service/OffreStage/offre-stage.service';

@Component({
  selector: 'app-internship',
  templateUrl: './internship.component.html',
  styleUrls: ['./internship.component.css']
})
export class InternshipComponent {
  offre: any;

  constructor(private es: OffreStageService, private ac: ActivatedRoute, private router: Router) { };

  ngOnInit(): void {
    this.es.getAllData().subscribe((response) => { this.offre = response })

    this.es.deleteOffre(this.ac.snapshot.params['idOffre']).subscribe(
      () =>
        this.router.navigate(['internship'])
    )
  }


  delete(id: any) {
    this.es.deleteOffre(id).subscribe(() => {
      this.offre = this.offre.filter((offre: any) => offre.idOffre != id)
    }, error => {
      console.log(error);
    });
  }

}
