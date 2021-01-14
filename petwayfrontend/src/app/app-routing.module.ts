import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminHomeComponent } from './admin/admin-pages/admin-home/admin-home.component';
import { AdminCadclienteComponent } from './admin/admin-pages/admin-cliente/admin-cadcliente/admin-cadcliente.component';
import { AdminListclienteComponent } from './admin/admin-pages/admin-cliente/admin-listcliente/admin-listcliente.component';

const routes: Routes = [
  {
    path: '',
    component: AdminHomeComponent},
  {
    path: 'cadcliente',
    component: AdminCadclienteComponent },
  {
    path: 'listcliente',
    component: AdminListclienteComponent },
  {
     path: 'editar/:id',
      component: AdminCadclienteComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
