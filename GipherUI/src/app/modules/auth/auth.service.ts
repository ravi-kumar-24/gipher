import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from './user';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

export const USER_NAME="username";
export const TOKEN_NAME="jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authBackEnd:string;
  public isUserLoggedIn:BehaviorSubject<boolean>=new BehaviorSubject<boolean>(false);
  constructor(private httpClient:HttpClient) { 
    this.authBackEnd="http://localhost:9090/accountmanager/api/v1/accountmanager/";
  }

  registerUser(user:User){
    const url=this.authBackEnd+"register";
    return this.httpClient.post(url,user,{observe:"response"});
  }

  loginUser(user:User){
    const url=this.authBackEnd+"login";
    sessionStorage.setItem(USER_NAME,user.username);
    return this.httpClient.post(url,user,{observe:"response"});
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }

  isTokenExpired(token?:string):boolean{
    if(localStorage.getItem(TOKEN_NAME)){
      return true;
    }else{
      return false;
    }
  }

  logout(){
    localStorage.removeItem(TOKEN_NAME);
    localStorage.clear();
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
  }

}
