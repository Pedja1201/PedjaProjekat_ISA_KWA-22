import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, FormBuilder } from '@angular/forms';
import { Adresa, AdresaPage } from 'src/app/model/adresa';
import { Nastavnik, NastavnikPage } from 'src/app/model/nastavnik';
import { Student } from 'src/app/model/student';
import { Univerzitet } from 'src/app/model/univerzitet';
import { AdreseService } from 'src/app/service/adrese.service';
import { NastavniciService } from 'src/app/service/nastavnici.service';

@Component({
  selector: 'app-forma-univerziteta',
  templateUrl: './forma-univerziteta.component.html',
  styleUrls: ['./forma-univerziteta.component.css']
})
export class FormaUniverzitetaComponent implements OnInit {
  title='Forma Univerziteta'
  @ViewChild(FormGroupDirective) formGroupDirective: FormGroupDirective | undefined;

  adrese: Adresa[] = [];
  nastavnici: Nastavnik[] = [];
  
  isLinear = false;
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  
  forma : FormGroup = new FormGroup({
    "naziv": new FormControl(null, [Validators.required]),
    "datumVremeOsnivanja": new FormControl(null, [Validators.required]),
    "adresa": new FormControl(null, [Validators.required]),
    "nastavnik": new FormControl(null, [Validators.required]),
  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  univerzitet: Univerzitet|null = null;

  constructor(private adreseService : AdreseService, private nastavniciService : NastavniciService, private _formBuilder: FormBuilder) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.univerzitet);
    this.forma.get("id")?.setValue(this.univerzitet?.id);
    this.forma.get("naziv")?.setValue(this.univerzitet?.naziv);
    this.forma.get("datumVremeOsnivanja")?.setValue(this.univerzitet?.datumVremeOsnivanja);
    this.forma.get("adresa")?.setValue(this.univerzitet?.adresa);
    this.forma.get("nastavnik")?.setValue(this.univerzitet?.nastavnik);
  }

  ngOnInit(): void {
    this.adreseService.getAll().subscribe((adrese : AdresaPage<Adresa>) =>{
      this.adrese = adrese.content;
    });
    this.nastavniciService.getAll().subscribe((nastavnici : NastavnikPage<Nastavnik>) =>{
      this.nastavnici = nastavnici.content;
    });
    this.forma.get("id")?.setValue(this.univerzitet?.id);
    this.forma.get("naziv")?.setValue(this.univerzitet?.id);
    this.forma.get("datumVremeOsnivanja")?.setValue(this.univerzitet?.id);
    this.forma.get("adresa")?.setValue(this.univerzitet?.id);
    this.forma.get("nastavnik")?.setValue(this.univerzitet?.id);

  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
      setTimeout(() => this.formGroupDirective?.resetForm(), 0)    
    }
  }


  //Metoda koja proverava 
  comparator1(adresa1: any, adresa2:any) {
    return adresa1 && adresa2
    ? adresa1.id === adresa2.id
    : adresa1 === adresa2;
  }
  comparator2(nastavnik1: any, nastavnik2:any) {
    return nastavnik1 && nastavnik2
    ? nastavnik1.id === nastavnik2.id
    : nastavnik1 === nastavnik2;
  }

}
