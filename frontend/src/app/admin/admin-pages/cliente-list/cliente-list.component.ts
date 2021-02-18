import { Component, OnInit } from '@angular/core';
import { Pessoa } from '../../../shared/models/pessoa';
import { Relatorio } from '../../../shared/payloader/relatorio';
import { first } from 'rxjs/operators';
import { PessoaService } from '../../../shared/services/pessoa.service';
import { RelatorioService } from '../../../shared/services/relatorio.service';
import { AlertModalService } from '../../../shared/alert-modal.service';
import { switchMap, take } from 'rxjs/operators';
import { EMPTY } from 'rxjs';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {

  loading = false;
  pessoas: Pessoa[];
  pessoaSelecionada: Pessoa;
  reportBlob: Blob = new Blob;
  strhtml: String = '';

  public dataMask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  constructor(
    private pessoaService: PessoaService,
    private modal: AlertModalService,
    private router: Router,
    private relatorioService: RelatorioService
    ) { 
      this.pessoas = [new Pessoa()];
      this.pessoaSelecionada = new Pessoa();
    }

    
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

  gerarRelatorio(pessoas: Pessoa[], formato: string) {
    var relatorio: Relatorio = new Relatorio(pessoas, formato);
    this.relatorioService.export(relatorio).subscribe(data => saveAs(data, `lista_contatos.${relatorio.formato}`));
  }
 

  onPagar(id: number) {
    this.router.navigate(['pagamento', id]);
  }

  onEdit(id: number) {
    this.router.navigate(['cliente-edit', id]);
  }

  onDelete(pessoa: Pessoa) {
    this.pessoaSelecionada = pessoa;
    
    const result$ = this.modal.showConfirm('Confirmacao', 'Tem certeza que deseja remover esse cliente?');
    result$.asObservable()
    .pipe(
      take(1),
      switchMap(
        result => (result && pessoa.id) ? this.pessoaService.remove(pessoa.id): EMPTY)
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
