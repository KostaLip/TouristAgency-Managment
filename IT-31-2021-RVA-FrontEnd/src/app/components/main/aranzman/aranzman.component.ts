import { Component, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Aranzman } from 'src/app/models/aranzman';
import { AranzmanService } from 'src/app/services/aranzman.service';
import { AranzmanDialogComponent } from '../../dialogs/aranzman-dialog/aranzman-dialog.component';
import { Hotel } from 'src/app/models/hotel';
import { TuristickaAgencija } from 'src/app/models/turisticka_agencija';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-aranzman',
  templateUrl: './aranzman.component.html',
  styleUrls: ['./aranzman.component.css']
})
export class AranzmanComponent implements OnChanges {
  displayedColumns = ['id', 'ukupnaCena', 'placeno', 'datumRealizacije', 'hotel', 'actions'];
  dataSource!: MatTableDataSource<Aranzman>;
  subscription!: Subscription;
  @Input() childSelectedAgencija!: TuristickaAgencija;

  @ViewChild(MatSort, {static: false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;

  constructor(private service:AranzmanService, public dialog:MatDialog) {

  }

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.subscription = this.service.getAranzmanByTuristickaAgencija(this.childSelectedAgencija.id).subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
    };
  }

  public openDialog(flag: number, id?: number, ukupnaCena?: number, placeno?: boolean, datumRealizacije?: Date, hotel?: Hotel) {
    const dialogRef = this.dialog.open(AranzmanDialogComponent, {data: {id, ukupnaCena, placeno, datumRealizacije, hotel}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.componentInstance.data.agencija = this.childSelectedAgencija;
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }

  public applyFilter(filter: any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

}
