import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './modules/gipher/components/home/home.component';
import { FavouriteComponent } from './modules/gipher/components/favourite/favourite.component';
import { RecommendationsComponent } from './modules/gipher/components/recommendations/recommendations.component';
import { LoginComponent } from 'src/app/modules/auth/login/login.component';
import { RegisterComponent } from 'src/app/modules/auth/register/register.component';
import {AuthGuardService} from 'src/app/modules/gipher/services/auth-guard.service';

const routes: Routes = [
{
  path:"",
  component:HomeComponent,
  canActivate:[AuthGuardService]
},
{
  path:"home",
  component:HomeComponent,
  canActivate:[AuthGuardService]
},
{
  path:"favourite",
  component:FavouriteComponent,
  canActivate:[AuthGuardService]

},
{
  path:"recommendations",
  component:RecommendationsComponent,
  canActivate:[AuthGuardService]
},
{
  path:"login",
  component:LoginComponent
},
{
  path:"register",
  component:RegisterComponent
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
