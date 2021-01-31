import { Uf } from './uf';
import { GenericModel } from '../generic.model';

export class Municipio implements GenericModel{
  id?: number;
  codigoIbge: string;
  nomeMunicipio:string;
  uf: Uf;

  constructor (){
    this.codigoIbge = '';
    this.nomeMunicipio = '';
    this.uf = new Uf;
  }

}
