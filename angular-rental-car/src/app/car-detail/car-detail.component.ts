import { Component, OnInit, Input } from '@angular/core';
import { Location, CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { Car } from '../car';
import { CarService } from '../car.service';
import { ImageService } from '../image.service';

@Component({
  selector: 'app-car-detail',
  templateUrl: './car-detail.component.html',
  styleUrls: ['./car-detail.component.css']
})
export class CarDetailComponent implements OnInit {

  car : Car;
  image: any;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private location: Location,
    private imageService: ImageService,
  ) { }

  ngOnInit(): void {
    this.getCar();
  }

  getCar(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.carService.getCar(id)
      .subscribe(car => this.car = car);

    this.image = this.imageService.getImage(id);
  }

  goBack(): void {
    this.location.back();
  }

}
