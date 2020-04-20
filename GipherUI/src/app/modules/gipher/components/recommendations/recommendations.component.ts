import { Component, OnInit } from '@angular/core';
import { GipherService } from 'src/app/modules/gipher/services/gipher.service';
import { Favourite } from 'src/app/modules/gipher/domain/favourite';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {

  gifs: Array<Favourite>;
  rData = true;
  count=1;
  statuscode: number;
  constructor(
    private gipherService:GipherService,
    private matSnackbar: MatSnackBar
  ) {
    this.gifs=[];
   }

  ngOnInit() {
    const message="No recommendations !!!!";
    this.gipherService.getAllRecommendations().subscribe(data =>{
      this.gifs=data;
      if(data.length ===0){
        this.matSnackbar.open(message,"",{
          duration:1000
        });
      }
    });
  }

  

}
