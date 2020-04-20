import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppRoutingModule} from '../../app-routing.module';

import {MaterialModule} from './../material/material.module'
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ContainerComponent } from './components/container/container.component';
import { CardComponent } from './components/card/card.component';


import {GipherService} from './services/gipher.service';
import { HomeComponent } from './components/home/home.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { RecommendationsComponent } from './components/recommendations/recommendations.component';
import {InterceptorService} from './services/interceptor.service';
import { AuthService } from 'src/app/modules/auth/auth.service';
import { DialogComponent } from './components/dialog/dialog.component';



@NgModule({
  declarations: [  HeaderComponent,  FooterComponent, ContainerComponent, CardComponent, HomeComponent, FavouriteComponent, RecommendationsComponent, DialogComponent, ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule,
    MaterialModule
    
  ],
  exports: [
    AppRoutingModule,
    HeaderComponent,
    FooterComponent
    ],
  providers:[
    GipherService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:InterceptorService,
      multi:true
    }
  ],
  entryComponents: [
    DialogComponent
  ]
})
export class GipherModule { }