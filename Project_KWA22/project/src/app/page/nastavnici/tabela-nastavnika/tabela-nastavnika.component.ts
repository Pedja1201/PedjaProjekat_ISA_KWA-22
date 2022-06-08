import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Nastavnik } from 'src/app/model/nastavnik';
import { NastavniciService } from 'src/app/service/nastavnici.service';

@Component({
  selector: 'app-tabela-nastavnika',
  templateUrl: './tabela-nastavnika.component.html',
  styleUrls: ['./tabela-nastavnika.component.css']
})
export class TabelaNastavnikaComponent implements OnInit {
  displayedColumns: string[] = ['id', 'ime', 'biografija', 'jmbg', 'adresa', 'zvanje', "akcije"];
  dataSource : Nastavnik[]=[];
  title="Tabela Nastavnika";

  @Input()
  elementi: any[] = [];

  @Output()
  uklanjanje : EventEmitter<any> = new EventEmitter<any>();

  @Output()
  izmena: EventEmitter<any> = new EventEmitter<any>();


  constructor(private servis : NastavniciService, private router : Router) { 
    servis.getAll().subscribe(nastavnici => { //Ovo sluzi za dobavljanje studenata prilikom
      this.elementi = nastavnici;                            //Rutiranja posebne tabele komponenete       
    });
  }

  ngOnInit(): void {
  }

  ukloni(id:number) {
    this.uklanjanje.emit(id);
  }

  izmeni(id:number) {
    this.izmena.emit(id);
  }

  prikaziDetalje(nastavnik: Nastavnik) {
    this.router.navigate(["/nastavnici", nastavnik.id]);
  }

}
