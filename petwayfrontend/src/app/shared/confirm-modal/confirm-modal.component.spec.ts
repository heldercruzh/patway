import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ComfirmModalComponent } from './confirm-modal.component';

describe('ComfirmModalComponent', () => {
  let component: ComfirmModalComponent;
  let fixture: ComponentFixture<ComfirmModalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ComfirmModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComfirmModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
