import { Component, OnInit } from '@angular/core';

import {AuthService} from '../auth.service';
import {User} from '../user';

import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

export const TOKEN_NAME="jwt_token";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User;
  constructor(
    private authService: AuthService,
    private snackBar: MatSnackBar,
    private router:Router
  ) { 
    this.user=new User();
  }

  ngOnInit() {
  }

  login(){
    this.authService.loginUser(this.user).subscribe(data=>{
      if(data){
        localStorage.setItem(TOKEN_NAME,data.body["token"]);
        this.snackBar.open(data.body["message"],"",{
          duration:1000
        });
        this.authService.isUserLoggedIn.next(true);
        this.router.navigate(["/home"])
      }
    },
    error =>{
      if(error.status===404){
        const errorMsg=error.error.message;
        this.snackBar.open(errorMsg,"",{
          duration:1000
        });
      }
    });
  }

}
