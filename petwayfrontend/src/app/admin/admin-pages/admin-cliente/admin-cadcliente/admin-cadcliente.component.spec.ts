import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminCadclienteComponent } from './admin-cadcliente.component';


describe('AdminCadclienteComponent', () => {
  let component: AdminCadclienteComponent;
  let fixture: ComponentFixture<AdminCadclienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
     
      declarations: [ AdminCadclienteComponent ]
    })

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCadclienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

});