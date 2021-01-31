import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AlertModalService } from '../../../shared/alert-modal.service';
import { PessoaService } from '../../../shared/services/pessoa.service';
import { Pessoa } from '../../../shared/models/pessoa';
import { Usuario } from '../../../shared/models/usuario';
import { UsuarioService } from '../../../shared/services/usuario.service';
import { Uf } from '../../../shared/models/uf';
import { UfService } from '../../../shared/services/uf.service';
import { Municipio } from '../../../shared/models/municipio';
import { MunicipioService } from '../../../shared/services/municipio.service';
import { Observable } from 'rxjs';
import { Perfil } from 'src/app/shared/models/perfil';

@Component({
  selector: 'app-cliente-cad',
  templateUrl: './cliente-cad.component.html',
  styleUrls: ['./cliente-cad.component.css']
})
export class ClienteCadComponent implements OnInit {

  clienteForm: FormGroup = this.formBuilder.group({});
  loading = false;

  error: string = '';   
  ufs: Observable<Uf[]> = new Observable;
  municipios: Municipio[] = [new Municipio];
  municipiosFiltrados?: Municipio[];
  pessoa: Pessoa = new Pessoa;
  usuario: Usuario = new Usuario;
  ssp: Uf = new Uf;
  municipio: Municipio = new Municipio;
  perfil: Perfil = new Perfil;
   
