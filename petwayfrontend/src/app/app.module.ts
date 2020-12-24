import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// To validated form
import { ReactiveFormsModule } from '@angular/forms';

// for dont refresh form
import { FormsModule } from '@angular/forms';
import { TextMaskModule } from 'angular2-text-mask';

// for working on service conetion to API
import { HttpClientModule } from '@angular/common/http';
import { AdminHomeComponent } from './admin/admin-pages/admin-home/admin-home.component';
import { AdminCadclienteComponent } from './admin/admin-pages/admin-cliente/admin-cadcliente/admin-cadcliente.component';
import { AdminListclienteComponent } from './admin/admin-pages/admin-cliente/admin-listcliente/admin-listcliente.component';
import { AdminFooterComponent } from './admin/admin-footer/admin-footer.component';
import { AdminNavbarComponent } from './admin/admin-navbar/admin-navbar.component';
import { AlertModalComponent } from './shared/alert-modal/alert-modal.component';
import { ConfirmModalComponent } from './shared/confirm-modal/confirm-modal.component';

// ngx-bootstrap imports
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule, BsModalRef } from 'ngx-bootstrap/modal';
import { AlertModule } from 'ngx-bootstrap/alert';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent,
    AdminCadclienteComponent,
    AdminListclienteComponent,
    AdminFooterComponent,
    AdminNavbarComponent,
    AlertModalComponent,
    ConfirmModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    TextMaskModule,    
    HttpClientModule,
    BrowserAnimationsModule,
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    AlertModule.forRoot(),
    BsDatepickerModule.forRoot(),
  ],
  entryComponents: [
    AlertModalComponent,
    ConfirmModalComponent
  ],
  providers: [
    BsModalRef,
    ConfirmModalComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
