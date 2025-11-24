import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Aranzman } from 'src/app/models/aranzman';
import { Hotel } from 'src/app/models/hotel';
import { AranzmanService } from 'src/app/services/aranzman.service';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-aranzman-dialog',
  templateUrl: './aranzman-dialog.component.html',
  styleUrls: ['./aranzman-dialog.component.css']
})
export class AranzmanDialogComponent {
  flag!: number;
  hoteli!: Hotel[];

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Aranzman>,
    @Inject (MAT_DIALOG_DATA) public data: Aranzman,
    public service: AranzmanService,
    public hoteliService: HotelService
  ) {}
  
  ngOnInit(): void {
    this.hoteliService.getAllHotel().subscribe(
      (data) => {
        this.hoteli = data;
      }
    );
  }

  public compare(a: any, b: any) {
    return a.id == b.id;
  }

  public add() {
    this.service.addAranzman(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno dodat aranzman sa ukupnom cijenom: ${this.data.ukupnaCena}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno dodavanje`, `Zatvori`, {duration: 1000});
    }
  }

  public update() {
    this.service.updateAranzman(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno azuriran aranzman sa ukupnom cijenom: ${this.data.ukupnaCena}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno azuriranje`, `Zatvori`, {duration: 1000});
    }
  }

  public delete() {
    this.service.deleteAranzman(this.data.id).subscribe(
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
