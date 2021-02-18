import { GenericService } from '../generic.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pessoa } from '../models/pessoa';

@Injectable({
  providedIn: 'root'
})

export class PessoaService extends GenericService<Pessoa>{
  constructor(http: HttpClient) {
    super(http, 'pessoa');
  }
}
