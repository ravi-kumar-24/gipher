import { Component, OnInit } from '@angular/core';
import { Favourite } from 'src/app/modules/gipher/domain/favourite';
import { GipherService } from 'src/app/modules/gipher/services/gipher.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  gifs: Array<Favourite>;
  fData = true;
  statuscode: number;
  constructor(
    private gipherService: GipherService,
    private matSnackbar: MatSnackBar) {
    this.gifs = [];
  }


  ngOnInit() {
    const message="Wishlist is empty !!!!";
    this.gipherService.getAllGIF4Favourite().subscribe(data =>{
      this.gifs=data;
      if(data.length ===0){
        this.matSnackbar.open(message,"",{
          duration:1000
        });
      }
    });
  }

  deleteGifFromFavourite(gif){
    console.log("favourite component",gif);
    this.gipherService.deleteGifFromFavourite(gif).subscribe(data =>{
      console.log("deleted",gif);
      const index=this.gifs.indexOf(gif);
      this.gifs.splice(index,1);
      this.matSnackbar.open("deleted. ","",{
        duration:1000
      });
    });
    return this.gifs;
  }

  addDescription2Gif(gif){
    console.log("gif :",gif)
    this.gipherService.updateORAddDescription(gif).subscribe((data)=>{
      this.matSnackbar.open("Successfully updated","",{
        duration:1000
      });
    },
    error =>{
      console.log("error",error);
    }
    );
  }

}
