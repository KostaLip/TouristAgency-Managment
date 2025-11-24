import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Destinacija } from 'src/app/models/destinacija';
import { DestinacijaService } from 'src/app/services/destinacija.service';

@Component({
  selector: 'app-destinacija-dialog',
  templateUrl: './destinacija-dialog.component.html',
  styleUrls: ['./destinacija-dialog.component.css']
})
export class DestinacijaDialogComponent {
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Destinacija>,
    @Inject (MAT_DIALOG_DATA) public data: Destinacija,
    public service: DestinacijaService
  ) {}

  public add() {
    this.service.addDestinacija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno dodata destinacija u mjestu: ${this.data.mesto}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno dodavanje`, `Zatvori`, {duration: 1000});
    }
  }

  public update() {
    this.service.updateDestinacija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspjesno azurirana destinacija u mjestu: ${this.data.mesto}`, `U redu`, {duration: 2500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspjesno azuriranje`, `Zatvori`, {duration: 1000});
    }
  }

  public delete() {
    this.service.deleteDestinacija(this.data.id).subscribe(
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
