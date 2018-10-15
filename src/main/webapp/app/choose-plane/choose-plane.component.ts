import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
    selector: 'jhi-choose-plane',
    templateUrl: './choose-plane.component.html',
    styleUrls: ['choose-plane.css'],
    animations: [
        trigger('flipState', [
            state(
                'active',
                style({
                    transform: 'rotateY(179.9deg)'
                })
            ),
            state(
                'inactive',
                style({
                    transform: 'rotateY(0)'
                })
            ),
            transition('active => inactive', animate('500ms ease-out')),
            transition('inactive => active', animate('500ms ease-in'))
        ])
    ]
})
export class ChoosePlaneComponent implements OnInit {
	message: string;
    flip = 'inactive';
	seats: any[][];
	columns: any[];

    constructor() {
        this.message = 'ChoosePlaneComponent message';
		this.seats = new Array();

		let numOfSeats = 17;

		// Plane rows:
		this.rows = ["A", "B", "C", "D"];
		// Loops through rows:
		for (let j=0; j< 4; j++){
			this.seats[j] = new Array();
			// Loops through seats:
			for (let i = 0; i < numOfSeats; i++){
	    		this.seats[j].push( this.rows[j]+(numOfSeats-i) );
			}
		}
	}



    ngOnInit() {

	}

    // Troca o estado de flip, acionando a animacao
    toggleFlip() {
        this.flip = this.flip === 'inactive' ? 'active' : 'inactive';
    }
}
