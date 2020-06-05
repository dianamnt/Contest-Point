import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogUnenrollComponent } from './dialog-unenroll.component';

describe('DialogUnenrollComponent', () => {
  let component: DialogUnenrollComponent;
  let fixture: ComponentFixture<DialogUnenrollComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogUnenrollComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogUnenrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
