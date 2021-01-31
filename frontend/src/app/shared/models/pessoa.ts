import { Usuario } from './usuario';
import { Uf } from './uf';
import { Municipio } from './municipio';
import { GenericModel } from '../generic.model';

export class Pessoa implements GenericModel {
  id?: number;
  nome: string;
  usuario: Usuario;
  cpf: string;
  rg: string;
  ssp: Uf;
  dataNascimento: string;
  genero?: boolean;
  telefone: string;
  celular: string;
  cep: string;
  municipio:  Municipio;
  bairro: string;
  endereco: string;
  numero: string;
  complemento: string;
  ativo: boolean;

  constructor() {
    this.nome = '';
    this.usuario = new Usuario;
    this.cpf = '';
    this.rg = '';
    this.ssp = new Uf;
    this.dataNascimento ='';
    this.telefone = '';
    this.celular = '';
    this.cep = '';
    this.municipio = new Municipio;
    this.bairro = '';
    this.endereco = '';
    this.numero = '';
    this.complemento = '';
    this.ativo = true;

  }

}
