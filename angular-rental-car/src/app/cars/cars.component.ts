import { Component, OnInit } from '@angular/core';
import { Car } from '../car';
import { CarService } from '../car.service';
import { ImageService } from '../image.service';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})
export class CarsComponent implements OnInit {

  cars : Car[];
  images: any;

  constructor(
    private carService : CarService,
    private imageService : ImageService
    ) { }

  ngOnInit(): void {
    this.getCars();
  }

  //retrieve the cars from the service
  getCars(): void {
    this.carService.getCars().subscribe(cars => this.cars = cars);
    this.images = this.imageService.getImages();
  }


}
