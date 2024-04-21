import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventService } from './event.service';
import { Event } from './event.model';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit{

constructor(private eventService: EventService) { }
  events: Event[] = [];
  formEvent!:FormGroup
  ngOnInit(): void {
    this.fetchEvents();
    this.formEvent = new FormGroup({
      name: new FormControl('',[Validators.required]),
      date_debut: new FormControl('',[Validators.required]),
      date_fin: new FormControl('',[Validators.required]),
      description: new FormControl('',[Validators.required]),
      nb_places: new FormControl('',[Validators.required,Validators.min(1)]),
      titre: new FormControl('',[Validators.required]),
      tutor: new FormControl('',[Validators.required]),
      image: new FormControl('',[Validators.required,])
    })
  }
  onSubmit(){
    if (this.formEvent.valid)
    {
      const formData = this.formEvent.value;
      this.eventService.addEvent(formData).subscribe(
        (response) =>{
          console.log(response);
        },
        (error) =>{
          console.log(error);
        }
      );
    }
  }
  fetchEvents():void{
    this.eventService.findAll().subscribe(
      events => {
        this.events = events;
        console.log("Event : ",events);
      },
      error => {
        console.log("Error getting events",error);
        
      }
    );
  }
getImageUrl(image: Blob): string {
  if (image) {
    const reader = new FileReader();
    reader.readAsDataURL(image);
    reader.onload = () => {
      return reader.result as string;
    };
  }
  return ''; 
}
getQrCode(id:number):void{
  this.eventService.generateQRCode(id)
}

}
