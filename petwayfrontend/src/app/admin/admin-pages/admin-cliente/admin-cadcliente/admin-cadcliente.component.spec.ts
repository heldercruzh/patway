import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { AdminCadclienteComponent } from './admin-cadcliente.component';

describe('AdminCadclienteComponent', () => {
  let component: AdminCadclienteComponent;
  let fixture: ComponentFixture<AdminCadclienteComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCadclienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCadclienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
