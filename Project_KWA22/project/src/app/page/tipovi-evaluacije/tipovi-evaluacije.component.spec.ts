import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoviEvaluacijeComponent } from './tipovi-evaluacije.component';

describe('TipoviEvaluacijeComponent', () => {
  let component: TipoviEvaluacijeComponent;
  let fixture: ComponentFixture<TipoviEvaluacijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TipoviEvaluacijeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoviEvaluacijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
