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

import { BrowserModule } from '@angular/platform-browser';
import { NotFoundComponent } from './not-found/not-found.component';
import { ChatComponent } from './components/chat/chat.component';
import { BetterDatePipe } from './better-date.pipe';
import { AuthInterceptor } from './jwt-interceptor';
import { LoginComponent } from './components/login/login.component';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RegisterComponent } from './components/register/register.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { CodeInputModule } from 'angular-code-input';
import { NgxCaptchaModule } from 'ngx-captcha';
import { MatPasswordStrengthModule } from "@angular-material-extensions/password-strength";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ProjectsComponent } from './components/projects/projects/projects.component';
import { MatDialog, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ViewMoreProjectComponent } from './components/view-more-project/view-more-project.component';




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
    ForumComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    ForgotPasswordComponent,
    ProjectsComponent,
    ViewMoreProjectComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
    CodeInputModule,
    MatPasswordStrengthModule.forRoot(),
    MatInputModule,
    MatIconModule,
    MatDialogModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    HttpClient
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
