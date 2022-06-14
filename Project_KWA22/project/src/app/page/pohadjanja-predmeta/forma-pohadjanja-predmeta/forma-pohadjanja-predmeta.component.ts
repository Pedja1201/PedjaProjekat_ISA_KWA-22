import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { RealizacijaPredmeta } from 'src/app/model/realizacija-predmeta';
import { Student } from 'src/app/model/student';
import { RealizacijePredmetaService } from 'src/app/service/realizacije-predmeta.service';
import { StudentiService } from 'src/app/service/studenti.service';

@Component({
  selector: 'app-forma-pohadjanja-predmeta',
  templateUrl: './forma-pohadjanja-predmeta.component.html',
  styleUrls: ['./forma-pohadjanja-predmeta.component.css']
})
export class FormaPohadjanjaPredmetaComponent implements OnInit {
  title='Forma Pohadjanja predmeta'

  realizacijePredmeta: RealizacijaPredmeta[] = [];
  studenti: Student[] = [];
  
  forma : FormGroup = new FormGroup({
    "konacnaOcena": new FormControl(null, [Validators.required]),
    "realizacijaPredmeta": new FormControl(null, [Validators.required]),
    "student": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  pohadjanjePredmeta: PohadjanjePredmeta|null = null;

  constructor(private realizacijePredmetaService : RealizacijePredmetaService, private studentiService : StudentiService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.pohadjanjePredmeta);
    this.forma.get("id")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("konacnaOcena")?.setValue(this.pohadjanjePredmeta?.konacnaOcena);
    this.forma.get("realizacijaPredmeta")?.setValue(this.pohadjanjePredmeta?.realizacijaPredmeta);
    this.forma.get("student")?.setValue(this.pohadjanjePredmeta?.student);
  }

  ngOnInit(): void {
    this.realizacijePredmetaService.getAll().subscribe(realizacijePredmeta =>{
      this.realizacijePredmeta = realizacijePredmeta;
    });
    this.studentiService.getAll().subscribe(studenti =>{
      this.studenti = studenti;
    });
    this.forma.get("id")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("konacnaOcena")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("realizacijaPredmeta")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("student")?.setValue(this.pohadjanjePredmeta?.id);
  }

  create() {
    if(this.forma.valid) {
      this.createEvent.emit(this.forma.value);
    }
  }


  //Metoda koja proverava 
  comparator(realizacijaPredmeta1: any, realizacijaPredmeta2:any) {
    return realizacijaPredmeta1 && realizacijaPredmeta2
    ? realizacijaPredmeta1.id === realizacijaPredmeta2.id
    : realizacijaPredmeta1 === realizacijaPredmeta2;
  }
  comparator2(student1: any, student2:any) {
    return student1 && student2
    ? student1.id === student2.id
    : student1 === student2;
  }

}
