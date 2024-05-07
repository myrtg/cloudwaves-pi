import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidatureService } from 'src/app/Service/candidature/candidature.service';

@Component({
  selector: 'app-candidature',
  templateUrl: './candidature.component.html',
  styleUrls: ['./candidature.component.css']
})
export class CandidatureComponent {
  candidature: any;

  constructor(private es: CandidatureService, private ac: ActivatedRoute, private router: Router) { };

  ngOnInit(): void {
    this.es.getAllData().subscribe((response) => { this.candidature = response })

    this.es.delete(this.ac.snapshot.params['idCandidature']).subscribe(
      () =>
        this.router.navigate(['candidate'])
    )
  }


  delete(id: any) {
    this.es.delete(id).subscribe(() => {
      this.candidature = this.candidature.filter((candidature: any) => candidature.idCandidature != id)
    }, error => {
      console.log(error);
    });
  }
}
