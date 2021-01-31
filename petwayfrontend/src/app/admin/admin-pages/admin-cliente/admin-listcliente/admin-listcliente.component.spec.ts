import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminListclienteComponent } from './admin-listcliente.component';


describe('AdminListclienteComponent', () => {
  let component: AdminListclienteComponent;
  let fixture: ComponentFixture<AdminListclienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminListclienteComponent ]
    })

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListclienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

});
 