import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { NaucnaOblast, NaucnaOblastPage } from '../../model/naucna-oblast';
import { NaucneOblastiService } from '../../service/naucne-oblasti.service';

@Component({
  selector: 'app-naucne-oblasti',
  templateUrl: './naucne-oblasti.component.html',
  styleUrls: ['./naucne-oblasti.component.css']
})
export class NaucneOblastiComponent implements OnInit {
  title="Primer Naucne oblasti";
  prikaz = false;
  
  //Naucna oblast
  naucneOblasti : NaucnaOblast[]=[];
  itemUpdate : NaucnaOblast | null = null;


  constructor(private service : NaucneOblastiService,  public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((naucneOblasti : NaucnaOblastPage<NaucnaOblast>) => {
      this.naucneOblasti = naucneOblasti.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.naucneOblasti = value.content;
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

  create(naucnaOblast: NaucnaOblast) {
    this.service.create(naucnaOblast).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(naucnaOblast: NaucnaOblast) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, naucnaOblast).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(naucnaOblast: any) {
    this.itemUpdate = { ...naucnaOblast };
    this.prikaz = true;
  }


}
