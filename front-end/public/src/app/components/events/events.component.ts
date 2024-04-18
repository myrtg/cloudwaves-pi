import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit{
  formEvent!:FormGroup
  ngOnInit(): void {
    this.formEvent = new FormGroup({
      date_debut: new FormControl('',[Validators.required]),
      date_fin: new FormControl('',[Validators.required]),
      description: new FormControl('',[Validators.required,Validators.pattern('^[A-Z]{1}[a-zA-Z]*$')]),
      nb_places: new FormControl('',[Validators.required,Validators.pattern('^[1-9]*$'),Validators.min(1)]),
      titre: new FormControl('',[Validators.required,Validators.pattern('^[A-Z]{1}[a-zA-Z]*$')]),
      tutor: new FormControl('',[Validators.required,Validators.pattern('^[A-Z]{1}[a-zA-Z]*$')]),
    })
  }

}
