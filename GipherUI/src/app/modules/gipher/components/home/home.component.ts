import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { GipherService } from '../../../gipher/services/gipher.service';

import { Favourite } from '../../../gipher/domain/favourite';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  favouriteObj: Favourite;
  gifs: Array<Favourite>;
  gifSearchText: string;
  statusCode: number;
  errorStatus:string;

  value:string;

  constructor(
    private gipherService: GipherService,
    private routes: ActivatedRoute,
    private matSnackBar: MatSnackBar
  ) {
    this.gifs = [];
  }

  ngOnInit() {
    this.gipherService.searchGifString.subscribe(value=>{
      console.log("intercepted by home"+value);
      this.value=value;
      this.searchGifs(this.value);
    });
  }

  searchGifs(value:string){
    this.gipherService.getAllGifDetails(this.value).subscribe(gifData => {
      const data = gifData['data'];
      this.gifs = [];
      data.forEach(element => {
        this.favouriteObj = new Favourite();
        this.favouriteObj.id = element['id'];
        this.favouriteObj.title = element['title'];
        if (element['username'] === "" || element['username'] == null) {
          this.favouriteObj.username = "Anonymous";
        } else {
          this.favouriteObj.username = element['username'];
        }
        this.favouriteObj.url = element['images']['480w_still']['url'];

        this.gifs.push(this.favouriteObj);
      });
    });
  }

  add2Favourite(gif) {
    console.log("inside container " + gif);
    this.gipherService.add2Favourite(gif).subscribe(data => {
      this.statusCode = data.status;
      if (this.statusCode === 201) {
        this.matSnackBar.open("Added to Favourite list !!", "", {
          duration: 1000
        });
      }

    },
      error => {
        console.log("error", error);
        this.errorStatus = `${error.status}`;
        console.log("error status", this.errorStatus);
        const errorMsg = `${error.error}`;
        console.log("error messgae", errorMsg);
        this.statusCode = parseInt(this.errorStatus, 10);
        if (this.statusCode === 409) {
          this.matSnackBar.open(errorMsg, "", {
            duration: 1000
          });
          this.statusCode = 0;
        }
      });
  }
}
