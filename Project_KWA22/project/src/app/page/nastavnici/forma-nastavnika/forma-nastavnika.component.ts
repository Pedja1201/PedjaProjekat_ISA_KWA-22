import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Adresa } from 'src/app/model/adresa';
import { Nastavnik } from 'src/app/model/nastavnik';
import { Zvanje } from 'src/app/model/zvanje';
import { AdreseService } from 'src/app/service/adrese.service';
import { ZvanjaService } from 'src/app/service/zvanja.service';

@Component({
  selector: 'app-forma-nastavnika',
  templateUrl: './forma-nastavnika.component.html',
  styleUrls: ['./forma-nastavnika.component.css']
})
export class FormaNastavnikaComponent implements OnInit {
  title='Forma Nastavnika'

  adrese: Adresa[] = [];
  zvanja: Zvanje[] = [];
  
  forma : FormGroup = new FormGroup({
    "ime": new FormControl(null, [Validators.required]),
    "biografija": new FormControl(null, [Validators.required]),
    "jmbg": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "zvanje": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  nastavnik: Nastavnik|null = null;

  constructor(private adreseService : AdreseService, private zvanjaService : ZvanjaService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.nastavnik);
    this.forma.get("id")?.setValue(this.nastavnik?.id);
    this.forma.get("ime")?.setValue(this.nastavnik?.ime);
    this.forma.get("biografija")?.setValue(this.nastavnik?.biografija);
    this.forma.get("jmbg")?.setValue(this.nastavnik?.jmbg);
    this.forma.get("adresa")?.setValue(this.nastavnik?.adresa);
    this.forma.get("zvanje")?.setValue(this.nastavnik?.zvanje)
  }

  ngOnInit(): void {
    this.adreseService.getAll().subscribe(adrese =>{
      this.adrese = adrese;
    });
    this.zvanjaService.getAll().subscribe(zvanja =>{
      this.zvanja = zvanja;
    });
    this.forma.get("id")?.setValue(this.nastavnik?.id);
    this.forma.get("ime")?.setValue(this.nastavnik?.id);
    this.forma.get("biografija")?.setValue(this.nastavnik?.id);
    this.forma.get("jmbg")?.setValue(this.nastavnik?.id);
    this.forma.get("adresa")?.setValue(this.nastavnik?.id);
    this.forma.get("zvanje")?.setValue(this.nastavnik?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator1(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  comparator2(zvanje1: any, zvanje2:any) {
    return zvanje1 && zvanje2
    ? zvanje1.id === zvanje2.id
    : zvanje1 === zvanje2;
  }

}
