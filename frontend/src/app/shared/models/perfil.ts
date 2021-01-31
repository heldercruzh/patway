import { GenericModel } from '../generic.model';

export class Perfil implements GenericModel{
    id?: number;
    name: string;

    constructor (){
        this.name = '';
    }

}
