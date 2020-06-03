import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEnrollComponent } from './dialog-enroll.component';

describe('DialogEnrollComponent', () => {
  let component: DialogEnrollComponent;
  let fixture: ComponentFixture<DialogEnrollComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEnrollComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEnrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
