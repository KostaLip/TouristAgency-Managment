import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TuristickaAgencija } from 'src/app/models/turisticka_agencija';
import { TuristickaAgencijaService } from 'src/app/services/turisticka-agencija.service';

@Component({
  selector: 'app-turisticka-agencija-dialog',
  templateUrl: './turisticka-agencija-dialog.component.html',
  styleUrls: ['./turisticka-agencija-dialog.component.css']
})
export class TuristickaAgencijaDialogComponent {

  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<TuristickaAgencija>,
    @Inject (MAT_DIALOG_DATA) public data: TuristickaAgencija,
    public service: TuristickaAgencijaService
  ) {}

  public add() {
    this.service.addTuristickaAgencija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno dodata turisticka agencija sa nazivom: ${this.data.naziv}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno dodavanje`, `Zatvori`, {duration: 1000});
    }
  }

  public update() {
    this.service.updateTuristickaAgencija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno azurirana turisticka agencija sa nazivom: ${this.data.naziv}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno azuriranje`, `Zatvori`, {duration: 1000});
    }
  }

  public delete() {
    this.service.deleteTuristickaAgencija(this.data.id).subscribe(
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
