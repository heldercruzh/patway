import { Usuario } from './usuario';
import { Uf } from './uf';
import { Municipio } from './municipio';

export class Pessoa {
  id: number;
  nome: string;
  usuario: Usuario;
  cpf: string;
  rg: string;
  ssp: Uf;
  dataNascimento: string;
  genero: boolean;
  telefone: string;
  celular: string;
  cep: string;
  municipio: Municipio;
  bairro: string;
  endereco: string;
  numero: string;
  complemento: string;
  ativo: boolean;
}
