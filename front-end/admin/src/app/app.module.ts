import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AsideComponent } from './components/aside/aside.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ChatComponent } from './components/chat/chat.component';
import { HomeComponent } from './components/home/home.component';
import { CategorieComponent } from './components/categorie/categorie.component';
import { ForumComponent } from './components/forum/forum.component';
import { EventsComponent } from './components/events/events.component';
import { PfebookComponent } from './components/pfebook/pfebook.component';
import { InternshipComponent } from './components/internship/internship.component';
import { UserComponent } from './components/user/user.component';
import { LoginComponent } from './login/login.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { AjoutQuestionComponent } from './components/ajout-question/ajout-question.component';
import { QuizComponent } from './components/quiz/quiz.component';
import { AjoutQuizComponent } from './components/ajout-quiz/ajout-quiz.component';
import { OffreStageComponent } from './components/offre-stage/offre-stage.component';
import { CandidatureComponent } from './components/candidature/candidature.component';

@NgModule({
  declarations: [
    AppComponent,
    AsideComponent,
   
    ChatComponent,
        HomeComponent,
        CategorieComponent,
        ForumComponent,
        EventsComponent,
        PfebookComponent,
        InternshipComponent,
        UserComponent,
        LoginComponent,
        QuestionsComponent,
        AjoutQuestionComponent,
        QuizComponent,
        AjoutQuizComponent,
        OffreStageComponent,
        CandidatureComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }