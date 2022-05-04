import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PohadjanjePredmeta } from 'src/app/model/pohadjanje-predmeta';
import { RealizacijaPredmeta } from 'src/app/model/realizacija-predmeta';
import { Student } from 'src/app/model/student';
import { RealizacijePredmetaService } from 'src/app/service/realizacije-predmeta.service';

@Component({
  selector: 'app-forma-pohadjanja-predmeta',
  templateUrl: './forma-pohadjanja-predmeta.component.html',
  styleUrls: ['./forma-pohadjanja-predmeta.component.css']
})
export class FormaPohadjanjaPredmetaComponent implements OnInit {
  title='Forma Pohadjanja predmeta'

  realizacijePredmeta: RealizacijaPredmeta[] = [];
  
  forma : FormGroup = new FormGroup({
    "konacnaOcena": new FormControl(null, [Validators.required]),
    "realizacijaPredmeta": new FormControl(null, [Validators.required]),

  })
  
  @Output()
  public createEvent: EventEmitter<any> = new EventEmitter<any>();
  
  @Input()
  pohadjanjePredmeta: PohadjanjePredmeta|null = null;

  constructor(private realizacijePredmetaService : RealizacijePredmetaService) { }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(this.pohadjanjePredmeta);
    this.forma.get("id")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("konacnaOcena")?.setValue(this.pohadjanjePredmeta?.konacnaOcena);
    this.forma.get("realizacijaPredmeta")?.setValue(this.pohadjanjePredmeta?.realizacijaPredmeta);

  }

  ngOnInit(): void {
    this.realizacijePredmetaService.getAll().subscribe(realizacijePredmeta =>{
      this.realizacijePredmeta = realizacijePredmeta;
    });
    this.forma.get("id")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("konacnaOcena")?.setValue(this.pohadjanjePredmeta?.id);
    this.forma.get("realizacijaPredmeta")?.setValue(this.pohadjanjePredmeta?.id);
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

}
