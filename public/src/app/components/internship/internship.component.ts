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
  }
}
