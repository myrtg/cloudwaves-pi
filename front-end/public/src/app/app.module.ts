import { NgModule } from '@angular/core';

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
import { ForumComponent } from './components/forum/forum.component';
import { LoginComponent } from './components/login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { RegisterComponent } from './components/register/register.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotFoundComponent } from './not-found/not-found.component';
import { ChatComponent } from './components/chat/chat.component';
import { CodeInputModule } from 'angular-code-input';
import { BetterDatePipe } from './better-date.pipe';
import { AuthInterceptor } from './jwt-interceptor';
import { CommentaireComponent } from './components/commentaire/commentaire.component';

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
    ForumComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    NotFoundComponent,
    BetterDatePipe,
    ChatComponent,
    CommentaireComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CodeInputModule,
 
   
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
