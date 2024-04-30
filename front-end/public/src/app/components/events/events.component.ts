import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventService } from './event.service';
import { Event } from './event.model';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    dateClick: (arg) => this.handleDateClick(arg),
    plugins: [dayGridPlugin, interactionPlugin],
  };
  constructor(private eventService: EventService) { }
  events: Event[] = [];
  formEvent!: FormGroup
  selectedFile!: File;
  ngOnInit(): void {
    this.fetchEvents();
    this.formEvent = new FormGroup({
      name: new FormControl('', [Validators.required]),
      date_debut: new FormControl('', [Validators.required]),
      date_fin: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      nb_places: new FormControl('', [Validators.required, Validators.min(1)]),
      titre: new FormControl('', [Validators.required]),
      tutor: new FormControl('', [Validators.required]),
      image: new FormControl('', [Validators.required,])
    })
  }

  handleDateClick(arg:any){
    alert("Date Click : "+arg.dateStr)
  }
  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  onSubmit() {
    if (this.formEvent.valid) {
      const formData = new FormData();
      formData.append('name', this.formEvent.get('name')!.value);
      formData.append('date_debut', this.formEvent.get('date_debut')!.value);
      formData.append('date_fin', this.formEvent.get('date_fin')!.value);
      formData.append('description', this.formEvent.get('description')!.value);
      formData.append('nb_places', this.formEvent.get('nb_places')!.value);
      formData.append('titre', this.formEvent.get('titre')!.value);
      formData.append('tutor', this.formEvent.get('tutor')!.value);

      formData.append('image', this.selectedFile, this.selectedFile.name); // Append the file object with namejkkjjk
      // const formData = this.formEvent.value;
      this.eventService.addEvent(formData).subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
  fetchEvents(): void {
    this.eventService.findAll().subscribe(
      events => {
        this.events = events;
        console.log("Event : ", events);
        this.calendarOptions.events = this.events.map(event => ({

          title: event.titre,
          start: event.dateDebut // Assuming your event object has a 'start' property
        }));
      },
      error => {
        console.log("Error getting events", error);

      }
    );
    console.log("Options : ", this.calendarOptions)
  }
  updateEvent(event: Event): void {
    console.log("Event : ", event);
    this.formEvent.setValue({
      "name": event.nom,
      "date_debut": event.dateDebut,
      "date_fin": event.dateFin,
      "description": event.description,
      "nb_places": event.nb_places,
      "titre": event.titre,
      "tutor": event.tutor,
      "image": event.image,
    });

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
  getQrCode(id: number): void {
    this.eventService.generateQRCode(id)
  }
  deleteEventById(id: number) {
    this.eventService.deleteEvent(id).subscribe(
      (resonse) => {
        console.log("Event deleted : ", resonse);
        this.fetchEvents();
      },
      (error) => {
        console.log("Error : ", error);
      }
    )
  }

}
