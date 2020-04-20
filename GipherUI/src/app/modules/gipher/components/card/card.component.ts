import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Favourite } from 'src/app/modules/gipher/domain/favourite';
import { MatDialog } from '@angular/material';
import { DialogComponent } from 'src/app/modules/gipher/components/dialog/dialog.component';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input()
  gif: Favourite;
  @Input()
  fData:boolean;
  @Input()
  rData:boolean;
  @Input()
  count:number;
  @Output()
  add2Favourite = new EventEmitter();
  @Output()
  deleteGifFromFavourite=new EventEmitter();
  @Output()
  addDescription2Gif=new EventEmitter();
  constructor(
    private dialog:MatDialog
  ) { }

  ngOnInit() {
  }

  addButtonClick(gif) {
    this.add2Favourite.emit(gif);
  }

  deleteGif(gif){
    console.log("delete called");
    this.deleteGifFromFavourite.emit(gif);
  }

  addDescription(){
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '260px',
      data:{comments:this.gif.comments}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('result comment',result);
      this.gif.comments=result;
      this.addDescription2Gif.emit(this.gif);
    });
  }

}
