import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { Fakultet, FakultetPage } from '../../model/fakultet';
import { AdreseService } from '../../service/adrese.service';
import { FakultetiService } from '../../service/fakulteti.service';
import { NastavniciService } from '../../service/nastavnici.service';
import { UniverzitetiService } from '../../service/univerziteti.service';

@Component({
  selector: 'app-fakulteti',
  templateUrl: './fakulteti.component.html',
  styleUrls: ['./fakulteti.component.css']
})
export class FakultetiComponent implements OnInit {

  title="Primer Fakulteta";
  prikaz = false;
  
  //Fakulteti
  fakulteti : Fakultet[]=[];
  fakultetUpdate : Fakultet | null = null;


  constructor(private service : FakultetiService, private univerzitetiService : UniverzitetiService, 
    private adreseService : AdreseService, private nastavniciService : NastavniciService, public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((fakulteti : FakultetPage<Fakultet>)=> {
      this.fakulteti = fakulteti.content;
    })
  }


  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.fakulteti = value.content;
    }, (error) => {
      console.log(error);
    });
  }

  delete(id: any) {
    this.service.delete(id).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Deleted...', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  create(fakultet: Fakultet) {
    this.service.create(fakultet).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(fakultet: Fakultet) {
    if(this.fakultetUpdate && this.fakultetUpdate.id) {
      this.service.update(this.fakultetUpdate.id, fakultet).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(fakultet: any) {
    this.fakultetUpdate = { ...fakultet };
    this.prikaz = true;
  }

}
