import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { StudijskiProgram, StudijskiProgramPage } from '../../model/studijski-program';
import { StudijskiProgramiService } from '../../service/studijski-programi.service';

@Component({
  selector: 'app-studijski-programi',
  templateUrl: './studijski-programi.component.html',
  styleUrls: ['./studijski-programi.component.css']
})
export class StudijskiProgramiComponent implements OnInit {
  title="Primer Studijskih programa";
  prikaz = false;
  
  
  studijskiProgrami : StudijskiProgram[]=[];
  itemUpdate : StudijskiProgram | null = null;


  constructor(private service : StudijskiProgramiService, public snackBar:MatSnackBar, public loginService : LoginService) {
    service.getAll().subscribe((studijskiProgrami : StudijskiProgramPage<StudijskiProgram>) => {
      this.studijskiProgrami = studijskiProgrami.content;
    })
  }



  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.service.getAll().subscribe((value) => {
      this.studijskiProgrami = value.content;
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

  create(studijskiProgram: StudijskiProgram) {
    this.service.create(studijskiProgram).subscribe((value) => {
      this.getAll();
      let snackBarRef = this.snackBar.open('Created', 'OK!',  {duration: 3000 });
    }, (error) => {
      console.log(error);
    })
  }

  update(studijskiProgram: StudijskiProgram) {
    if(this.itemUpdate && this.itemUpdate.id) {
      this.service.update(this.itemUpdate.id, studijskiProgram).subscribe((value) => {
        this.getAll();
        let snackBarRef = this.snackBar.open('Updated', 'OK!',  {duration: 2000 }); //SnackPoruka nakon izmene
      }, (error) => {
        console.log(error);
      })
    }

  }

  setUpdate(studijskiProgram: any) {
    this.itemUpdate = { ...studijskiProgram };
    this.prikaz = true;
  }

}
