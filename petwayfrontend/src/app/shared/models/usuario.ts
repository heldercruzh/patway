import { Perfil } from './perfil';

export class Usuario {
  id: number;
  email: string;
  token?: string;
  senha: string;
  perfil: Perfil;

}
