import { Component, OnInit } from '@angular/core';
import { Pessoa } from '../../../../shared/models/pessoa';
import { first } from 'rxjs/operators';
import { PessoaService } from '../../../../shared/services/pessoa.service';
import { ConfirmModalComponent } from '../../../../shared/confirm-modal/confirm-modal.component';
import { AlertModalService } from '../../../../shared/alert-modal.service';
import { switchMap, take } from 'rxjs/operators';
import { EMPTY } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-listcliente',
  templateUrl: './admin-listcliente.component.html',
  styleUrls: ['./admin-listcliente.component.css']
})
export class AdminListclienteComponent implements OnInit {

  loading = false;
  pessoas: Pessoa[];
  pessoaSelecionada: Pessoa;

  public dataMask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  constructor(
    private pessoaService: PessoaService,
    private confirmModal: ConfirmModalComponent,
    private modal: AlertModalService,
    private router: Router,
    ) { }

    
  ngOnInit() {
    this.onRefresh();
  }


  onRefresh() {
    this.pessoaService.read().pipe(first())
    .subscribe(
        pessoas => {
          this.loading = false;
          this.pessoas = pessoas;
        },
        error => {
          this.modal.showAlertDanger('Erro ao carregar cursos. Tente novamente mais tarde.');
        });
  }


  onEdit(id) {
    this.router.navigate(['editar', id]);
  }

  onDelete(pessoa) {
    this.pessoaSelecionada = pessoa;
    
    const result$ = this.modal.showConfirm('Confirmacao', 'Tem certeza que deseja remover esse cliente?');
    result$.asObservable()
    .pipe(
      take(1),
      switchMap(result => result ? this.pessoaService.remove(pessoa.id): EMPTY)
    )
    .subscribe(
      success => {
        this.onRefresh();
      },
      error => {
        this.modal.showAlertDanger('Erro ao remover curso. Tente novamente mais tarde.');
      }
    );
  }

}
