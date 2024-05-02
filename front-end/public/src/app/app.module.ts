import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { EventsComponent } from './components/events/events.component';
import { PfebookComponent } from './components/pfebook/pfebook.component';
import { InternshipComponent } from './components/internship/internship.component';
import { FaqComponent } from './components/faq/faq.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { BloceventsComponent } from './components/blocevents/blocevents.component';
import { ChatComponent } from './components/chat/chat.component';
import { ForumComponent } from './components/forum/forum.component';
import { HttpClientModule } from '@angular/common/http';
import { ForumAddComponent } from './components/forum-add/forum-add.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    EventsComponent,
    PfebookComponent,
    InternshipComponent,
    FaqComponent,
    ContactusComponent,
    BloceventsComponent,
    ChatComponent,
    ForumComponent,
    ForumAddComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, 
    CommonModule,
      FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
  



})
export class AppModule { }
