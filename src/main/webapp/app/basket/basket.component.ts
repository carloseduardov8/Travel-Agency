import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'jhi-basket',
  templateUrl: './basket.component.html',
  styleUrls: [
    'basket.css'
  ]
})
export class BasketComponent implements OnInit {

  message: string;

  constructor() {
    this.message = 'BasketComponent message';
  }

  ngOnInit() {
  }

}
