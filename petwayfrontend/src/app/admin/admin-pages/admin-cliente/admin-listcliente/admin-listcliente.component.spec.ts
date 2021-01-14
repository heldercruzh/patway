import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { AdminListclienteComponent } from './admin-listcliente.component';

describe('AdminListclienteComponent', () => {
  let component: AdminListclienteComponent;
  let fixture: ComponentFixture<AdminListclienteComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListclienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListclienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
