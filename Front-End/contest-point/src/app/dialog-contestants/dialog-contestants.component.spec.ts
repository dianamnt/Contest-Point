import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogContestantsComponent } from './dialog-contestants.component';

describe('DialogContestantsComponent', () => {
  let component: DialogContestantsComponent;
  let fixture: ComponentFixture<DialogContestantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogContestantsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogContestantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
