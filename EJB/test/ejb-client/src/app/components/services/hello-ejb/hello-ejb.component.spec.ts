import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelloEjbComponent } from './hello-ejb.component';

describe('HelloEjbComponent', () => {
  let component: HelloEjbComponent;
  let fixture: ComponentFixture<HelloEjbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelloEjbComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HelloEjbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
