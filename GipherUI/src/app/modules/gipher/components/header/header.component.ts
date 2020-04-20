import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/modules/auth/auth.service';
import { GipherService } from 'src/app/modules/gipher/services/gipher.service';
export const TOKEN_NAME="jwt_token";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  showSearchBar:boolean;
  value:string;
  constructor(
    private router:Router,
    private authService:AuthService,
    private gipherService:GipherService
  ) { 
    this.showSearchBar=false;
    this.authService.isUserLoggedIn.subscribe(value=>{
    this.showSearchBar=value;
    })

    this.gipherService.searchGifString.subscribe(value=>{
      this.value=value;
    });
  }

  ngOnInit() {
  }

  goToHome(){
    console.log("home clicked");
    this.router.navigate(['/home']);
  }

  logout(){
    this.authService.logout();
    this.authService.isUserLoggedIn.next(false);
    this.router.navigate(['/login']);
  }

  onEnter(value:string){
    console.log("onEnter called");
    this.gipherService.searchGifString.next(value);
  }

}
