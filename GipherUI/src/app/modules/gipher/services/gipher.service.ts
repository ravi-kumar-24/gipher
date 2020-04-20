import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, ObservableLike } from 'rxjs';
import { Favourite } from 'src/app/modules/gipher/domain/favourite';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
export const USER_NAME="username";

@Injectable({
  providedIn: 'root'
})
export class GipherService {

  thirdPartyAPI:string;
  managerBackend:string;
  recommendationsBackend:string;
  apiKey:string;
  limit:string;
  userName:string;
  public searchGifString:BehaviorSubject<string>=new BehaviorSubject<string>("cats");


  constructor(private httpClient: HttpClient) {
    this.thirdPartyAPI="https://api.giphy.com/v1/gifs/search?api_key=l9hJzMcWztLWfH9xUozhsMlQ7syZxlpr&q=";
    this.limit="&limit=5";
    this.managerBackend="http://localhost:9090/giphermanager/api/v1/gifmanager/user/"
    this.recommendationsBackend="http://localhost:9090/gipherrecommendations/api/v1/recommendations";
   }

   getAllGifDetails(query: string): Observable<any> {
    const url = `${this.thirdPartyAPI}${query}${this.limit}`;
    console.log(url);
    return this.httpClient.get(url);
  }

  add2Favourite(gif:Favourite):Observable<any>{
    this.userName=sessionStorage.getItem(USER_NAME);
    const url=this.managerBackend+this.userName+"/favourite";
    return this.httpClient.post(url,gif,{
      observe:"response"
    });
  }

  getAllGIF4Favourite():Observable<Favourite[]>{
    this.userName=sessionStorage.getItem(USER_NAME);
    const url=this.managerBackend+this.userName+"/favourites";
    return this.httpClient.get<Favourite[]>(url);
  }
  
  getAllRecommendations():Observable<Favourite[]>{
    return this.httpClient.get<Favourite[]>(this.recommendationsBackend);
  }

  deleteGifFromFavourite(gif:Favourite){
    this.userName=sessionStorage.getItem(USER_NAME);
    const url=this.managerBackend+this.userName+"/"+gif.id;
    return this.httpClient.delete(url);
  }

  updateORAddDescription(gif:Favourite){
    this.userName=sessionStorage.getItem(USER_NAME);
    const url=this.managerBackend+this.userName+"/"+gif.id;
    return this.httpClient.patch(url,gif,{
      observe:"response"
    });
  }

 }
