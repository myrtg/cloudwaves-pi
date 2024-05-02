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
import { ForumComponent } from './components/forum/forum.component';
import { LoginComponent } from './components/login/login.component';
import {HttpClient, HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import {CodeInputModule} from "angular-code-input";
import { ChatService } from './chat.service';
import { UserService } from './user.service';
import { ChatComponent } from './components/chat/chat.component';
import { AuthInterceptor } from './jwt-interceptor';

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
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CodeInputModule

  ],
  providers: [
    HttpClient,ChatService, 
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
