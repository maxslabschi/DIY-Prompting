import {AfterViewInit, Component, inject, OnInit, ViewChild} from '@angular/core';
import {MatTableModule, MatTable, MatTableDataSource} from '@angular/material/table';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { MatSortModule, MatSort } from '@angular/material/sort';
import {Flight} from '../interfaces';
import {DataService} from '../data.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrl: './flights.component.css',
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, DatePipe]
})
export class FlightsComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Flight>;
  dataSource = new MatTableDataSource<Flight>();

  dataService: DataService = inject(DataService);

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['flightNumber', 'departure', 'arrival', 'departureTime', 'arrivalTime'];

  ngOnInit() {
    this.dataService.getAllFlights().subscribe(flights => {
      this.dataSource.data = flights;
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
