import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Destinacija } from 'src/app/models/destinacija';
import { Hotel } from 'src/app/models/hotel';
import { DestinacijaService } from 'src/app/services/destinacija.service';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-hotel-dialog',
  templateUrl: './hotel-dialog.component.html',
  styleUrls: ['./hotel-dialog.component.css']
})
export class HotelDialogComponent implements OnInit {
  flag!: number;
  destinacije!: Destinacija[];

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Hotel>,
    @Inject (MAT_DIALOG_DATA) public data: Hotel,
    public service: HotelService,
    public destinacijaService: DestinacijaService
  ) {}
  
  ngOnInit(): void {
    this.destinacijaService.getAllDestinacija().subscribe(
      (data) => {
        this.destinacije = data;
      }
    );
  }

  public compare(a: any, b: any) {
    return a.id == b.id;
  }

  public add() {
    this.service.addHotel(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno dodat hotel sa nazivom: ${this.data.naziv}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno dodavanje`, `Zatvori`, {duration: 1000});
    }
  }

  public update() {
    this.service.updateHotel(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno azurirana hotel sa nazivom: ${this.data.naziv}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno azuriranje`, `Zatvori`, {duration: 1000});
    }
  }

  public delete() {
    this.service.deleteHotel(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${this.data}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno brisanje`, `Zatvori`, {duration: 1000});
    }
  }

  public cancel() {
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmjena`, `Zatvori`, {duration: 2500});
  }
}
