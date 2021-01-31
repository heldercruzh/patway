import { GenericModel } from '../generic.model';

export class Uf implements GenericModel{
    id?: number = 0;
    nome: string = '';
    uf: string = '';

    constructor() {
        this.nome = '';
        this.uf = '';
    }
}
