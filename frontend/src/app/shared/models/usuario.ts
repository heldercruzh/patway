import { Perfil } from './perfil';
import { GenericModel } from '../generic.model';

export class Usuario implements GenericModel{
  id?: number;
  email: string;
  token: string;
  senha: string;
  perfil: Perfil;

  constructor() {

    this.email = '';
    this.token = '';
    this.senha = '';
    this.perfil = new Perfil;

  }

}