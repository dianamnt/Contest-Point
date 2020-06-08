import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEditContestComponent } from './dialog-edit-contest.component';

describe('DialogEditContestComponent', () => {
  let component: DialogEditContestComponent;
  let fixture: ComponentFixture<DialogEditContestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEditContestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEditContestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