  public telefoneMask = ['(', /\d/, /\d/, ')', ' ', /\d/,/\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public dataMask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];
  public cpfMask = [/\d/, /\d/, /\d/, '.', /\d/,/\d/,/\d/, '.', /\d/,/\d/,/\d/, '-', /\d/, /\d/];
  public cepMask = [/\d/, /\d/, '.', /\d/,/\d/,/\d/, '-', /\d/,/\d/,/\d/];
  
  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private modal: AlertModalService,
      private pessoaService: PessoaService,
      private usuarioService: UsuarioService,
      private municipioService: MunicipioService,
      private ufService: UfService
  ) { 
    
    this.newPessoa();
    this.createForm();
  }

  ngOnInit() {

    this.listarUfs();

    this.listarMunicipios();
    /*
    this.route.params.subscribe(
      (params: any) => {
         this.pessoaService.read(params['id'])
         .subscribe(
           pessoa => { 
             this.pessoa = pessoa;
                        
             this.editForm();
             this.municipiosFiltrados = this.municipios.filter((item)=> item.uf.id == pessoa.municipio.uf.id);
           });
      }
    );*/
    
  }

  // usado para facilitar o acesso aos camponpmjs.com/package/ do formulário
  get f() { return this.clienteForm.controls; }
  
  
  onSubmit() {

      console.log("t1 - "+this.pessoa.usuario.id);
      
      console.log(this.clienteForm.value);

      /*
      // para aqui se o formulário for inválido
      if (this.clienteForm.invalid) {  
          console.log('formulario inválido');
          return;
      }*/

      this.loading = true;
      
      
            
      this.pessoaService.save(this.clienteForm.value)
          .pipe(first())
          .subscribe(
            retornoPessoa => {
              this.newPessoa();
              this.createForm();
              this.modal.showAlertSuccess("Dados salvo com sucesso!!");
              //this.router.navigate(['/listcliente']);
              },
            error => {                  
    //            this.modal.showAlertDanger(error + " pessoa");
            });
          }
       

 
  private editForm () {
    this.clienteForm = this.formBuilder.group({
        id: [this.pessoa.id],
        nome: [this.pessoa.nome, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        dataNascimento: [this.pessoa.dataNascimento, [Validators.required]], //Validators.pattern('^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-][0-9]{4}$')]],
        genero: [this.pessoa.genero, Validators.required],
        usuario: this.formBuilder.group({
          id: [this.pessoa.usuario.id],
          email: [this.pessoa.usuario.email, [Validators.required, Validators.pattern('^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.?/?([a-z]+)?$')]],
          senha: [this.pessoa.usuario.senha, Validators.required],
          perfil: [this.pessoa.usuario.perfil]
        }),        
        telefone: [this.pessoa.telefone, [Validators.required, Validators.pattern('^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$')]],
        celular: [this.pessoa.celular, [Validators.required, Validators.pattern('^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$')]],
        cpf: [this.pessoa.cpf, [Validators.required, Validators.pattern('^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}')]],
        rg: [this.pessoa.rg, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
        ssp: this.formBuilder.group({
          id: [this.pessoa.ssp.id, Validators.required]
        }),
        cep: [this.pessoa.cep, [Validators.required, Validators.pattern('[0-9]{2}.[0-9]{3}-[0-9]{3}')]],
        bairro: [this.pessoa.bairro, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]], 
        uf: [this.pessoa.municipio.uf.id, Validators.required],
        municipio: this.formBuilder.group({
          id: [this.pessoa.municipio.id, Validators.required] 
        }),
        endereco: [this.pessoa.endereco, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        numero: [this.pessoa.numero, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        complemento: [this.pessoa.complemento]
    });
    this.loading = false;
  }

  private createForm() {
    this.clienteForm = this.formBuilder.group({
        id: [this.pessoa.id],
        nome: [this.pessoa.nome, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        dataNascimento: [this.pessoa.dataNascimento, [Validators.required]], //Validators.pattern('^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-][0-9]{4}$')]],
        genero: [this.pessoa.genero, Validators.required],
        usuario: this.formBuilder.group({
          id: [this.usuario.id],
          email: [this.usuario.email, [Validators.required, Validators.pattern('^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.?/?([a-z]+)?$')]],
          senha: [this.usuario.senha, Validators.required],
          perfil: [this.usuario.perfil]
        }),        
        telefone: [this.pessoa.telefone, [Validators.required, Validators.pattern('^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$')]],
        celular: [this.pessoa.celular, [Validators.required, Validators.pattern('^(\\([0-9]{2}\\))\\s([9]{1})?([0-9]{4})-([0-9]{4})$')]],
        cpf: [this.pessoa.cpf, [Validators.required, Validators.pattern('^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}')]],
        rg: [this.pessoa.rg, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
        ssp: this.formBuilder.group({
          id: [this.ssp.id, Validators.required]
        }),
        cep: [this.pessoa.cep, [Validators.required, Validators.pattern('[0-9]{2}.[0-9]{3}-[0-9]{3}')]],
        bairro: [this.pessoa.bairro, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]], 
        uf: ['', Validators.required],
        municipio: this.formBuilder.group({
          id: [this.municipio.id, Validators.required] 
        }),
        endereco: [this.pessoa.endereco, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        numero: [this.pessoa.numero, [Validators.required, Validators.minLength(1), Validators.maxLength(100)]],
        complemento: [this.pessoa.complemento]
    });
    this.loading = false;
  }


  newPessoa(){
    
    this.pessoa = new Pessoa();
   
    this.municipio = new Municipio();
    this.municipio.uf = new Uf();
    this.ssp = new Uf();
    this.usuario = new Usuario();

    this.perfil = new Perfil();
    //fixar o perfil administrador por default
    this.perfil.id = 1;
    this.usuario.perfil = this.perfil;
  }


  dateMask(event: any) {
    var v = event.target.value;
    if (v.match(/^\d{2}$/) !== null) {
      event.target.value = v + '/';
    } else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
      event.target.value = v + '/';
    }
  }

 

  listarUfs(): any {
    this.ufs = this.ufService.read();
  }

  listarMunicipios() {
    this.municipioService.read().pipe(first())
    .subscribe(
        municipios => {
          this.municipios = municipios;
        });
  }

  buscarMunicipiosPorUF(id:number){
    //this.clienteForm.get('municipio').setValue(new Municipio);
    this.municipiosFiltrados = this.municipios.filter((item)=> item.uf.id == id);
  }

}
