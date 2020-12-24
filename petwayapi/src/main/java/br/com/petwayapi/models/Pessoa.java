package br.com.petwayapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.petwayapi.models.Pessoa;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	public Pessoa() { }
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@OneToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;
				
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String rg;
	
	@ManyToOne(targetEntity = Uf.class)
	@JoinColumn(name = "ssp")
	private Uf ssp;
		
	@Column(nullable = false)
	private String dataNascimento;
	
	@Column(nullable = false)
	private boolean genero;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String celular;
	
	@Column(nullable = false)
	private String cep;
	
	@ManyToOne(targetEntity = Municipio.class)
	@JoinColumn(name = "municipioId")
	private Municipio municipio;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = true)
	private String complemento;
			
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Uf getSsp() {
		return ssp;
	}

	public void setSsp(Uf ssp) {
		this.ssp = ssp;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
			
}
	