import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'jhi-choose-plane',
  templateUrl: './choose-plane.component.html',
  styleUrls: [
    'choose-plane.css'
  ]
})
export class ChoosePlaneComponent implements OnInit {

  message: string;

  constructor() {
    this.message = 'ChoosePlaneComponent message';
  }

  ngOnInit() {
  }

}
