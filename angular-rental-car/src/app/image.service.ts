import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  allImages =[
    { "id" : 1, "url": "assets/images/1.png", "video": "Kje4rkHjK_c"},
    { "id" : 2, "url": "assets/images/2.png", "video": "bjYrHkqOAMc"},
    { "id" : 3, "url": "assets/images/3.png", "video": "1TnApLouJ9o"},
    { "id" : 4, "url": "assets/images/4.png", "video": "i6yAMIGbmhE"}
  ]

  constructor() { }

  getImage(id: number) {

    for( let i = 0; i < this.allImages.length; i++)
    {
      if(this.allImages[i].id == id){
        return this.allImages[i];
      }
    }
  }

  getImages(){
    return this.allImages;
  }

}